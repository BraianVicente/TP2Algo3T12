package fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CombinarController implements EventHandler<ActionEvent> {
	
	private GameController controller;
	
	public CombinarController(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void handle(ActionEvent event) {
		controller.combinarUnidades();
	}

}
