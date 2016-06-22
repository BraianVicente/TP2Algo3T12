package fiuba.algo3.controlador;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.Casillero;
import fiuba.algo3.vista.CanvasJuego.ModoVista;

public class ClickedUnitManager implements CallbackCasillero {
	
	private Casillero casillero;
	
	@Override
	public void execute(Casillero cas) {
		casillero = cas;
	}
//	
//	public Unidad getUnidadTerrestre() {
//		if (casillero.getuTerrestre() != null)
//			return casillero.getuTerrestre();
//		else
//			return getUnidadAerea();
//	}
//	
//	public Unidad getUnidadAerea() {
//		if (casillero.getuAerea() != null)
//			return casillero.getuAerea();
//		else
//			return getUnidadTerrestre();
//	}
	
	public Unidad getUnidad(ModoVista modo){
		Unidad u=null;
		if(modo==ModoVista.AMBAS) {
			if(null!= casillero.getuTerrestre())u= casillero.getuTerrestre();
			else u= casillero.getuAerea();
		}
		if(modo==ModoVista.SOLOAIRE) u= casillero.getuAerea();
		if(modo==ModoVista.SOLOTIERRA) u= casillero.getuTerrestre();
		return u;
	}

	public Posicion getPosicion() {
		return casillero.getPosicion();
	}
	public void actualizarCasillero(Casillero cas) {
		casillero=cas;
	}
}
