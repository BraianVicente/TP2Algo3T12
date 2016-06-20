package fiuba.algo3.controlador;

import java.util.Iterator;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;

public class GameController {
	
	private Juego juego;
	private ClickedUnitManager manager;
	private CanvasJuego cj;
	
	public GameController(Juego juego, ClickedUnitManager manager, CanvasJuego cj) {
		this.juego = juego;
		this.manager = manager;
		this.cj = cj;
	}
	
	public void transformarUnidad() {
		Unidad unit = manager.getUnidad();
		Posicion pos = juego.obtenerPosicion(unit);
		if (unit.sePuedeTransformar())
			juego.transformarUnidad(pos);
		cj.actualizar();
	}
	
	public void combinarUnidades() {
		juego.combinarUnidades();
		cj.actualizar();
	}
	
}
