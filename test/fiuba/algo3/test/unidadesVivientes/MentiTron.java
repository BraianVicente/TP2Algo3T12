package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public class MentiTron extends UnidadConVida {

	public MentiTron(){
		super(new Decepticons());
	}
	@Override
	public int getVidaMaxima() {
		return 100;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 1;
	}

	@Override
	protected int getPuntosAtaque() {
		return 15;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 2;
	}

}
