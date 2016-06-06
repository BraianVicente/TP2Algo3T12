package fiuba.algo3.modelo.formas;

import fiuba.algo3.modelo.modificadores.ContenedorModificadores;

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
	@Override
	public  float obtenerCoeficientePorVelocidad(ContenedorModificadores modificadores){
		return modificadores.coeficienteVelocidadPorForma(this);
	}

}
