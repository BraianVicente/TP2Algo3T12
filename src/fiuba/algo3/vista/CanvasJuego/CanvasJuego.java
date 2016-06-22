package fiuba.algo3.vista.CanvasJuego;


import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.controlador.TeclaEnCanvasEventHandler;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * SOBRE CANVASJUEGO:COMO USAR
 * 
 * Desde afuera, solo se deben usar los siguientes metodos:
 * 
 * +agregarCallbackClickeo(!!!!!!)
 * +agregarCallbackHovereo(!!!!!!!!!!)
 * +seleccionadorEn
 * +esconderSeleccionador
 * +destruir() 
 * (hace que se deje de actualizar la ventana y se pueda destruir el canvas, 
 * asi no sigue corriendo una vez cerrada la ventana
 * OJO: el Canvas no se destruye ni se lo lleva el recolector 
 * 	de basura hasta que no se llama este metodo!!!!)
 * +getModoVista
 * +setModoVista
 * 
 * Algunos los tuve que dejar publicos pero ___les pido que no los usen__ !!
 * Si hace falta algo de interfaz, por favor dejenme escrito aca lo que cambiaron!!
 * 
 * CanvasJuego
 * @author Jose Sb
 *
 */


public class CanvasJuego extends Canvas implements Actualizable{
	
	
	
	private MueveVista mueveVista;
	private Image seleccionador;
	private Image seleccionadorObjetivo;
	private TeclaEnCanvasEventHandler teclaEventHandler;
	private Juego juego;
	private Posicion seleccionadaViejaSeraBorrada;
	private Posicion objetivoVIEJOSERABORRADO;
	CacheImagenes cacheImagenes = new CacheImagenes();

	private ArrayList<CallbackCasillero> callbacksClickeo;
	private ArrayList<CallbackCasillero> callbacksHover;
	
	private PosicionEnElPlano hovereando;
	
	
	private Image hovereador;
	
	private Timer timer;
	private Posicion posicionAtaque;
	private Image explosion;
	
