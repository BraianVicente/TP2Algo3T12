package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideMenasor;

public class Menasor extends Transformer {
	
	private int vidaMax;

	public Menasor(DeathListener command) {
		super(new Decepticons(), command);
	}
	
	public Menasor() {
		super(new Decepticons(), new IgnorarMuerte());
	}
	
	public Menasor(Transformer a, Transformer b, Transformer c) {
		super(new Decepticons(), new IgnorarMuerte());
		vidaMax = a.getVida() + b.getVida() + c.getVida();
		super.setVida(vidaMax);
	}

	@Override
	protected Forma getVehiculo() {
		return new HumanoideMenasor();
	}

	@Override
	protected Forma getHumanoide() {
		return new HumanoideMenasor();
	}

	@Override
	public boolean esTerrestre() {
		return true;
	}

	@Override
	public int getVidaMaxima() {
		return vidaMax;
	}

}
