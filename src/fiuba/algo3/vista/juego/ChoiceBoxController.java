package fiuba.algo3.vista.juego;

import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.ModoVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceBox;

public class ChoiceBoxController implements EventHandler<ActionEvent> {

	private ChoiceBox<String> vista;
	private CanvasJuego cj;
	
	public ChoiceBoxController(ChoiceBox<String> cb, CanvasJuego cj) {
		vista = cb;
		this.cj = cj;
	}
	
	@Override
	public void handle(ActionEvent ev) {
		System.out.println("ModoVista Changed! " + vista.getValue());
		if (vista.getValue().equals("Tierra")) {
			cj.setModoVista(ModoVista.SOLOTIERRA);
		} else if (vista.getValue().equals("Aire")) {
			cj.setModoVista(ModoVista.SOLOAIRE);
		} else if (vista.getValue().equals("Ambas")) {
			cj.setModoVista(ModoVista.AMBAS);
		}
	}

}
