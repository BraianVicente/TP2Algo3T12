package fiuba.algo3.modelo.modificadores;

public class ModificadorBurbuja extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 2;
	}
	@Override
	public boolean recibeDanio(){
		return false;
	}
	@Override
	public Object clone() {
		return new ModificadorBurbuja();
	}

}
