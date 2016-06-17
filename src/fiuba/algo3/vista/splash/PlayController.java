package fiuba.algo3.vista.splash;

import fiuba.algo3.vista.juego.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class PlayController implements EventHandler<ActionEvent> {

	private TextField namePlayer1;
	private TextField namePlayer2;
	
	public PlayController(TextField player1, TextField player2) {
		namePlayer1 = player1;
		namePlayer2 = player2;
	}
	
	@Override
	public void handle(ActionEvent ev) {
		Application.launch(Main.class, namePlayer1.getText(), namePlayer2.getText());
	}

}
