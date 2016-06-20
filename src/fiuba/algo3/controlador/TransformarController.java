package fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TransformarController implements EventHandler<ActionEvent> {
	
	private GameController controller;
	
	public TransformarController(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void handle(ActionEvent ae) {
		controller.transformarUnidad();
	}

}
