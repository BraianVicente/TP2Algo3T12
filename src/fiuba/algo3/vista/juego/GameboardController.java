package fiuba.algo3.vista.juego;

import java.net.URL;
import java.util.ResourceBundle;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GameboardController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane GamePane;
    
    @FXML
    private AnchorPane StatsPane;
    
    @FXML
    private Button finTurnoButton;
    
    @FXML
    private Button transformarButton;
    
    @FXML
    private Button combinarButton;
    
    @FXML
    private ImageView jugandoImage;
    
    private Tablero tablero;
    private Juego juego;

    @FXML
    void initialize() {
        assert GamePane != null : "fx:id=\"GamePane\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert finTurnoButton != null : "fx:id=\"finTurnoButton\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert transformarButton != null : "fx:id=\"transformarButton\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert combinarButton != null : "fx:id=\"combinarButton\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert StatsPane != null : "fx:id=\"StatsPane\" was not injected: check your FXML file 'MainJuego.fxml'.";
        
        finTurnoController ftc = new finTurnoController(finTurnoButton);
        finTurnoButton.setOnAction(ftc);
        
		tablero = new Tablero();
		// TODO: Get player names
		juego=new Juego(tablero,new Jugador("auto", new Autobots()),new Jugador("dece", new Decepticons()));
		juego.agregarUnidad(new Posicion(8,8,Plano.TERRESTRE), new Optimusprime());
		juego.agregarUnidad(new Posicion(0,0,Plano.AEREO), new Megatron());
		
		setJugandoImage(juego.jugadorEnTurno().getEquipo());
		
		CanvasJuego cj = new CanvasJuego(juego);
		
		GamePane.getChildren().add(cj);
    }
    
    public void setJugandoImage(Equipo equipo) {
    	//jugandoImage.setImage(new Image("fiuba/algo3/vista/imagenes/equipos/" + equipo.toString() + ".jpg"));
    }
}
