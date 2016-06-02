package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public class MentiPrime extends UnidadConVida {

	public MentiPrime(){
		super(new Autobots());
	}
	@Override
	public int getVidaMaxima() {
		return 100;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 3;
	}

	@Override
	protected int getPuntosAtaque() {
		return 10;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 4;
	}

    @Override
    public Forma getFormaActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
