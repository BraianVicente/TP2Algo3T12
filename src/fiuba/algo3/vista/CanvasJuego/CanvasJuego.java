package fiuba.algo3.vista.CanvasJuego;


import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.controlador.TeclaEnCanvasEventHandler;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
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
	private Posicion seleccionadaViejaSeraBorrada;
	private Posicion objetivoVIEJOSERABORRADO;
	CacheImagenes cacheImagenes = new CacheImagenes();

	private ArrayList<CallbackCasillero> callbacksSeleccion;
	private ArrayList<CallbackCasillero> callbacksHover;
	
	public CanvasJuego(Juego juego){
		super(340, 371);
		mueveVista = new MueveVista(340, 371);
		this.juego=juego;
		seleccionadaViejaSeraBorrada = new Posicion(0,0);
		objetivoVIEJOSERABORRADO =seleccionadaViejaSeraBorrada;
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e->mueveVista.presionado(e));
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e->mueveVista.soltado(e));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e->mueveVista.salio(e));
		this.addEventHandler(ScrollEvent.SCROLL, e->mueveVista.scrolleado(e));
		//this.setOnMouseDragged(e->mueveVista.draggeado(e));
		this.setOnMouseMoved(e->mouseMovido(e));
		this.setFocusTraversable(true);
		teclaEventHandler= new TeclaEnCanvasEventHandler(seleccionadaViejaSeraBorrada,objetivoVIEJOSERABORRADO,juego,this);	
		this.setOnKeyPressed(teclaEventHandler);
		mueveVista.seleccionaPosicion(p->selecciona(p));
		Timer timer = new Timer();
		timer.schedule(new Actualizador(this), 0, 33);
	
		seleccionador = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		seleccionadorObjetivo = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		
		callbacksSeleccion = new ArrayList<CallbackCasillero>();
		callbacksHover = new ArrayList<CallbackCasillero>();
		
		modoVista = ModoVista.AMBAS;
	}
	
	//------------------------INPUT----------------------------------------------//
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
		if(juego.enTablero(p)){
			teclaEventHandler.cambiarSeleccionada(p);
			Casillero construido = construirCasillero(p);
			for(CallbackCasillero calls : callbacksSeleccion){
				calls.execute(construido);
			}
		}
	}
	
	public void mouseMovido(MouseEvent e){
		Posicion p = mueveVista.obtenerPosicion(e);
		if(juego.enTablero(p)){
			Casillero construido = construirCasillero(p);
			for(CallbackCasillero calls : callbacksHover){
				calls.execute(construido);
			}
		}
	}

	public void agregarCallbackSeleccion(CallbackCasillero call){
		callbacksSeleccion.add(call);
	}
	
	public void agregarCallbackHover(CallbackCasillero call){
		callbacksHover.add(call);
	}
	
	//----------------------------COSAS QUE ESTAN DUPLICADAS/CAMBIARON----------------//
	public void seleccionarObjetivoESTAFUNCIONHAYQUEBORRARLA(Posicion p) {
		objetivoVIEJOSERABORRADO=p;
	}
	
	public Posicion getSeleccionadaESTAFUNCIONHAYQUEBORRARLA() {
		return seleccionadaViejaSeraBorrada;
	}
	
	//------------------------DIBUJADO----------------------------------------------//

	public void actualizar(){
		double xv = mueveVista.getX();
		double yv = mueveVista.getY();
		double escala = mueveVista.getEscala();
		
		ArrayList<Unidad> unidades = juego.obtenerUnidades();
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());

		//primero, el fondo blanco
		gc.setFill(Color.WHITE);
		gc.fillRect(0+xv*escala, 0+yv*escala, 
				juego.obtenerAncho()*mueveVista.anchoCasillero(),
				juego.obtenerAlto()*mueveVista.altoCasillero());
		
		//la tierra
		if(modoVista==ModoVista.AMBAS || modoVista==ModoVista.SOLOTIERRA){
			dibujarSuperficies(gc,Posicion.Plano.TERRESTRE,1);
		}
		
		//las unidades terrestres
		dibujarUnidades(gc,false);
		
		//el cielo
		if(modoVista==ModoVista.AMBAS){
			dibujarSuperficies(gc,Posicion.Plano.AEREO,0.5f);
		}else if(modoVista==ModoVista.SOLOAIRE){
			dibujarSuperficies(gc,Posicion.Plano.AEREO,1);
		}
		
		//las unidades aereas
		dibujarUnidades(gc,true);
		
		
		//el cuadrado seleccionado
		/*
		if(seleccionadaViejaSeraBorrada != null){
			gc.drawImage(seleccionador,
				(seleccionadaViejaSeraBorrada.getX()*80+xv)*escala,
				(seleccionadaViejaSeraBorrada.getY()*80+yv)*escala,
				80*escala,
				80*escala);
		}

		if(objetivoVIEJOSERABORRADO != null){
			gc.drawImage(seleccionadorObjetivo,
				(objetivoVIEJOSERABORRADO.getX()*80+xv)*escala,
				(objetivoVIEJOSERABORRADO.getY()*80+yv)*escala,
				80*escala,
				80*escala);
		}
		*/
		if(seleccionada != null){
			gc.drawImage(seleccionador,
				mueveVista.xPantalla(seleccionada),
				mueveVista.yPantalla(seleccionada),
				mueveVista.anchoCasillero(),
				mueveVista.altoCasillero());
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
	
	private void dibujarUnidades(GraphicsContext gc, boolean aereas){
		for(Unidad u: juego.obtenerUnidades()){
			Posicion p = juego.posicion(u);
			if(u.esAerea() == aereas){
				try{
					Image imgU = cacheImagenes.obtenerImagen(u.nombreImagen());
					gc.drawImage(imgU,
							mueveVista.xPantalla(p), 
							mueveVista.yPantalla(p), 
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero()
					);
				}catch(ImagenInexistenteExcption e){
					
				}
			}
		}
	}
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	
	//---------------------------------------selecci�n----------------------
	private PosicionEnElPlano seleccionada;
	public void seleccionadorEn(PosicionEnElPlano pos){
		seleccionada = (PosicionEnElPlano) pos.clone();
	}
	public void seleccionadorEn(Posicion pos){
		seleccionada = new PosicionEnElPlano(pos.getX(),pos.getY());
	}
	public void esconderSeleccionador(){
		seleccionada = null;
	}
	
	//no debr�a haber getter ya que VentanaJuego se ocupa s�lo de lo gr�fico
	
	//-----------------------------------modos--------------------------------//
	private ModoVista modoVista;

	public ModoVista getModoVista() {
		return modoVista;
	}

	public void setModoVista(ModoVista modoVista) {
		this.modoVista = modoVista;
	}
	
}
