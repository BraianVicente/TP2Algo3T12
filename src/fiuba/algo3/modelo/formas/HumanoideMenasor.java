package fiuba.algo3.modelo.formas;

public class HumanoideMenasor extends FormaHumanoide {

	@Override
	public Forma getAlternativa() {
		return this;
	}

	@Override
	public int getDistanciaAtaque() {
		return 2;
	}

	@Override
	public int getPuntosAtaque() {
		return 115;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 2;
	}

	@Override
	public Integer disminuirEnUnMovimiento() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
