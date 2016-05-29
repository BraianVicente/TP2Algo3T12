package fiuba.algo3.modelo.formas;

public class HumanoideFrenzy extends Forma {

	@Override
	public Forma getAlternativa() {
		return new Duster();
	}

	@Override
	public int getDistanciaAtaque() {
		return 5;
	}

	@Override
	public int getPuntosAtaque() {
		return 10;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 2;
	}

}
