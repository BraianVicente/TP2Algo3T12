package fiuba.algo3.vista.CanvasJuego;

import java.util.ArrayList;
import java.util.Timer;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;

public class CanvasJuego extends Canvas implements Actualizable{
	MueveVista mueveVista;
	Tablero tablero;
	public CanvasJuego(Tablero tablero){
		super(800,600);
		mueveVista = new MueveVista(800,600);
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, e->mueveVista.presionado(e));
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, e->mueveVista.soltado(e));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e->mueveVista.salio(e));
		this.addEventHandler(ScrollEvent.SCROLL, e->mueveVista.scrolleado(e));
		this.setOnMouseDragged(e->mueveVista.movido(e));
		
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e->movido(e));
		Timer timer = new Timer();
		timer.schedule(new Actualizador(this), 0, 33);
		
		this.tablero = tablero;
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
	}
	
	public void movido(MouseEvent e){
		GraphicsContext gc =getGraphicsContext2D();
		gc.strokeText("holaaa!"+mueveVista.getX()+","+mueveVista.getY(), e.getX(), e.getY());
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
