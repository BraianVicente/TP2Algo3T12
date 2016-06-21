package fiuba.algo3.vista.finalSplash;

import fiuba.algo3.vista.splash.Splash;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PlayAgainController implements EventHandler<ActionEvent> {
	
	private Button reference;
	
	public PlayAgainController(Button l) {
		reference = l;
	}
	
	@Override
	public void handle(ActionEvent ev) {
		try {
			Splash.class.newInstance().start(new Stage());
			Stage stage = (Stage) reference.getScene().getWindow();
			stage.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
