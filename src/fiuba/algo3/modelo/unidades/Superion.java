package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.formas.HumanoideSuperion;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;

public class Superion extends UnidadCombinable {
	
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
	public boolean esTerrestre() {
		return true;
	}

	@Override
	public int getVidaMaxima() {
		return vidaMax;
	}

	@Override
	public boolean esAerea() {
		return false;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 2;
	}

	@Override
	protected int getPuntosAtaque() {
		return 100;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 2;
	}

	@Override
	public float coeficienteMovimientoEn(Pantano s) {
		return 0;
	}
	
	@Override
	public float getCoeficienteMovimientoActual() {
	  	return modificadores.coeficienteVelocidad()*modificadores.coeficienteVelocidadPorForma(new HumanoideBumblebee());
	}

    
}
