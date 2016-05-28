package fiuba.algo3.modelo.formas;

public class Camaro extends Forma {

	@Override
	public Forma getAlternativa() {
		return new HumanoideBumblebee();
	}

	@Override
	public int getDistanciaAtaque() {
		return 3;
	}

	@Override
	public int getPuntosAtaque() {
		return 20;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 5;
	}

}