package fiuba.algo3.vista.bienvenida;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EscenaBienvenida extends Scene{
	Scene escenaSiguiente;
	Stage stage;
	public EscenaBienvenida(Scene escenaSiguiente, Stage stage, Parent contenedor){
		super(contenedor, 640,800);
		this.escenaSiguiente=escenaSiguiente;
		this.stage=stage;
	}
}
