package fiuba.algo3.vista.juego;

import fiuba.algo3.vista.splash.Splash;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class VolverMenuController implements EventHandler<ActionEvent>{

private Stage reference;
	
	public VolverMenuController(Stage stage) {
		reference = stage;
	}
	
	@Override
	public void handle(ActionEvent ev) {
		try {
			Splash.class.newInstance().start(new Stage());
			reference.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
