package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;

public class Menasor extends UnidadCombinable {
	
	private int vidaMax;

	public Menasor(DeathListener command) {
		super(new Decepticons(), command);
	}
	
	public Menasor() {
        this(new IgnorarMuerte());
	}
	
	public Menasor(Transformer a, Transformer b, Transformer c) {
		super(new Decepticons(), new IgnorarMuerte());
		vidaMax = a.getVida() + b.getVida() + c.getVida();
		super.setVida(vidaMax);
	}

	@Override
	public boolean esTerrestre() {
		return true;
	}

	@Override
	public int getVidaMaxima() {
		return vidaMax;
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
		return 115;
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
		return "fiuba/algo3/vista/imagenes/transformers/Menasor.png";
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
