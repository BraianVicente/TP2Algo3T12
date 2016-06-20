package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.Casillero;

public class MoverController implements CallbackCasillero {

	private Casillero actual, anterior;
	private Juego juego;
	
	public MoverController(Juego juego) {
		this.juego = juego;
		actual = null;
		anterior = null;
	}
	
	@Override
	public void execute(Casillero cas) {
		if (actual != null && cas.isEmpty()) {
			anterior = actual;
			actual = cas;
			
			Unidad unit = anterior.getUnidad();
			Posicion posActual = juego.obtenerPosicion(unit);
			Posicion posNueva = new Posicion(actual.getPos().getX(), actual.getPos().getY(), unit.getPlanoPerteneciente());
			
			juego.moverUnidad(posActual, posNueva);
		} else {
			anterior = actual;
			actual = cas;
		}
	}
	
	public boolean isEmpty(Casillero cas) {
		return (cas.getuTerrestre() == null && cas.getuAerea() == null);
	}

}
