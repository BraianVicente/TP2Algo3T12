package fiuba.algo3.modelo;

import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;

public class Death implements DeathListener {
	
	private Tablero tablero;
	private static DeathListener INSTANCE = new Death();
	
	private Death() {}
	
	@Override
	public void asignarTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public static DeathListener getInstance() {
		return INSTANCE;
	}

	@Override
	public void unidadMuerta(Posicion pos) {
		if (tablero.isEmpty(pos)) throw new RuntimeException();
		if (tablero.obtenerUnidad(pos).tieneChispa())
			tablero.agregarChispa(pos, ChispaSuprema.getInstance());
		tablero.quitarUnidadActual(pos);
	}

}
