package fiuba.algo3.modelo.tablero.contenedorUnidades;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.UnidadException;

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
		return "Ya está contenida la unidad";
	}

}
