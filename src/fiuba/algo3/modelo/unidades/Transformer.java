package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.modificadores.ModificadorPsionica;
import fiuba.algo3.modelo.tablero.TransformacionInvalida;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;


public abstract class Transformer extends Unidad {
	Forma forma;
	boolean seTransformoEnEsteTurno;
	
	Transformer(Equipo equipo,DeathListener command){
		super(equipo,command);
		forma = getVehiculo();
		movimientosUsados=0;
		seTransformoEnEsteTurno=false;
	}
	
	public void transformar(){
			if(seTransformoEnEsteTurno) throw new TransformacionInvalida();
			forma = forma.getAlternativa();
			seTransformoEnEsteTurno=true;
		//sin embargo, creo q esta bueno que queden los getVehiculo() getHumanoide() por las dudas
		//(y porque son los que acordamos, y porque 
		//se me hace que tiene mas sentido aunque esta solucion me guste mas porque ahorra un if (if esVehiculo()))
	}
	//------------------formas--------------------//
	protected abstract Forma getVehiculo();
	protected abstract Forma getHumanoide();
	
	//-----------------estadisticas---------------//
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
	///lo ideal es volar �stos
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
    //esto tambi�n habr�a que volarlo
    
    public float getCoeficienteMovimientoActual() {
  		// TODO Auto-generated method stub
  		return modificadores.coeficienteVelocidad()*forma.obtenerCoeficientePorVelocidad(modificadores);
  	}
    //--------------------------superficies-----------------------------//

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
	
	@Override
	public void avanzarTurno(){
		super.avanzarTurno();
		this.seTransformoEnEsteTurno=false;
	}
	

}
