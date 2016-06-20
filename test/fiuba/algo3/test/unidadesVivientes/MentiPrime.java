package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidades.Unidad;

public class MentiPrime extends MentiUnidad {

	protected MentiPrime() {
		super(new Autobots(), new IgnorarMuerte());
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
	public int getPuntosAtaque() {
		return 10;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 0;
	}

	@Override
	public void serAfectadoPor(NebulosaAndromeda s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAfectadoPor(Nubes s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAfectadoPor(TormentaPsionica s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAfectadoPor(Espinas s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAfectadoPor(Pantano s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serAfectadoPor(Rocosa s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float coeficienteMovimientoEn(Pantano s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean esAerea(){
		return false;
	}
	
	@Override
	public boolean esTerrestre(){
		return true;
	}
	@Override
	public float getCoeficienteMovimientoActual() {
	  	return modificadores.coeficienteVelocidad()*modificadores.coeficienteVelocidadPorForma(new HumanoideBumblebee());
	}

	@Override
	public String nombreImagen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sePuedeTransformar() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public float coeficienteVelocidadParaSuperficie(Superficie superficie) {
		// TODO Auto-generated method stub
		return 0;
	}

    @Override
    public String nombre() {
        return this.toString();
    }
}
