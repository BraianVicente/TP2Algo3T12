package fiuba.algo3.modelo.formas;

public class HumanoideRatchet extends Forma {

	@Override
	public Forma getAlternativa() {
		return new AvionF22();
	}

	@Override
	public int getDistanciaAtaque() {
		return 5;
	}

	@Override
	public int getPuntosAtaque() {
		return 5;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 1;
	}

}
