package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;

public class GameController {
	
	private Juego juego;
	private ClickedUnitManager manager;
	
	public GameController(Juego juego, ClickedUnitManager manager) {
		this.juego = juego;
		this.manager = manager;
	}
	
	public void transformarUnidad() {
		Posicion pos = manager.getPosicion();
		Unidad unit = juego.obtenerUnidad(pos);
		System.out.println(unit.nombreImagen());
		if (unit.sePuedeTransformar())
			juego.transformarUnidad(pos);
	}
	
}
