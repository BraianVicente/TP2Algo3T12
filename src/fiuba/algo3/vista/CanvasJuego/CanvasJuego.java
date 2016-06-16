package fiuba.algo3.vista.CanvasJuego;


import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.controlador.TeclaEnCanvasEventHandler;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class CanvasJuego extends Canvas implements Actualizable{
	MueveVista mueveVista;
	Image seleccionador;
	Image seleccionadorObjetivo;
	TeclaEnCanvasEventHandler teclaEventHandler;
	Juego juego;
	private Posicion seleccionada;
	private Posicion objetivo;

	private ArrayList<CallbackSeleccionCasillero> callbacksCasilleros;
	
	public CanvasJuego(Juego juego){
		super(800,600);
		mueveVista = new MueveVista(800,600);
		this.juego=juego;
		seleccionada = new Posicion(0,0);
		objetivo =seleccionada;
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e->mueveVista.presionado(e));
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e->mueveVista.soltado(e));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e->mueveVista.salio(e));
		this.addEventHandler(ScrollEvent.SCROLL, e->mueveVista.scrolleado(e));
		this.setOnMouseDragged(e->mueveVista.movido(e));
		this.setFocusTraversable(true);
		teclaEventHandler= new TeclaEnCanvasEventHandler(seleccionada,objetivo,juego,this);	
		this.setOnKeyPressed(teclaEventHandler);
		mueveVista.seleccionaPosicion(p->selecciona(p));
		Timer timer = new Timer();
		timer.schedule(new Actualizador(this), 0, 33);
	
		seleccionador = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		seleccionadorObjetivo = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionadorObjetivo.png");
		
		callbacksCasilleros = new ArrayList<CallbackSeleccionCasillero>();
	}

	public void seleccionarObjetivo(Posicion p) {
		objetivo=p;
	}

	public void agregarCallback(CallbackSeleccionCasillero call){
		callbacksCasilleros.add(call);
	}

	public void actualizar(){
		double xv = mueveVista.getX();
		double yv = mueveVista.getY();
		double escala = mueveVista.getEscala();
		ArrayList<Unidad> unidades = juego.obtenerUnidades();
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());

		gc.setFill(Color.YELLOW);
		gc.fillRect(0+xv*escala, 0+yv*escala, 10*80*escala, 10*80*escala);

		for(Unidad u: unidades){
			if(u.esAerea()) gc.setFill(Color.LIGHTBLUE);
			else gc.setFill(Color.GREEN);
			Posicion p = juego.posicion(u);
			gc.fillRect(
					(p.getX()*80+xv)*escala,
					(p.getY()*80+yv)*escala,
					80*escala,
					80*escala
			);
		}

		if(seleccionada != null){
			gc.drawImage(seleccionador,
				(seleccionada.getX()*80+xv)*escala,
				(seleccionada.getY()*80+yv)*escala,
				80*escala,
				80*escala);
		}

		if(objetivo != null){
			gc.drawImage(seleccionadorObjetivo,
				(objetivo.getX()*80+xv)*escala,
				(objetivo.getY()*80+yv)*escala,
				80*escala,
				80*escala);
		}
	}
	public void selecciona(Posicion p){
		seleccionada = (Posicion)p.clone();
		Posicion seleccionadaAerea = seleccionada.nuevaPosicionConDistintoPlano(Posicion.Plano.AEREO);
		teclaEventHandler.cambiarSeleccionada(seleccionada);
		//ningun try, si no hay superficie estamos fritos
		Superficie supTerrestre = juego.obtenerSuperficie(seleccionada);
		Superficie supAerea = juego.obtenerSuperficie(seleccionadaAerea);

		Unidad u;
		try{

			u = juego.obtenerUnidad(p);

		}catch(PosicionLibreException e){
			u=null;
		}
		Casillero c = new Casillero(supAerea,supTerrestre,p,u);

		for(CallbackSeleccionCasillero calls : callbacksCasilleros){
			calls.execute(c);
		}
	}
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Posicion getSeleccionada() {
		return seleccionada;
	}
}
