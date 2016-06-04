package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;

public interface DeathListener {
	/*
	void unidadMuerta(Posicion pos);
	void asignarTablero(Tablero tablero);
	*/
	void murio(Unidad u) throws NoSeEncuentraUnidadException;
}
