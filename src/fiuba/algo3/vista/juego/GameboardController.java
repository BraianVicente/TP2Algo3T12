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
import fiuba.algo3.vista.CanvasJuego.ModoVista;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
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
    private ChoiceBox<String> vistaChoiceBox;
    
    @FXML
    private ImageView jugandoImage;
    
    private Tablero tablero;
    private Juego juego;
    
    private String namePlayer1 = "";
    private String namePlayer2 = "";
    
    @FXML
    void initialize() {
        assert GamePane != null : "fx:id=\"GamePane\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert finTurnoButton != null : "fx:id=\"finTurnoButton\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert transformarButton != null : "fx:id=\"transformarButton\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert combinarButton != null : "fx:id=\"combinarButton\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert StatsPane != null : "fx:id=\"StatsPane\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert vistaChoiceBox != null : "fx:id=\"vistaChoiceBox\" was not injected: check your FXML file 'MainJuego.fxml'.";
        assert jugandoImage != null : "fx:id=\"jugandoImage\" was not injected: check your FXML file 'MainJuego.fxml'.";
		
    }
    
    public void setJugandoImage(Equipo equipo) {
    	jugandoImage.setImage(new Image("fiuba/algo3/vista/imagenes/equipos/" + equipo.toString() + ".png"));
    }
    
    public void setPlayerNames(String name1, String name2) {
    	namePlayer1 = name1;
    	namePlayer2 = name2;
    }
    
    public void setUp() {    	
    	tablero = new Tablero();
		juego=new Juego(tablero,new Jugador(namePlayer1, new Autobots()),new Jugador(namePlayer2, new Decepticons()));
		juego.agregarUnidad(new Posicion(8,8,Plano.TERRESTRE), new Optimusprime());
		juego.agregarUnidad(new Posicion(0,0,Plano.AEREO), new Megatron());
		
		setJugandoImage(juego.jugadorEnTurno().getEquipo());
		
		CanvasJuego cj = new CanvasJuego(juego);
    	
    	GamePane.getChildren().add(cj);
		
		ChoiceBoxController cbc = new ChoiceBoxController(vistaChoiceBox, cj);
		vistaChoiceBox.setOnAction(cbc);
		
		finTurnoController ftc = new finTurnoController(juego, this);
        finTurnoButton.setOnAction(ftc);
    }
}
