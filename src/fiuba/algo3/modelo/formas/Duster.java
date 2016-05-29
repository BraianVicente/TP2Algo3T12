package fiuba.algo3.modelo.formas;

public class Duster extends Forma {

	@Override
	public Forma getAlternativa() {
		return new HumanoideFrenzy();
	}

	@Override
	public int getDistanciaAtaque() {
		return 2;
	}

	@Override
	public int getPuntosAtaque() {
		return 25;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 6;
	}

}