	public CanvasJuego(Juego juego){
		super(360, 371);
		
		this.juego=juego;
		
		mueveVista = new MueveVista(360, 371);
		this.addEventHandler(MouseEvent.MOUSE_MOVED, e->mueveVista.movido(e));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->mueveVista.clickeado(e));
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e->mueveVista.presionado(e));
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e->mueveVista.soltado(e));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e->mueveVista.salio(e));
		this.addEventHandler(ScrollEvent.SCROLL, e->mueveVista.scrolleado(e));
		this.setOnMouseDragged(e->mueveVista.draggeado(e));
		this.setFocusTraversable(true);
		
		seleccionadaViejaSeraBorrada = new Posicion(0,0);
		objetivoVIEJOSERABORRADO =seleccionadaViejaSeraBorrada;
		
		teclaEventHandler= new TeclaEnCanvasEventHandler(seleccionadaViejaSeraBorrada,objetivoVIEJOSERABORRADO,juego,this);	
		this.setOnKeyPressed(teclaEventHandler);
		
		/*
		timer = new Timer();
		timer.schedule(new Actualizador(this), 0, 33);
		*/
		
		seleccionador = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		seleccionadorObjetivo = new Image("/fiuba/algo3/vista/CanvasJuego/seleccionador.png");
		hovereador = new Image("/fiuba/algo3/vista/CanvasJuego/hovereador.png");
		explosion = new Image("/fiuba/algo3/vista/imagenes/efectos/explosion.png");
		posicionAtaque=null;
		callbacksClickeo = new ArrayList<CallbackCasillero>();
		callbacksHover = new ArrayList<CallbackCasillero>();
		
		modoVista = ModoVista.AMBAS;
		hovereando = new PosicionEnElPlano(0,0); 
		
		mueveVista.agregarCallbackClickeo(p->clickea(p));
		mueveVista.agregarCallbackHover(p->hoverea(p));
		
		actualizar();
	}
	
	//--------------------MANEJO DE CONCURRENCIA/LIMPIEZA----------------------//
	public void destruir(){
		timer.cancel();
		timer.purge();
	}
	
	//------------------------INPUT----------------------------------------------//
	public Casillero construirCasillero(Posicion pos){
		return juego.construirCasillero(pos);

	}

	private void clickea(Posicion p){
		if(juego.enTablero(p)){
			teclaEventHandler.cambiarSeleccionada(p);
			Casillero construido = construirCasillero(p);
			for(CallbackCasillero calls : callbacksClickeo){
				calls.execute(construido);
			}
		}
		actualizar();
	}
	
	private void hoverea(Posicion p){
		//Posicion p = mueveVista.obtenerPosicion(e);
		if(juego.enTablero(p)){
			Casillero construido = construirCasillero(p);
			for(CallbackCasillero calls : callbacksHover){
				calls.execute(construido);
			}
		}
		actualizar();
		synchronized(hovereando){
			hovereando = new PosicionEnElPlano(p.getX(),p.getY());
		}
	}

	public void agregarCallbackClickeo(CallbackCasillero call){
		callbacksClickeo.add(call);
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
		
		//System.out.println("Still updating!");
		
		double xv = mueveVista.getX();
		double yv = mueveVista.getY();
		double escala = mueveVista.getEscala();
		
		ArrayList<Unidad> unidades = juego.obtenerUnidades();
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		

		//el fondo blanco
		gc.setFill(Color.WHITE);
		gc.fillRect(0+xv*escala, 0+yv*escala, 
				juego.obtenerAncho()*mueveVista.anchoCasillero(),
				juego.obtenerAlto()*mueveVista.altoCasillero());
		
		//la tierra
		if(modoVista==ModoVista.AMBAS || modoVista==ModoVista.SOLOTIERRA){
			dibujarSuperficies(gc,Posicion.Plano.TERRESTRE,1);
		}
		dibujarMontePerdicion(gc);	
		//los bonuses
		
		dibujarBonuses(gc);		
		dibujarChispa(gc);
		
		//las unidades terrestres
			if(modoVista==ModoVista.AMBAS || modoVista==ModoVista.SOLOTIERRA) dibujarUnidades(gc,Plano.TERRESTRE);
		//el cielo
		if(modoVista==ModoVista.AMBAS){
			dibujarSuperficies(gc,Posicion.Plano.AEREO,0.5f);
		}else if(modoVista==ModoVista.SOLOAIRE){
			dibujarSuperficies(gc,Posicion.Plano.AEREO,0.8f);
		}
		//efectos
		dibujarEfectos(gc);
		//las unidades aerea
		if(modoVista==ModoVista.AMBAS || modoVista==ModoVista.SOLOAIRE){
			dibujarUnidades(gc,Plano.AEREO);
		}
		
		
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
		
		//el cuadrado hovereando
		synchronized(hovereando){
			gc.drawImage(hovereador,
					mueveVista.xPantalla(hovereando),
					mueveVista.yPantalla(hovereando),
					mueveVista.anchoCasillero(),
					mueveVista.altoCasillero());
			
		}
		//el cartelito
		String accion = juego.accionPosibleEn(hovereando);
		if(accion!=""){
			Bounds bounds = (new Text(accion)).getLayoutBounds();
			double offsetX=20;
			double offsetY=20;
			gc.save();
			gc.setFill(Color.BEIGE);
			gc.fillRect(offsetX+mueveVista.getXMouse(), offsetY+mueveVista.getYMouse(), bounds.getWidth()+20, bounds.getHeight()*1.5);
			gc.setFill(Color.BLACK);
			gc.fillText(accion, offsetX+mueveVista.getXMouse()+10, offsetY + mueveVista.getYMouse()+bounds.getHeight());
			gc.restore();
		}
		
		
		
		//el halo de ataque
		if(haloAtaque!=null){
			gc.save();
			gc.setFill(Color.RED);
			gc.setGlobalAlpha(0.3);
			for(PosicionEnElPlano p : haloAtaque){
				gc.fillRect(mueveVista.xPantalla(p),
							mueveVista.yPantalla(p),
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero());
			}
			gc.restore();
		}
		//el halo de movimiento
		if(haloMovimiento!=null){
			gc.save();
			gc.setFill(Color.YELLOW);
			gc.setGlobalAlpha(0.3);
			for(PosicionEnElPlano p : haloMovimiento){
				gc.fillRect(mueveVista.xPantalla(p),
							mueveVista.yPantalla(p),
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero());
			}
			gc.restore();
		}
	}

	private void dibujarMontePerdicion(GraphicsContext gc) {
		try {
			gc.drawImage(cacheImagenes.obtenerImagen(juego.obtenerNombreImagenMontePerdicion()),
						mueveVista.xPantalla(juego.posicionMontePerdicion()), 
						mueveVista.yPantalla(juego.posicionMontePerdicion()), 
						mueveVista.anchoCasillero(),
						mueveVista.altoCasillero());
		} catch (RuntimeException e) {

		}
	}

	private void dibujarChispa(GraphicsContext gc) {

			try {
				gc.drawImage(cacheImagenes.obtenerImagen(Chispa.nombreImagen()),
							mueveVista.xPantalla(juego.posicionChispa()), 
							mueveVista.yPantalla(juego.posicionChispa()), 
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero());
			} catch (RuntimeException e) {

			}

		
	}

	private void dibujarEfectos(GraphicsContext gc) {
		if(posicionAtaque!=null)
			gc.drawImage(explosion,
						mueveVista.xPantalla(posicionAtaque), 
						mueveVista.yPantalla(posicionAtaque), 
						mueveVista.anchoCasillero(),
						mueveVista.altoCasillero());
		posicionAtaque=null;
		
	}
	public void setearPosicionExplosion(Posicion p){
		posicionAtaque=p;
	}
	private void dibujarBonuses(GraphicsContext gc) {
		ArrayList<Bonus> bonuses = juego.obtenerBonuses();
		for(Bonus b: bonuses){
			Posicion p = juego.posicion(b);
			try{
				Image imgB = cacheImagenes.obtenerImagen(b.nombreImagen());
				gc.drawImage(imgB,
						mueveVista.xPantalla(p), 
						mueveVista.yPantalla(p), 
						mueveVista.anchoCasillero(),
						mueveVista.altoCasillero()
				);
			}catch(ImagenInexistenteException e){
				
			}
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
					
				}catch(ImagenInexistenteException e){
					
				}
				
			}
			
		}
		gc.restore();
	}
	
	private void dibujarUnidades(GraphicsContext gc, Plano plano){
		for(Unidad u: juego.obtenerUnidades()){
			
			if(u.getPlanoPerteneciente() == plano){
				dibujarUnidad(gc, u);
				
			}
		}
	}
	
	
	private void dibujarUnidad(GraphicsContext gc, Unidad u) {
		Posicion p = juego.posicion(u);
		try{
			Image imgU = cacheImagenes.obtenerImagen(u.nombreImagen());
			gc.drawImage(imgU,
					mueveVista.xPantalla(p), 
					mueveVista.yPantalla(p), 
					mueveVista.anchoCasillero(),
					mueveVista.altoCasillero()
			);
			ArrayList<String> nombres_imagenes = u.obtenerNombresImagenesModificadores();
			gc.save();
			gc.setGlobalAlpha(0.5f/nombres_imagenes.size());
			for(String nombre : nombres_imagenes ){
				if(nombre != null){
					Image imgM = cacheImagenes.obtenerImagen(nombre);
					gc.drawImage(imgM,
							mueveVista.xPantalla(p), 
							mueveVista.yPantalla(p), 
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero()
					);
				}
			}
			gc.restore();
		}catch(ImagenInexistenteException e){
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
		actualizar();
	}
	
	//-----------------------------------mostrar halos de ataque/movimiento------//
	private ArrayList<PosicionEnElPlano> haloAtaque;
	private ArrayList<PosicionEnElPlano> haloMovimiento;
	
	public void setHaloAtaque(ArrayList<PosicionEnElPlano> haloAtaque){
		this.haloAtaque = haloAtaque;
	}
	
	public void setHaloMovimiento(ArrayList<PosicionEnElPlano> haloMovimiento){
		this.haloMovimiento = haloMovimiento;
	}
	
}
