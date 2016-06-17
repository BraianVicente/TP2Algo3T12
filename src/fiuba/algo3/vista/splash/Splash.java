package fiuba.algo3.vista.splash;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Splash extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Splash.fxml"));
	    
	        Scene scene = new Scene(root, 640, 400);
	    
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

}
