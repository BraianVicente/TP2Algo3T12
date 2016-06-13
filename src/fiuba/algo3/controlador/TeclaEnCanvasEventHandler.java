package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TeclaEnCanvasEventHandler implements EventHandler<KeyEvent>{
		private Posicion posActual;
		private Juego juego;
		private CanvasJuego canvas;

		
    public TeclaEnCanvasEventHandler(Posicion seleccionada,Juego juego,CanvasJuego canvas) {
    	this.posActual=seleccionada;
    	this.juego=juego;
    	this.canvas=canvas;
    }

    @Override
	public void handle(KeyEvent e) {
    	try{
        if (e.getCode() == KeyCode.Q)  {
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(-1,-1));
        	posActual=posActual.obtenerMismaPosicionDesplazada(-1,-1);
        }
          
        
        if (e.getCode() == KeyCode.W)   	{
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(0,-1));
        	posActual=posActual.obtenerMismaPosicionDesplazada(0,-1);
        }
      
        
        if (e.getCode() == KeyCode.E)     	{
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(1,-1));
        	posActual=posActual.obtenerMismaPosicionDesplazada(1,-1);
        }
        if (e.getCode() == KeyCode.D)     	{
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(1,0));
        	posActual=posActual.obtenerMismaPosicionDesplazada(1,0);
        }
        if (e.getCode() == KeyCode.A){
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(-1,0));
        	posActual=posActual.obtenerMismaPosicionDesplazada(-1,0);
        }
        if (e.getCode() == KeyCode.Z) {
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(-1,1));
        	posActual=posActual.obtenerMismaPosicionDesplazada(-1,1);
        }
        if (e.getCode() == KeyCode.X){
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(0,1));
        	posActual=posActual.obtenerMismaPosicionDesplazada(0,1);
        }
        if (e.getCode() == KeyCode.C) {
        	juego.moverUnidad(posActual, posActual.obtenerMismaPosicionDesplazada(1,1));
        	posActual=posActual.obtenerMismaPosicionDesplazada(1,1);
        }
        if (e.getCode() == KeyCode.T) {
        	Unidad unidad=juego.obtenerUnidad(posActual);
        	juego.transformarUnidad(posActual);
        	posActual=juego.obtenerPosicion(unidad);
        }
        if (e.getCode() == KeyCode.P)     	juego.avanzarTurno();
        if (e.getCode() == KeyCode.UP)    	posActual=posActual.nuevaPosicionConDistintoPlano(Plano.AEREO);
        if (e.getCode() == KeyCode.DOWN)    	posActual=posActual.nuevaPosicionConDistintoPlano(Plano.TERRESTRE);  
        canvas.selecciona(posActual);}
    	catch(RuntimeException e2){
    		Alert alert = new Alert(AlertType.ERROR,"Lo que trato de realizar es contra las reglas.\n"
    				+ " Posibles motivos:\n"
    				+ "	No se puede transformar porque la otra posicion esta ocupada\n"
    				+ "	No se puede mover\n"
    				+ "	Hay otra unidad en la linea de ataque\n"
    				+ "	La unidad que quiere atacar o utilizar no corresponde al equipo debido");
    		alert.showAndWait();
    	}
	}
    public void cambiarSeleccionada(Posicion seleccionada){
    	this.posActual=seleccionada;
    }
}
