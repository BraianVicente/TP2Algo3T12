package fiuba.algo3.vista.finalSplash;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FinalSplash extends Application {

	public void start(Stage stage, String winner) throws Exception {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FinalSplash.fxml")); 
			Parent root = loader.load();
			
			FinalSplashController controller = loader.<FinalSplashController>getController();
			controller.setWinnerName(winner);
	    
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

	@Override
	public void start(Stage stage) throws Exception {
		start(stage, "Debug");
	}

}
