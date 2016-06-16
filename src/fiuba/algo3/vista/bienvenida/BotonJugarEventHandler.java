package fiuba.algo3.vista.bienvenida;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonJugarEventHandler implements EventHandler<ActionEvent>{
		Stage stage;
		Scene siguienteEscena;

	    public BotonJugarEventHandler(Stage stage, Scene siguienteEscena) {
	        this.stage = stage;
	        this.siguienteEscena = siguienteEscena;
	    }

	    @Override
	    public void handle(ActionEvent actionEvent) {
	    	stage.hide();
	       stage.setScene(siguienteEscena);
	       stage.show();
	       stage.setFullScreenExitHint("");
	       stage.setFullScreen(true);
	       }
}
