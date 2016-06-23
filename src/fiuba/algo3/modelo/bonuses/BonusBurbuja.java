package fiuba.algo3.modelo.bonuses;

import fiuba.algo3.modelo.bonuses.levantador.LevantadoDeBonusListener;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.modificadores.ModificadorBurbuja;

public class BonusBurbuja extends Bonus {

	public BonusBurbuja(LevantadoDeBonusListener command) {
		super(command);
	}

	@Override
	public Modificador obtenerModificador() {
		return new ModificadorBurbuja();
	}

	@Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/bonus/bonusInvulnerabilidad.png";
	}

}
