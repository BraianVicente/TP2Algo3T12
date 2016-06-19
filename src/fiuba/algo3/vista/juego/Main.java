package fiuba.algo3.vista.juego;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	FXMLLoader loader;
	Parent root;
	Scene scene;
	
	public void start(Stage stage, String name1, String name2) {
		
		try {
			loader = new FXMLLoader(getClass().getResource("Gameboard.fxml"));
			root = loader.load();
			
			GameboardController controller = loader.getController();
			controller.setPlayerNames(name1, name2);
			controller.setUp();
	    
	        scene = new Scene(root, 640, 400);
	    
	        stage.setTitle("Algoformers");
	        stage.setScene(scene);
	        stage.show();
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage scene) throws Exception {
		start(scene, "", "");
		
	}
}
