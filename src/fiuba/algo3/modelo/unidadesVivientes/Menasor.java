package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.formas.HumanoideMenasor;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;

public class Menasor extends UnidadConVida {
	
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
		return 115;
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
	public Forma getFormaActual() {
		return new HumanoideMenasor();
	}
	
	@Override
	public float getCoeficienteMovimientoActual() {
	  	return modificadores.coeficienteVelocidad()*modificadores.coeficienteVelocidadPorForma(new HumanoideBumblebee());
	}
}
