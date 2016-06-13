package fiuba.algo3.vista;

	
import java.io.IOException;

import fiuba.algo3.controlador.TeclaEnCanvasEventHandler;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.bienvenida.EscenaBienvenida;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {

		Tablero tablero = new Tablero();
		Juego juego=new Juego(tablero,new Jugador("auto", new Autobots()),new Jugador("dece", new Decepticons()));
		juego.agregarUnidad(new Posicion(8,8,Plano.TERRESTRE), new Optimusprime());
		juego.agregarUnidad(new Posicion(0,0,Plano.AEREO), new Megatron());
		BorderPane contenedor = new BorderPane();
		CanvasJuego canvas = new CanvasJuego(juego);

		
		contenedor.setCenter(canvas);
		Scene escenaJuego = new Scene( contenedor, 640, 400);
//		BorderPane contenedorBienvenida=new BorderPane();
//		BackgroundImage imagenFondo=new BackgroundImage(new Image("/fiuba/algo3/vista/bienvenida/fondo.png"), null, null, null, null);
//		Background fondo=new Background(imagenFondo);
//		Button boton =new Button();
//		boton.setText("Jugar");
//		contenedorBienvenida.setCenter(boton);
//		EscenaBienvenida bienvenida=new EscenaBienvenida(escenaJuego,stage, contenedorBienvenida);
		
		stage.setTitle("Algoformers");
		stage.setScene(escenaJuego);
		stage.show();
		stage.setFullScreenExitHint("");
		stage.setFullScreen(true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
