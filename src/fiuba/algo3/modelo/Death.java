package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.NoSeEncuentraUnidadException;

public class Death implements DeathListener {
	private Tablero aInformar;
	public Death(Tablero aInformar){
		this.aInformar = aInformar;
	}
	public void murio(Unidad u) throws NoSeEncuentraUnidadException{
		aInformar.murio(u);
	}
	/*
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
			tablero.agregarChispa(pos);
		tablero.quitarUnidadActual(pos);
	}
	*/
	

}
