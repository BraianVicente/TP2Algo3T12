package fiuba.algo3.modelo.bonuses;

import fiuba.algo3.modelo.LevantadoDeBonusListener;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.modificadores.ModificadorDoble;

public class BonusDobleCanion extends Bonus {

	public BonusDobleCanion(LevantadoDeBonusListener command) {
		super(command);
	}

	@Override
	public Modificador obtenerModificador() {
		return new ModificadorDoble();
	}
	
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/bonus/bonusDoble.png";
	}

}
