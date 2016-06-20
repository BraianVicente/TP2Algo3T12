package fiuba.algo3.vista.juego;

import java.net.URL;

import java.util.Random;
import java.util.ResourceBundle;
import fiuba.algo3.vista.statsPane.StatsPane ;

import fiuba.algo3.controlador.ClickedUnitManager;
import fiuba.algo3.controlador.CombinarController;
import fiuba.algo3.controlador.GameController;
import fiuba.algo3.controlador.MoverController;
import fiuba.algo3.controlador.TransformarController;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;

import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.unidades.*;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.Casillero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private Button finTurnoButton;
    
    @FXML
    private Button transformarButton;
    
    @FXML
    private Button combinarButton;
    
    @FXML
    private ChoiceBox<String> vistaChoiceBox;
    
    @FXML
    private ImageView jugandoImage;

    @FXML
    private AnchorPane StatsPane;

    @FXML
    private TextField unidadNombre ;

    @FXML
    private ImageView unidadImagen ;

    @FXML
    private ProgressBar vidaUnidad ;

    @FXML
    private TextField ataqueUnidad ;

    @FXML
    
    private TextField tieneChispa ;
    
    @FXML
    private ImageView terrestreView ;

    @FXML
    private TextArea terrestreEfectos ;

    @FXML
    private ImageView aereoView ;

    @FXML
    private TextArea aereoEfectos ;
    

    
    private Tablero tablero;
    private Juego juego;
    
    private String namePlayer1 = "";
    private String namePlayer2 = "";
    
    private GameController controller;
    private ClickedUnitManager manager;
    MoverController mover;
    
    Unidad[] unitList = {
    		new Optimusprime(),
    		new Bumblebee(),
    		new Ratchet(),
    		
    		new Megatron(),
    		new Bonecrusher(),
    		new Frenzy()
    };
    
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
    	tablero = new Tablero(6, 6);
    	
		juego = new Juego(tablero,new Jugador(namePlayer1, new Autobots(),tablero),new Jugador(namePlayer2, new Decepticons(),tablero));
		setUpUnits(juego);
		
		setJugandoImage(juego.jugadorEnTurno().getEquipo());
		
		CanvasJuego cj = new CanvasJuego(juego);
		
		manager = new ClickedUnitManager();
		controller = new GameController(juego, manager, cj);
		mover = new MoverController(juego);
		
		cj.agregarCallbackClickeo(manager);
		cj.agregarCallbackClickeo(mover);
    	
    	GamePane.getChildren().add(cj);
    	
    	cj.agregarCallbackClickeo(c->juego.clickeoCasillero(c,cj));
    	cj.agregarCallbackHover(c->hovereoCasillero(c));
		
		ChoiceBoxController cbc = new ChoiceBoxController(vistaChoiceBox, cj);
		vistaChoiceBox.setOnAction(cbc);
		
		FinTurnoController ftc = new FinTurnoController(juego, this);
        finTurnoButton.setOnAction(ftc);
        
        TransformarController tc = new TransformarController(controller);
        transformarButton.setOnAction(tc);
        
        CombinarController cc = new CombinarController(controller);
        combinarButton.setOnAction(cc);

    }
    
    public void hovereoCasillero(Casillero c){
    	if(!c.isEmpty()){
            this.setUnidadSeleccionada(c.getUnidad());
        }
        this.setSuperficieAerea(c.getsAerea());
        this.setSuperficieTerrestre(c.getsTerrestre());
    
    }
    
    
    public void setUpUnits(Juego juego) {
    	Random generator = new Random();
    	Posicion pos = null;
    	
    	for (Unidad unit: unitList) {
    		while ((pos == null) || (!juego.posicionVacia(pos)))
    			pos = new Posicion(generator.nextInt(6), generator.nextInt(6), unit.getPlanoPerteneciente());
    		juego.agregarUnidad(pos, unit);
    	}
    	
    	unitList = null;
    }

    
    public void setUnidadSeleccionada(Unidad u){
        this.unidadImagen(u);
        this.vidaUnidad(u);
        this.unidadNombre(u);
        this.ataqueUnidad(u);
        this.tieneChispa(u);
    }
    
    public void setUnidadSeleccionada(Posicion p){
        Unidad u = this.tablero.obtenerUnidad(p);
        this.setUnidadSeleccionada(u);
    }
    
    public void setSuperficieTerrestre(Superficie s){
        this.terrestreView(s);
        this.terrestreEfectos(s);
        
    }
    
    public void setSuperficieAerea(Superficie s){
        this.aereoView(s);
        this.aereoEfectos(s);
    }
    
    private void unidadNombre(Unidad unidad) {
        this.unidadNombre.setText(unidad.nombre());
    }

    private void unidadImagen(Unidad u) {
        this.unidadImagen.setImage(new Image(u.nombreImagen()));
    }

    private void vidaUnidad(Unidad u) {
        this.vidaUnidad.setProgress((u.getVida())/u.getVidaMaxima());
    }

    private void ataqueUnidad(Unidad u) {
        Integer ataq = u.getPuntosAtaque();
        this.ataqueUnidad.setText(ataq.toString());
            
    }

    private void terrestreView(Superficie s) {
        this.terrestreView.setImage(new Image(s.nombreImagen()));
    }

    private void terrestreEfectos(Superficie s) {
        this.terrestreEfectos.setText(s.efecto());
    }

    private void aereoView(Superficie s) {
        this.aereoView.setImage(new Image(s.nombreImagen()));
    }

    private void aereoEfectos(Superficie s) {
        this.aereoEfectos.setText(s.efecto());
    }

    
    private void tieneChispa(Unidad u) {
        if(u.tieneChispa()){
            this.tieneChispa.setText("Si");
        }
        this.tieneChispa.setText("No");
        
    }


}
