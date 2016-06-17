package fiuba.algo3.vista.CanvasJuego;


import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.controlador.TeclaEnCanvasEventHandler;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
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
	CacheImagenes cacheImagenes = new CacheImagenes();

	private ArrayList<CallbackCasillero> callbacksSeleccion;
	private ArrayList<CallbackCasillero> callbacksHover;
	
	public CanvasJuego(Juego juego){
		super(340, 371);
		mueveVista = new MueveVista(340, 371);
		this.juego=juego;
		seleccionada = new Posicion(0,0);
		objetivo =seleccionada;
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e->mueveVista.presionado(e));
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e->mueveVista.soltado(e));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e->mueveVista.salio(e));
		this.addEventHandler(ScrollEvent.SCROLL, e->mueveVista.scrolleado(e));
		//this.setOnMouseDragged(e->mueveVista.draggeado(e));
		this.setOnMouseMoved(e->mouseMovido(e));
		this.setFocusTraversable(true);
		teclaEventHandler= new TeclaEnCanvasEventHandler(seleccionada,objetivo,juego,this);	
		this.setOnKeyPressed(teclaEventHandler);
		mueveVista.seleccionaPosicion(p->selecciona(p));
		Timer timer = new Timer();
		timer.schedule(new Actualizador(this), 0, 33);
	
		seleccionador = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		seleccionadorObjetivo = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		
		callbacksSeleccion = new ArrayList<CallbackCasillero>();
		callbacksHover = new ArrayList<CallbackCasillero>();
	}
	
	public void mouseMovido(MouseEvent e){
		Posicion p = mueveVista.obtenerPosicion(e);
		Casillero construido = construirCasillero(p);
		for(CallbackCasillero calls : callbacksHover){
			calls.execute(construido);
		}
	}

	public void seleccionarObjetivo(Posicion p) {
		objetivo=p;
	}

	public void agregarCallbackSeleccion(CallbackCasillero call){
		callbacksSeleccion.add(call);
	}
	
	public void agregarCallbackHover(CallbackCasillero call){
		callbacksHover.add(call);
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
		
		dibujarSuperficies(gc,Posicion.Plano.TERRESTRE,1);

		for(Unidad u: unidades){
			if(u.esAerea()) gc.setFill(Color.LIGHTBLUE);
			else gc.setFill(Color.GREEN);
			Posicion p = juego.posicion(u);
			gc.fillRect(
					mueveVista.xPantalla(p),
					mueveVista.yPantalla(p),
					mueveVista.anchoCasillero(),
					mueveVista.altoCasillero()
			);
		}
		
		dibujarSuperficies(gc,Posicion.Plano.AEREO,0.5f);

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
	private void dibujarSuperficies(GraphicsContext gc, Plano plano, float opacidad) {
		gc.save();
		gc.setGlobalAlpha(opacidad);
		
		for(int x = 0; x<juego.obtenerAncho(); x+=1){
			for(int y = 0; y<juego.obtenerAlto(); y+=1){
				Posicion obtener = new Posicion(x,y,plano);
				try{
					Superficie aDibujar = juego.obtenerSuperficie(obtener);
					Image imgSup = cacheImagenes.obtenerImagen(aDibujar.nombreImagen());
					
					
					gc.drawImage(imgSup,
							mueveVista.xPantalla(obtener), 
							mueveVista.yPantalla(obtener), 
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero()
					);
					
				}catch(ImagenInexistenteExcption e){
					
				}
				
			}
			
		}
		gc.restore();
	}
	
	public Casillero construirCasillero(Posicion pos){
		Posicion terrestre = pos.nuevaPosicionConDistintoPlano(Posicion.Plano.TERRESTRE);
		Posicion aerea = pos.nuevaPosicionConDistintoPlano(Posicion.Plano.AEREO);
		
		//ningun try, si no hay superficie estamos fritos
		Superficie supTerrestre = juego.obtenerSuperficie(terrestre);
		Superficie supAerea = juego.obtenerSuperficie(aerea);
		
		Unidad uTerrestre;
		try{
			uTerrestre = juego.obtenerUnidad(terrestre);
		}catch(PosicionLibreException e){
			uTerrestre=null;
		}
		
		Unidad uAerea;
		try{
			uAerea = juego.obtenerUnidad(aerea);
		}catch(PosicionLibreException e){
			uAerea=null;
		}
		
		return new Casillero(supAerea,supTerrestre,pos,uAerea,uTerrestre);

	}

	public void selecciona(Posicion p){
		teclaEventHandler.cambiarSeleccionada(p);
		Casillero construido = construirCasillero(p);
		for(CallbackCasillero calls : callbacksSeleccion){
			calls.execute(construido);
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
