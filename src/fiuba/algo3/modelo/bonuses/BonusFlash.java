package fiuba.algo3.modelo.bonuses;

import fiuba.algo3.modelo.bonuses.levantador.LevantadoDeBonusListener;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.modificadores.ModificadorFlash;

public class BonusFlash extends Bonus {

	public BonusFlash(LevantadoDeBonusListener command) {
		super(command);
	}

	@Override
	public Modificador obtenerModificador() {
		return new ModificadorFlash();
	}
	
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/bonus/bonusFlash.png";
	}

}
