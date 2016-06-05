package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;

import fiuba.algo3.modelo.tablero.superficies.terrestre.*;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.superficies.aerea.*;

import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.modificadores.ModificadorPsionica;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;


public abstract class Transformer extends UnidadConVida {
	Forma forma;
	
	Transformer(Equipo equipo,DeathListener command){
		super(equipo,command);
		forma = getVehiculo();
	}
	
	public void transformar(){
		forma = forma.getAlternativa();
		//sin embargo, creo q esta bueno que queden los getVehiculo() getHumanoide() por las dudas
		//(y porque son los que acordamos, y porque 
		//se me hace que tiene mï¿½s sentido aunque esta soluciï¿½n me guste mï¿½s porque ahorra un if (if esVehiculo()))
	}
	//------------------formas--------------------//
	protected abstract Forma getVehiculo();
	protected abstract Forma getHumanoide();
	
	//-----------------estadï¿½sticas---------------//
	@Override
	public int getDistanciaAtaque() {
		return forma.getDistanciaAtaque();
	}

	@Override
	public int getPuntosAtaque() {
		return (int) Math.ceil(forma.getPuntosAtaque()*forma.coeficienteAtaqueVehiculo(coeficienteAtaqueModoVehiculo()));
	}

	@Override
	public int getDistanciaMovimiento() {
		return forma.getDistanciaMovimiento();
	}
	///lo ideal es volar éstos
	public boolean esVehiculo() {
		return forma.esVehiculo();
	}
	
	public boolean esHumanoide(){
		return forma.esHumanoide();
		
	}

    
    public boolean esTerrestre(){
    	return forma.esTerrestre();
    }
    public boolean esAerea(){
    	return forma.esAerea();
    }
    //esto también habría que volarlo
    public Forma getFormaActual() {
        return this.forma;
    }
    
    //--------------------------superfícies-----------------------------//
    public void serAfectadoPor(NebulosaAndromeda s){
    	if(esAerea()){//////!!!!!!!!!!
    		agregarModificador(new ModificadorNebulosa());
    	}
    }
	
	public void serAfectadoPor(TormentaPsionica s){
		if(esAerea()){//////!!!!!!!!!!
    		agregarModificador(new ModificadorPsionica());
    	}
	}
	public void serAfectadoPor(Espinas s){
		disminuirVida((int)forma.danioRealPorEspinas((getVidaMaxima()*5)/100));
	}


	public float coeficienteMovimientoEn(Pantano s){
		return forma.coeficienteMovimientoEnPantano();
	}

	

}
