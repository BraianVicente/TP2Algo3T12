package fiuba.algo3.modelo.formas;

public class HumanoideSuperion extends FormaHumanoide {

	@Override
	public Forma getAlternativa() {
		return this; // throw exception? think that'd be useless
	}

	@Override
	public int getDistanciaAtaque() {
		return 2;
	}

	@Override
	public int getPuntosAtaque() {
		return 100;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 2;
	}
	
	@Override
	public boolean esHumanoide() {
		return true;
	}

	@Override
	public Integer disminuirEnUnMovimiento() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
}