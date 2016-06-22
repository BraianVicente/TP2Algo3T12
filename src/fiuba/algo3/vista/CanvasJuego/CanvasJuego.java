package fiuba.algo3.vista.CanvasJuego;


import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.posicion.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.WritableFloatValue;
import javafx.geometry.Bounds;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
	
	
	private float escalaSeleccionador;
	private MueveVista mueveVista;
	private Image seleccionador;
	private Image seleccionadorObjetivo;
	private Juego juego;
	CacheImagenes cacheImagenes = new CacheImagenes();

	private ArrayList<CallbackCasillero> callbacksClickeo;
	private ArrayList<CallbackCasillero> callbacksHover;
	
	private PosicionEnElPlano hovereando;
	
	
	private Image hovereador;
	
	private Actualizador actualizador;
	private Posicion posicionAtaque;
	private Image explosion;
	
	private ContenedorEmbellecedores embellecedores;
	
	
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
		
		actualizador=new Actualizador(this);
		actualizador.start();
		
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
		
		embellecedores=new ContenedorEmbellecedores();
		
		escalaSeleccionador = 1;
		
		pedirActualizacion();
	}
	
	
	
	//------------------------INPUT----------------------------------------------//
	public Casillero construirCasillero(Posicion pos){
		return juego.construirCasillero(pos);

	}

	private void clickea(Posicion p){
		if(juego.enTablero(p)){

			Casillero construido = construirCasillero(p);
			for(CallbackCasillero calls : callbacksClickeo){
				calls.execute(construido);
			}
		}
		pedirActualizacion();
	}
	
	private void hoverea(Posicion p){
		//Posicion p = mueveVista.obtenerPosicion(e);
		if(juego.enTablero(p)){
			Casillero construido = construirCasillero(p);
			for(CallbackCasillero calls : callbacksHover){
				calls.execute(construido);
			}
			
			if(construido.getuAerea()!=null){
				embellecedores.settearHovereado(construido.getuAerea());
			}else if(construido.getuTerrestre()!=null){
				embellecedores.settearHovereado(construido.getuTerrestre());
			}else{
				embellecedores.settearHovereado(null);
			}
		}
		pedirActualizacion();
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
	
	//------------------------DIBUJADO----------------------------------------------//
	public void pedirActualizacion(){
		
	}
	
	public void actualizar(){
		if(this.getParent()==null){
			actualizador.stop();
		}
		
		actualizacion();
		
	}

	public void actualizacion(){
		ArrayList<Unidad> unidades = juego.obtenerUnidades();
		embellecedores.revisarEmbellecedores(juego);
		embellecedores.detectarCambioPosicion();
		
		GraphicsContext gc = getGraphicsContext2D();
		gc.clearRect(0, 0, getWidth(), getHeight());
		
		//el fondo blanco
		gc.setFill(Color.WHITE);
		PosicionEnElPlano cero=new PosicionEnElPlano(0,0); 
		gc.fillRect(mueveVista.xPantalla(cero),
					mueveVista.yPantalla(cero),
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
		
		//halos
		dibujarHalos(gc);
		
		//el cuadrado seleccionado
		if(seleccionada != null){
			
			gc.drawImage(seleccionador,
				mueveVista.xPantalla(seleccionada)-(escalaSeleccionador-1)*mueveVista.anchoCasillero()/2,
				mueveVista.yPantalla(seleccionada)-(escalaSeleccionador-1)*mueveVista.altoCasillero()/2,
				mueveVista.anchoCasillero()*escalaSeleccionador,
				mueveVista.altoCasillero()*escalaSeleccionador
				);
		}
		
		//el cuadrado hovereando
		if(hovereando != null){
			synchronized(hovereando){
				gc.drawImage(hovereador,
						mueveVista.xPantalla(hovereando),
						mueveVista.yPantalla(hovereando),
						mueveVista.anchoCasillero(),
						mueveVista.altoCasillero());
				
			}
		}
		//el cartelito
		String accion = juego.accionPosibleEn(hovereando);
		if(accion!=""){
			Bounds bounds = (new Text(accion)).getLayoutBounds();
			double offsetX=20;
			double offsetY=20;
			gc.save();
			gc.setFill(Color.BEIGE);
			gc.fillRect(offsetX+mueveVista.getXMouse(), 
						offsetY+mueveVista.getYMouse(), 
						bounds.getWidth()+20, 
						bounds.getHeight()*1.5);
			gc.setFill(Color.BLACK);
			gc.fillText(accion, 
						offsetX+mueveVista.getXMouse()+10, 
						offsetY + mueveVista.getYMouse()+16);
			gc.restore();
		}
		
		
		//explosiones
		embellecedores.dibujarExplosiones(gc, mueveVista, cacheImagenes);
		//rangos
		dibujarRangos(gc);
	}
	public void dibujarHalos(GraphicsContext gc){
		if(halos != null && juego.enTablero(halos)){
			ArrayList<PosicionEnElPlano> haloMovimiento = juego.obtenerHaloMovimiento(halos);
			dibujarHalo(gc,haloMovimiento,Color.YELLOW);
			ArrayList<PosicionEnElPlano> haloAtaque = juego.obtenerHaloAtaque(halos);
			dibujarHalo(gc,haloAtaque,Color.RED);
		}
	}

	private void dibujarHalo(GraphicsContext gc, ArrayList<PosicionEnElPlano> halo, Color color) {
		gc.save();
		gc.setFill(color);
		gc.setGlobalAlpha(0.3);
		for(PosicionEnElPlano p : halo){
			gc.fillRect(mueveVista.xPantalla(p),
						mueveVista.yPantalla(p),
						mueveVista.anchoCasillero(),
						mueveVista.altoCasillero());
		}
		gc.restore();
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

				gc.drawImage(cacheImagenes.obtenerImagen(Chispa.nombreImagen()),
							mueveVista.xPantalla(juego.posicionChispa()), 
							mueveVista.yPantalla(juego.posicionChispa()), 
							mueveVista.anchoCasillero(),
							mueveVista.altoCasillero());

		
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
				embellecedores.dibujarUnidad(gc, u, juego, mueveVista, cacheImagenes);
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
		escalaSeleccionador = 1.3f;
		Timeline timeline = new Timeline();
		KeyValue keyE= new KeyValue(new EscribeEscalaSeleccionador(),1,Interpolator.EASE_IN);
		KeyFrame frameE = new KeyFrame(Duration.millis(80),keyE);
		timeline.getKeyFrames().add(frameE);
		timeline.playFromStart();
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
		pedirActualizacion();
	}
	
	//-----------------------------------mostrar halos de ataque/movimiento------//
	private Unidad mostrandoRangos;
	private Unidad halos;
	
	public void setHalos(Unidad halos){
		this.halos = halos;
	}
	
	
	//---------------------embelleciendo el seleccionador--------------------------//
	protected class EscribeEscalaSeleccionador implements WritableFloatValue{

		@Override
		public Number getValue() {
			return escalaSeleccionador;
		}

		@Override
		public float get() {
			return escalaSeleccionador;
		}

		@Override
		public void set(float arg0) {
			escalaSeleccionador= arg0;
		}

		@Override
		public void setValue(Number arg0) {
			escalaSeleccionador = (float) arg0;
		}
		
	}


	public void mostrarRangos(Unidad u) {
		mostrandoRangos = u;
	}
	
	public void dibujarRangos(GraphicsContext gc){
		if(mostrandoRangos!=null && juego.enTablero(mostrandoRangos)){
			dibujarRango(gc,
				mostrandoRangos.getDistanciaAtaque(),
				juego.posicion(mostrandoRangos),
				Color.RED);
			dibujarRango(gc,
				(int)mostrandoRangos.getMovimientosRestantes(),
				juego.posicion(mostrandoRangos),
				Color.YELLOW);
		}
	}
	
	public void dibujarRango(GraphicsContext gc, int distancia, Posicion centro, Color color){
		gc.save();
		gc.setStroke(color);
		gc.setLineWidth(5);
		
		Posicion arribaIzq = centro.obtenerMismaPosicionDesplazada(-distancia  , -distancia  );
		Posicion arribaDer = centro.obtenerMismaPosicionDesplazada( distancia+1, -distancia  );
		Posicion abajoIzq  = centro.obtenerMismaPosicionDesplazada(-distancia  ,  distancia+1);
		Posicion abajoDer  = centro.obtenerMismaPosicionDesplazada( distancia+1,  distancia+1);
		
		gc.beginPath();
		gc.moveTo(	mueveVista.xPantalla(arribaIzq), 
					mueveVista.yPantalla(arribaIzq));
		
		gc.lineTo(	mueveVista.xPantalla(arribaDer), 
					mueveVista.yPantalla(arribaDer));
		
		gc.lineTo(	mueveVista.xPantalla(abajoDer), 
					mueveVista.yPantalla(abajoDer));
		
		gc.lineTo(	mueveVista.xPantalla(abajoIzq), 
					mueveVista.yPantalla(abajoIzq));
		
		gc.lineTo(	mueveVista.xPantalla(arribaIzq), 
					mueveVista.yPantalla(arribaIzq));
		gc.stroke();
		
		gc.restore();

	}
}
