package fiuba.algo3.modelo.tablero.contenedorBonuses;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.bonuses.BonusException;

public class BonusYaContenidoException extends BonusException {

	public BonusYaContenidoException(Bonus b) {
		super(b);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String descripcion() {
		return "Ya esta contenido";
	}

}
