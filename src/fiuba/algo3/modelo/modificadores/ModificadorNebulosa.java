package fiuba.algo3.modelo.modificadores;

import fiuba.algo3.modelo.formas.FormaAerea;
import fiuba.algo3.modelo.formas.FormaVehiculo;

public class ModificadorNebulosa extends ModificadorTransitorio {
	@Override
	public float coeficienteVelocidadPorForma(FormaVehiculo  vehiculo){
		return 0;
	}
	@Override
	protected int turnosDuracion() {
		return 3;
	}
	@Override
	public boolean puedeMoverse(){
		return false;
	}
	@Override
	public Object clone() {
		return new ModificadorNebulosa();
	}

}
