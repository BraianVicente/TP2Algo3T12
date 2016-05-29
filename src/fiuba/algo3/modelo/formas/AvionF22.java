package fiuba.algo3.modelo.formas;

public class AvionF22 extends Forma {

	@Override
	public Forma getAlternativa() {
		return new HumanoideRatchet();
	}

	@Override
	public int getDistanciaAtaque() {
		return 2;
	}

	@Override
	public int getPuntosAtaque() {
		return 35;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 8;
	}

}