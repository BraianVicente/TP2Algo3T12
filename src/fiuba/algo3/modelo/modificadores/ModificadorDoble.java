package fiuba.algo3.modelo.modificadores;

public class ModificadorDoble extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 3;
	}
	
	protected float coeficienteAtaque(){
		return 2;
	}

}
