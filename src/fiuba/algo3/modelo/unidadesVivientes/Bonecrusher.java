package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Blindado;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBonecrusher;

public class Bonecrusher extends Transformer {

	public Bonecrusher() {
		super(new Decepticons());
	}

	@Override
	protected Forma getVehiculo() {
		return new Blindado();
	}

	@Override
	protected Forma getHumanoide() {
		return new HumanoideBonecrusher();
	}

	@Override
	public int getVidaMaxima() {
		return 200;
	}

}
