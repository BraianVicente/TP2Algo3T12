package fiuba.algo3.controlador;

import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.Casillero;

public class ControladorMiraGonzalo {
	//el controlador va a conocer a los botones y va a estar lleno 
	//de eventos, por ahora no hay botones visuales así q lo ideal 
	//es mappear todo al teclado por ahora.
	
	ControladorMiraGonzalo(CanvasJuego canvas){//tb el Juego... (aprovechalo que Braian le metió cosas lindas)
		canvas.agregarCallback(c->seleccionCasillero(c));
	}
	
	private Object seleccionCasillero(Casillero c) {
		// si no se seleccionó ninguna unidad, c.getUnidad() es null
		// TODO acá habría que pasarle la superficie a algo de 
		//la vista que te muestre sus propiedades, no lo tuvimos en cuenta
		return null;
	}
}
