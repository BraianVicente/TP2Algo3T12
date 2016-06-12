package fiuba.algo3.controlador;

import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TeclaEnCanvasEventHandler  implements EventHandler<KeyEvent>{
		Tablero tablero;
		Unidad unidad;
    public TeclaEnCanvasEventHandler(Tablero tab,Unidad uni) {
        tablero=tab;
        unidad=uni;
    }

    @Override
    public void handle(KeyEvent event) {

        if (event.getCode() == KeyCode.Q) {
            tablero.mover(unidad,tablero.posicion(unidad).obtenerMis);
        }

    }
}
