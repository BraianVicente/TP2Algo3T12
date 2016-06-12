package fiuba.algo3.vista;

	
import java.io.IOException;

import fiuba.algo3.controlador.TeclaEnCanvasEventHandler;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Tablero tablero = new Tablero();
		Juego juego=new Juego(tablero,new Jugador("auto", new Autobots()),new Jugador("dece", new Decepticons()));
		tablero.agregarUnidad(new Posicion(8,8), new Bumblebee());
		
		tablero.agregarUnidad(new Posicion(0,0), new Megatron());
		BorderPane contenedor = new BorderPane();
		CanvasJuego canvas = new CanvasJuego(tablero,juego);

		
		contenedor.setCenter(canvas);
		Scene scene = new Scene(contenedor, 640, 400);
		stage.setTitle("Algoformers");
		stage.setScene(scene);
		stage.show();
		stage.setFullScreen(true);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
