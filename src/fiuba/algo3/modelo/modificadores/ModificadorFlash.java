package fiuba.algo3.modelo.modificadores;

public class ModificadorFlash extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 3;
	}
	@Override
	protected float coeficienteVelocidad(){
		return 3;
	}

}
