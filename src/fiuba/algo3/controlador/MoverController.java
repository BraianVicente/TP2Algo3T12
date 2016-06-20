package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.Casillero;

public class MoverController implements CallbackCasillero {

	private Casillero actual, anterior;
	private Juego juego;
	
	public MoverController(Juego juego) {
		this.juego = juego;
	}
	
	@Override
	public void execute(Casillero cas) {
		if (actual == null) {
			actual = cas;
		} else if (anterior != null) {
			anterior = actual;
			actual = cas;
		} else {
			anterior = actual;
			actual = cas;
			juego.moverUnidad(anterior.getPosicion(), actual.getPosicion());
		}
	}
	
	public boolean isEmpty(Casillero cas) {
		return (cas.getuTerrestre() == null && cas.getuAerea() == null);
	}

}
