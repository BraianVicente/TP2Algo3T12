package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.FormaHumanoide;
import fiuba.algo3.modelo.formas.HumanoideSuperion;

public class Superion extends Transformer {
	
	private int vidaMax;

	public Superion(DeathListener command) {
		super(new Autobots(), command);
	}
	
	public Superion() {
		super(new Autobots(), new IgnorarMuerte());
	}
	
	public Superion(Transformer a, Transformer b, Transformer c) {
		super(new Autobots(), new IgnorarMuerte());
		vidaMax = a.getVida() + b.getVida() + c.getVida();
		super.setVida(vidaMax);
	}

	@Override
	protected Forma getVehiculo() {
		return new HumanoideSuperion();
	}

	@Override
	protected Forma getHumanoide() {
		return new HumanoideSuperion();
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
