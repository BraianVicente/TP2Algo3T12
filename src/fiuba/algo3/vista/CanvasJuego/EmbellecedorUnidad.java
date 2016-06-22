package fiuba.algo3.vista.CanvasJuego;


import java.util.ArrayList;

import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableFloatValue;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.util.Duration;
public class EmbellecedorUnidad{
	private Unidad unidad;
	private Juego juego;
	private Posicion ultimaPosicion;
	
	private float x;
	private float y;
	private int vidaAnterior;
	private float shake;
	private boolean hovereando;
	
	public EmbellecedorUnidad(Unidad u,Juego juego){
		unidad = u;
		this.juego = juego;
		vidaAnterior = unidad.getVida();
		shake = 0;
		hovereando = false;
		ultimaPosicion=null;
	}
	
	public void setHovereando(boolean hovereando){
		this.hovereando= hovereando;
	}
	
	public void detectarCambio(){
		if(juego.enTablero(unidad)){
			Posicion p = juego.obtenerPosicion(unidad);
			ultimaPosicion=p;
			
			Timeline timeline = new Timeline();
			KeyValue keyX= new KeyValue(escribeX(),p.getX());
			KeyValue keyY= new KeyValue(escribeY(),p.getY());
			KeyFrame frameX = new KeyFrame(Duration.millis(200),keyX);
			KeyFrame frameY = new KeyFrame(Duration.millis(200),keyY);
			
			if(vidaAnterior!=unidad.getVida()){
				shake = 0.1f;
				vidaAnterior=unidad.getVida();
				KeyValue keyShake= new KeyValue(escribeShake(),0);
				KeyFrame frameShake = new KeyFrame(Duration.millis(500),keyShake);
				timeline.getKeyFrames().add(frameShake);
			}
			
			
			
			timeline.getKeyFrames().add(frameX);
			timeline.getKeyFrames().add(frameY);
			
			timeline.playFromStart();
		}
	}
	
	
	
	
	
	//--------------------------------clases para tweening------------------------//
	public EscribeX escribeX(){
		return new EscribeX(this);
	}
	
	public EscribeShake escribeShake(){
		return new EscribeShake(this);
	}
	
	public EscribeY escribeY(){
		return new EscribeY(this);
	}
	
	protected class EscribeX implements WritableFloatValue{
		private EmbellecedorUnidad embellecedor;
		public EscribeX(EmbellecedorUnidad embellecedor){
			this.embellecedor = embellecedor;
		}

		@Override
		public Number getValue() {
			return embellecedor.x;
		}

		@Override
		public float get() {
			return embellecedor.x;
		}

		@Override
		public void set(float arg0) {
			embellecedor.x = arg0;
		}

		@Override
		public void setValue(Number arg0) {
			embellecedor.x = (float) arg0;
		}
		
	}
	
	protected class EscribeShake implements WritableFloatValue{
		private EmbellecedorUnidad embellecedor;
		public EscribeShake(EmbellecedorUnidad embellecedor){
			this.embellecedor = embellecedor;
		}

		@Override
		public Number getValue() {
			return embellecedor.shake;
		}

		@Override
		public float get() {
			return embellecedor.shake;
		}

		@Override
		public void set(float arg0) {
			embellecedor.shake = arg0;
		}

		@Override
		public void setValue(Number arg0) {
			embellecedor.shake = (float) arg0;
		}
		
	}
	
	protected class EscribeY implements WritableFloatValue{
		private EmbellecedorUnidad embellecedor;
		public EscribeY(EmbellecedorUnidad embellecedor){
			this.embellecedor = embellecedor;
		}

		@Override
		public Number getValue() {
			return embellecedor.y;
		}

		@Override
		public float get() {
			return embellecedor.y;
		}

		@Override
		public void set(float arg0) {
			embellecedor.y = arg0;
		}

		@Override
		public void setValue(Number arg0) {
			embellecedor.y = (float) arg0;
		}
		
	}

	public void dibujar(GraphicsContext gc, MueveVista mueveVista, CacheImagenes cacheImagenes) {
		if(juego.enTablero(unidad)){
			gc.save();
			Posicion p = juego.posicion(unidad);
			try{
				double extraHovereando = 0;
				if(hovereando) extraHovereando =  (mueveVista.anchoCasillero()*0.3f);
				
				
				ArrayList<String> nombres_imagenes = unidad.obtenerNombresImagenesModificadores();
				gc.save();
				gc.setGlobalAlpha(0.5f/nombres_imagenes.size());
				for(String nombre : nombres_imagenes ){
					if(nombre != null){
						Image imgM = cacheImagenes.obtenerImagen(nombre);
						gc.drawImage(imgM,
								mueveVista.xPantalla(x)-extraHovereando/2, 
								mueveVista.yPantalla(y)-extraHovereando/2, 
								mueveVista.anchoCasillero()+extraHovereando,
								mueveVista.altoCasillero()+extraHovereando
								);
					}
				}
				gc.restore();
				
				Image imgU = cacheImagenes.obtenerImagen(unidad.nombreImagen());
				
				
				ColorAdjust agrisar = new ColorAdjust();
				agrisar.setSaturation(-0.6);
				agrisar.setContrast(-0.4);
				if(!juego.puedeHacerAlgo(unidad)){
					gc.setEffect(agrisar);
				}
				
				gc.drawImage(imgU,
						mueveVista.xPantalla(x+Math.random()*shake*2-shake)-extraHovereando/2,
						mueveVista.yPantalla(y+Math.random()*shake*2-shake)-extraHovereando/2,
						mueveVista.anchoCasillero()+extraHovereando,
						mueveVista.altoCasillero()+extraHovereando
						);
				
			}
			catch(ImagenInexistenteException e){}
			gc.restore();
		}
	}

	public Posicion getUltimaPosicion() {
		return ultimaPosicion;
	}
	
	
	
}
