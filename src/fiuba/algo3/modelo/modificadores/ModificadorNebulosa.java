package fiuba.algo3.modelo.modificadores;

public class ModificadorNebulosa extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 3;
	}
	@Override
	protected boolean puedeMoverse(){
		return false;
	}

}
