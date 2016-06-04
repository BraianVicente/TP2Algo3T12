package fiuba.algo3.modelo.modificadores;

public class ModificadorPsionica extends ModificadorPermanente {
	@Override
	protected float coeficienteAtaqueModoVehiculo(){
		return 0.4f;
	}
	@Override
	protected boolean afectadoPorPsionica(){
		return true;
	}
}
