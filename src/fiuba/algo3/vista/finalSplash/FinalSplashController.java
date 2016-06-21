package fiuba.algo3.vista.finalSplash;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FinalSplashController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nombreJugador;

    @FXML
    private Button playButton;

    @FXML
    void initialize() {
        assert nombreJugador != null : "fx:id=\"nombreJugador\" was not injected: check your FXML file 'Splash.fxml'.";
        assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'Splash.fxml'.";
        
        PlayAgainController controller = new PlayAgainController(playButton);
        playButton.setOnAction(controller);
    }
    
    public void setWinnerName(String name) {
    	nombreJugador.setText(name);
    }
}
