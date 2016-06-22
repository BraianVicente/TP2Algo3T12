package fiuba.algo3.modelo.modificadores;

public class ModificadorPsionica extends ModificadorPermanente {
	@Override
	public float coeficienteAtaqueModoVehiculo(){
		return 0.4f;
	}
	@Override
	public boolean afectadoPorPsionica(){
		return true;
	}
	@Override
	public Object clone() {
		return new ModificadorPsionica();
	}
	
	@Override
	public String nombreImagen() {
		return null;
	}
}
