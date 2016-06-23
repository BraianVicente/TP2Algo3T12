package fiuba.algo3.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CombinarController implements EventHandler<ActionEvent> {
	
	private GameController controller;
	
	public CombinarController(GameController controller) {
		this.controller = controller;
	}
	
	@Override
	public void handle(ActionEvent event) {
		try{
			controller.combinarUnidades();
		}catch(RuntimeException e){
			Alert alert = new Alert(AlertType.ERROR,""
					+ "COMBINACION INVALIDA\n"
					+ "Esto puede deberse a que usted no posee 3 unidades vivas o\n"
					+ "estas no se encuentran a la distancia correcta(debe ser 2 o menos casilleros).\n"
					);
			alert.showAndWait();
		}
	}

}
