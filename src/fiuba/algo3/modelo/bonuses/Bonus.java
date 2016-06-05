package fiuba.algo3.modelo.bonuses;

import fiuba.algo3.modelo.IgnorarLevantado;
import fiuba.algo3.modelo.LevantadoDeBonusListener;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public abstract class Bonus {
	private LevantadoDeBonusListener command;
	public Bonus(LevantadoDeBonusListener command){
		this.command = command;
		if(this.command==null){
			this.command = new IgnorarLevantado();
		}
	}
	protected abstract Modificador obtenerModificador();
	public void serAgarradoPor(UnidadConVida u){
		u.agregarModificador(obtenerModificador());
		command.agarrado(this);
	}
}
