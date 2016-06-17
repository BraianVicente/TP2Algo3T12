package fiuba.algo3.vista.juego;

import java.net.URL;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class GameboardControler {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane GamePane;
    
    private Tablero tablero;
    private Juego juego;

    @FXML
    void initialize() {
        assert GamePane != null : "fx:id=\"GamePane\" was not injected: check your FXML file 'MainJuego.fxml'.";
        
		tablero = new Tablero();
		juego=new Juego(tablero,new Jugador("auto", new Autobots()),new Jugador("dece", new Decepticons()));
		juego.agregarUnidad(new Posicion(8,8,Plano.TERRESTRE), new Optimusprime());
		juego.agregarUnidad(new Posicion(0,0,Plano.AEREO), new Megatron());
		
		CanvasJuego cj = new CanvasJuego(juego);
		
		GamePane.getChildren().add(cj);
    }
}
