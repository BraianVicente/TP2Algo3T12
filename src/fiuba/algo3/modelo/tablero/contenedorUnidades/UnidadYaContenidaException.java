package fiuba.algo3.modelo.tablero.contenedorUnidades;

import fiuba.algo3.modelo.unidades.UnidadException;
import fiuba.algo3.modelo.unidades.Unidad;

public class UnidadYaContenidaException extends UnidadException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnidadYaContenidaException(Unidad u) {
		super(u);
	}

	@Override
	public String descripcion() {
		return "Ya esta contenida la unidad";
	}

}
