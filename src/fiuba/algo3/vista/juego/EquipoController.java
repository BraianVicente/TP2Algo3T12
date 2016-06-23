package fiuba.algo3.vista.juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EquipoController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent e) {
			Alert alert = new Alert(AlertType.INFORMATION,""
					+ "Este juego fue desarrollado por el equipo T12,\n"
					+ "para presentar en el curso de AyP3 del turno tarde, \n"
					+ "del primer cuatrimestre del a\u00F1o 2016."
					);
			alert.showAndWait();
	}
	

}
