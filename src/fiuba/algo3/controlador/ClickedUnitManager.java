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
		if (casillero.getuTerrestre() != null)
			return casillero.getuTerrestre();
		else
			return getUnidadAerea();
	}
	
	public Unidad getUnidadAerea() {
		if (casillero.getuAerea() != null)
			return casillero.getuAerea();
		else
			return getUnidadTerrestre();
	}
	
	public Posicion getPosicion() {
		return casillero.getPosicion();
	}
}
