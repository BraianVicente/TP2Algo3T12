package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidades.Unidad;

abstract public class MentiUnidad extends Unidad{
	
	public MentiUnidad(Equipo e){
		super(e);
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
}
