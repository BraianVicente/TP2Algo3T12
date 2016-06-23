package fiuba.algo3.modelo.bonuses.levantador;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.tablero.Tablero;

public class RemoverLevantado implements LevantadoDeBonusListener {

	Tablero tablero;
	public RemoverLevantado(Tablero t){
		tablero = t;
	}
    
	@Override
	public void agarrado(Bonus b) {
		tablero.agarrado(b);
	}

}
