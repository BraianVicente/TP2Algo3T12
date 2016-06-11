package fiuba.algo3.vista;

	
import java.io.IOException;

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
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		Tablero tablero = new Tablero();
		tablero.agregarUnidad(new Posicion(8,8), new Bumblebee());
		
		tablero.agregarUnidad(new Posicion(0,0), new Megatron());
		BorderPane contenedor = new BorderPane();
		CanvasJuego canvas = new CanvasJuego(tablero);
		contenedor.setCenter(canvas);
		
		Scene scene = new Scene(contenedor, 640, 400);
		stage.setTitle("Algoformers");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
