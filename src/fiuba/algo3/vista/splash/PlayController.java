package fiuba.algo3.vista.splash;

import fiuba.algo3.vista.juego.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayController implements EventHandler<ActionEvent> {

	private TextField namePlayer1;
	private TextField namePlayer2;
	
	public PlayController(TextField player1, TextField player2) {
		namePlayer1 = player1;
		namePlayer2 = player2;
	}
	
	@Override
	public void handle(ActionEvent ev) {
		try {
			Main.class.newInstance().start(new Stage(), namePlayer1.getText(), namePlayer2.getText());
			Stage stage = (Stage) namePlayer1.getScene().getWindow();
			stage.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
