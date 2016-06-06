package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public interface DeathListener {

	void murio(UnidadConVida u) throws NoSeEncuentraUnidadException;
}
