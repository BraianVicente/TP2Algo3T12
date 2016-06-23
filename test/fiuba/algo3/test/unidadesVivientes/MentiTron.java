package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidades.Unidad;

public class MentiTron extends MentiUnidad {

	public MentiTron() {
		super(new Decepticons());
	}

	@Override
	public int getVidaMaxima() {
		return 50;
	}

	@Override
	public int getDistanciaAtaque() {
		return 0;
	}

	@Override
	public int getPuntosAtaque() {
		return 15;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 2;
	}

        @Override
    public String nombre() {
        return this.toString();
    }

    @Override
    public void darChispa() {
        chispa = ChispaSuprema.getInstance();
    }

}
