package fiuba.algo3.modelo.formas;

public class HumanoideBonecrusher extends Forma {

	@Override
	public Forma getAlternativa() {
		return new Blindado();
	}

	@Override
	public int getDistanciaAtaque() {
		return 3;
	}

	@Override
	public int getPuntosAtaque() {
		return 30;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 1;
	}


	@Override
	public boolean esHumanoide(){
		return true;
	}
	
	@Override
	public boolean esVehiculo(){
		return false;
	}
}
