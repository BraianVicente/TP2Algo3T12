package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public class MentiTron extends UnidadConVida {

	public MentiTron() {
		super(new Decepticons(), new IgnorarMuerte());
	}

	@Override
	public int getVidaMaxima() {
		return 50;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 0;
	}

	@Override
	protected int getPuntosAtaque() {
		return 15;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 2;
	}

	@Override
	public Forma getFormaActual() {
		return null;
	}

}
