package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.contenedorCasilleros.NoSeEncuentraUnidadException;

public interface DeathListener {
	/*
	void unidadMuerta(Posicion pos);
	void asignarTablero(Tablero tablero);
	*/
	void murio(Unidad u) throws NoSeEncuentraUnidadException;
}
