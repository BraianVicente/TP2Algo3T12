package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidades.Unidad;

public interface DeathListener {

	void murio(Unidad u) throws NoSeEncuentraUnidadException;
}
