package fiuba.algo3.modelo.unidades.muerte;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidades.Unidad;

public interface DeathListener {

	public class DesarmadorCombinables {

	}

	void murio(Unidad u) throws NoSeEncuentraUnidadException;
}
