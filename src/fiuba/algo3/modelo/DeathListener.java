package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public interface DeathListener {
	/*
	void unidadMuerta(Posicion pos);
	void asignarTablero(Tablero tablero);
	*/
	void murio(UnidadConVida u) throws NoSeEncuentraUnidadException;
}
