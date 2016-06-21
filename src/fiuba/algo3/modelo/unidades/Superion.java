package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.formas.HumanoideSuperion;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;

public class Superion extends UnidadCombinable {
	




	public Superion( DeathListener command, Unidad unita, Unidad unitb, Unidad unitc) {
		super(new Autobots(), command, unita, unitb, unitc);

	}


	@Override
	public boolean esTerrestre() {
		return true;
	}


	@Override
	public boolean esAerea() {
		return false;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 2;
	}

	@Override
	public int getPuntosAtaque() {
		return 100;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 2;
	}

	@Override
	public float coeficienteMovimientoEn(Pantano s) {
		return 0;
	}
	
	@Override
	public float getCoeficienteMovimientoActual() {
	  	return modificadores.coeficienteVelocidad()*modificadores.coeficienteVelocidadPorForma(new HumanoideBumblebee());
	}

	@Override
	public String nombreImagen() {
		return "fiuba/algo3/vista/imagenes/transformers/Superion.png";
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
