package fiuba.algo3.modelo.modificadores;

public class ModificadorNebulosa extends ModificadorTransitorio {

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
