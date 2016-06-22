package fiuba.algo3.modelo.bonuses;

import fiuba.algo3.modelo.bonuses.levantador.IgnorarLevantado;
import fiuba.algo3.modelo.bonuses.levantador.LevantadoDeBonusListener;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.unidades.Unidad;

public abstract class Bonus {
	private LevantadoDeBonusListener command;
	public Bonus(LevantadoDeBonusListener command){
		this.command = command;
		if(this.command==null){
			this.command = new IgnorarLevantado();
		}
	}
	public abstract Modificador obtenerModificador();
	public void serAgarradoPor(Unidad u){
		u.recibirBonus(this);
		command.agarrado(this);
	}
	abstract public String nombreImagen();
}
