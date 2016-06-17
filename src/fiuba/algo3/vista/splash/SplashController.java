package fiuba.algo3.vista.splash;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SplashController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField namePlayer2;

    @FXML
    private TextField namePlayer1;

    @FXML
    private Button playButton;

    @FXML
    void initialize() {
        assert namePlayer2 != null : "fx:id=\"namePlayer2\" was not injected: check your FXML file 'Splash.fxml'.";
        assert namePlayer1 != null : "fx:id=\"namePlayer1\" was not injected: check your FXML file 'Splash.fxml'.";
        assert playButton != null : "fx:id=\"playButton\" was not injected: check your FXML file 'Splash.fxml'.";
        
        PlayController pc = new PlayController(namePlayer1, namePlayer2);
        playButton.setOnAction(pc);
    }
}
