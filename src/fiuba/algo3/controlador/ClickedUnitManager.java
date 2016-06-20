package fiuba.algo3.controlador;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.Casillero;

public class ClickedUnitManager implements CallbackCasillero {
	
	private Casillero casillero;
	
	@Override
	public void execute(Casillero cas) {
		casillero = cas;
	}
	
	public Unidad getUnidadTerrestre() {
		return casillero.getuTerrestre();
	}
	
	public Unidad getUnidadAerea() {
		return casillero.getuAerea();
	}
	
	public Unidad getUnidad() {
		return casillero.getUnidad();
	}
	
	public Posicion getPosicion() {
		return casillero.getPosicion();
	}
}
