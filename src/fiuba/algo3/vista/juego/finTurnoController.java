package fiuba.algo3.vista.juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class finTurnoController implements EventHandler<ActionEvent> {
	
	private Button finTurnoButton;
	
	public finTurnoController(Button b) {
		finTurnoButton = b;
	}
	
	@Override
	public void handle(ActionEvent ae) {
		// Avisar al Juego que termino el turno
	}

}
