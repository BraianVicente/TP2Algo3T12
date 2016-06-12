package fiuba.algo3.vista.CanvasJuego;

import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class CanvasJuego extends Canvas implements Actualizable{
	MueveVista mueveVista;
	Tablero tablero;
	Image seleccionador;
	private Posicion seleccionada;
	
	private ArrayList<CallbackSeleccionCasillero> callbacksCasilleros;
	
	public CanvasJuego(Tablero tablero){
		super(800,600);
		mueveVista = new MueveVista(800,600);
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e->mueveVista.presionado(e));
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e->mueveVista.soltado(e));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e->mueveVista.salio(e));
		this.addEventHandler(ScrollEvent.SCROLL, e->mueveVista.scrolleado(e));
		this.setOnMouseDragged(e->mueveVista.movido(e));
		
		mueveVista.seleccionaPosicion(p->selecciona(p));
		Timer timer = new Timer();
		timer.schedule(new Actualizador(this), 0, 33);
		this.tablero = tablero;
		
		seleccionador = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		seleccionada = null;
		
		callbacksCasilleros = new ArrayList<CallbackSeleccionCasillero>();
	}
	
	public void agregarCallback(CallbackSeleccionCasillero call){
		callbacksCasilleros.add(call);
	}
	
	public void actualizar(){
		double xv = mueveVista.getX();
		double yv = mueveVista.getY();
		double escala = mueveVista.getEscala();
		ArrayList<Unidad> unidades = tablero.obtenerUnidades();
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		
		gc.setFill(Color.YELLOW);
		gc.fillRect(0+xv*escala, 0+yv*escala, 10*80*escala, 10*80*escala);
		
		gc.setFill(Color.GREEN);
		for(Unidad u: unidades){
			Posicion p = tablero.posicion(u);
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
	}
	public void selecciona(Posicion p){
		seleccionada = (Posicion)p.clone();
		Posicion seleccionadaAerea = seleccionada.nuevaPosicionConDistintoPlano(Posicion.Plano.AEREO); 
		
		//ningún try, si no hay superficie estamos fritos
		Superficie supTerrestre = tablero.obtenerSuperficie(seleccionada);
		Superficie supAerea = tablero.obtenerSuperficie(seleccionadaAerea);
		Unidad u;
		try{
			u = tablero.obtenerUnidad(p);
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
}
