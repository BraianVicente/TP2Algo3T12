package fiuba.algo3.vista.CanvasJuego;

import fiuba.algo3.modelo.posicion.Posicion;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableFloatValue;
import javafx.beans.value.WritableIntegerValue;
import javafx.event.ActionEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Explosion {
	private int cuadro;
	public Posicion posicion;
	private CallbackFinAnimacion callbackFin;
	public Explosion(Posicion posicion,CallbackFinAnimacion callbackFin){
		Timeline timeline = new Timeline();
		cuadro = 1;
		KeyValue key= new KeyValue(new EscribeCuadro(this),9);
		KeyFrame frame = new KeyFrame(Duration.millis(800),key);
		timeline.getKeyFrames().add(frame);
		timeline.setOnFinished(e->finAnimacion(e));
		timeline.playFromStart();
		this.posicion=posicion;
		this.callbackFin=callbackFin;
	}
	
	//public void dibujar
	
	private void finAnimacion(ActionEvent e) {
		callbackFin.fin(this);
	}

	private Image actual(CacheImagenes cache){
		return cache.obtenerImagen(CacheImagenes.cuadroExplosion(cuadro));
		
	}
	
	protected class EscribeCuadro implements WritableIntegerValue{
		private Explosion explosion;
		public EscribeCuadro(Explosion explosion){
			this.explosion = explosion;
		}
		@Override
		public Number getValue() {
			return explosion.cuadro;
		}
		@Override
		public int get() {
			return explosion.cuadro;
		}
		@Override
		public void set(int arg0) {
			explosion.cuadro = arg0;
			
		}
		@Override
		public void setValue(Number arg0) {
			explosion.cuadro = (int) arg0;
		}

		
	}

	public void dibujarEn(GraphicsContext gc, MueveVista mueveVista, CacheImagenes cacheImagenes) {
		Image img = actual(cacheImagenes); 
		gc.drawImage(img,
				mueveVista.xPantalla(posicion.getX()-0.5),
				mueveVista.yPantalla(posicion.getY()-0.5),
				mueveVista.anchoCasillero()*2,
				mueveVista.altoCasillero()*2
			);
		
	}
}
