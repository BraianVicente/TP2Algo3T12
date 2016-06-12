package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Transformer;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TeclaEnCanvasEventHandler implements EventHandler<KeyEvent>{
		Posicion posActual;
		Juego juego;
		
    public TeclaEnCanvasEventHandler(Posicion seleccionada,Juego juego) {
    	this.posActual=seleccionada;
    	this.juego=juego;
    }

    @Override
	public void handle(KeyEvent e) {
		System.out.println(" Codigo: "+e.getCode());
        if (e.getCode() == KeyCode.Q)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(-1,-1));
          
        
        if (e.getCode() == KeyCode.W)   	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(0,-1));
      
        
        if (e.getCode() == KeyCode.E)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(1,-1));
        if (e.getCode() == KeyCode.D)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(1,0));
        if (e.getCode() == KeyCode.A)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(-1,0));
        if (e.getCode() == KeyCode.Z)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(-1,1));
        if (e.getCode() == KeyCode.X)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(0,1));
        if (e.getCode() == KeyCode.C)     	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(1,1));
        if (e.getCode() == KeyCode.T)     	((Transformer) juego.obtenerUnidad(posActual)).transformar();
        if (e.getCode() == KeyCode.P)     	juego.avanzarTurno();
        
	}
}
