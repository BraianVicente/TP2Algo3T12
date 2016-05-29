package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.Camaro;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;

public class Bumblebee extends Transformer {

	public Bumblebee(){
		super(new Autobots());
	}
	@Override
	protected Forma getVehiculo() {
		return new Camaro();
	}

	@Override
	protected Forma getHumanoide() {
		return new HumanoideBumblebee();
	}

	@Override
	public int getVidaMaxima() {
		return 350;
	}

}
