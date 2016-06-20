package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;

public class GameController {
	
	private Juego juego;
	
	public GameController(Juego juego, CanvasJuego cj) {
		this.juego = juego;
	}
	
	public void transformarUnidad(Posicion pos) {
		Unidad unit = juego.obtenerUnidad(pos);
		if (unit.sePuedeTransformar())
			juego.transformarUnidad(pos);
	}
	
}
