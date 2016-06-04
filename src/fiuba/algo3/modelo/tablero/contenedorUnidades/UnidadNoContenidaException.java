package fiuba.algo3.modelo.tablero.contenedorUnidades;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.UnidadException;

public class UnidadNoContenidaException extends UnidadException {

	public UnidadNoContenidaException(Unidad problematica) {
		super(problematica);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String descripcion() {
		return "No esta contenida la unidad";
	}

}
