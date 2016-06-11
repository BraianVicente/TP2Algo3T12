package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.IgnorarMuerte;
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

public class MentiTron extends Unidad {

	public MentiTron() {
		super(new Decepticons(), new IgnorarMuerte());
	}

	@Override
	public int getVidaMaxima() {
		return 50;
	}

	@Override
	protected int getDistanciaAtaque() {
		return 0;
	}

	@Override
	protected int getPuntosAtaque() {
		return 15;
	}

	@Override
	protected int getDistanciaMovimiento() {
		return 2;
	}

	@Override
	public Forma getFormaActual() {
		return null;
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
}
