package fiuba.algo3.vista.juego;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ComoJugarController implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent e) {
		Alert alert = new Alert(AlertType.INFORMATION,""
				+ "Si quiere transformar, primero seleccione la unidad y luego presione el boton transformar\n"
				+ "Si quiere moverse, primero seleccione la unidad y luego presione el lugar a donde desea moverse\n"
				+ "Si quiere atacar, primero seleccione la unidad atacante y luego presione el lugar a donde se encuentra el objetivo\n"
				+ "Si quiere combinar las unidades deben estar a una distancia de 2 casilleros como maximo entre si, para hacerlo presione el boton combinar.\n"
				+ "Si quiere seleccionar una unidad voladora, y hay dos unidades en el mismo casillero primero seleccione el modo de vista Cielo y luego seleccione.\n"
				);
		alert.showAndWait();
	}

}
