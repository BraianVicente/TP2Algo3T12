package fiuba.algo3.vista.juego;

import fiuba.algo3.vista.splash.Splash;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CerrarController implements EventHandler<ActionEvent>{
	

private Stage reference;
	
	public CerrarController(Stage stage) {
		reference = stage;
	}
	
	@Override
	public void handle(ActionEvent ev) {
		try {
			reference.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
