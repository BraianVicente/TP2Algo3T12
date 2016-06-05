package fiuba.algo3.modelo.formas;

public abstract class FormaVehiculo extends Forma {
	@Override
	public boolean esHumanoide() {
		return false;
	}

	@Override
	public boolean esVehiculo() {
		return true;
	}
	@Override
	public float coeficienteAtaqueVehiculo(float posible) {
		return posible;
	}

}
