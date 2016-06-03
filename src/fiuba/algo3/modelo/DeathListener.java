package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;

public interface DeathListener {
	void unidadMuerta(Posicion pos);
	void asignarTablero(Tablero tablero);
}
