package fiuba.algo3.modelo.modificadores;

public class ModificadorBurbuja extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 2;
	}
	@Override
	protected boolean recibeDanio(){
		return false;
	}

}
