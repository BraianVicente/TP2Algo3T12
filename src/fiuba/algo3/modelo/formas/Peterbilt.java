package fiuba.algo3.modelo.formas;

public class Peterbilt extends Forma {

	@Override
	public Forma getAlternativa() {
		return new HumanoideOptimusprime();
	}

	@Override
	public int getDistanciaAtaque() {
		return 4;
	}

	@Override
	public int getPuntosAtaque() {
		return 15;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 5;
	}

	@Override
	public boolean esHumanoide(){
		return false;
	}
	
	@Override
	public boolean esVehiculo(){
		return true;
	}
}
