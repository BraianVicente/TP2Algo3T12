/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.vista.statsPane;

import static javafx.application.Application.launch;

/**
 *
 * @author brahvic
 */
	
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;


public class Main extends Application {
	
	FXMLLoader loader;
	Parent root;
	Scene scene;
	
    @Override
	public void start(Stage stage) throws Exception{
		
		try {
			loader = new FXMLLoader(getClass().getResource("StatsPane.fxml"));
			root = loader.load();
			
			StatsPane controller = loader.getController();
            controller.setUp();
            scene = new Scene(root, 130, 370);
	    
	        stage.setTitle("StatsPane");
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
