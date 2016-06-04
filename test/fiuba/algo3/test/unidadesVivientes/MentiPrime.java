package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public class MentiPrime extends UnidadConVida {

	protected MentiPrime() {
		super(new Autobots(), new IgnorarMuerte());
	}

	@Override
	public int getVidaMaxima() {
		return 100;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 3;
	}

	@Override
	protected int getPuntosAtaque() {
		return 10;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 0;
	}

	@Override
	public Forma getFormaActual() {
		return null;
	}

}
