package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.tablero.superficies.terrestre.*;
import fiuba.algo3.modelo.tablero.superficies.aerea.*;

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
		//se me hace que tiene m�s sentido aunque esta soluci�n me guste m�s porque ahorra un if (if esVehiculo()))
	}
	//------------------formas--------------------//
	protected abstract Forma getVehiculo();
	protected abstract Forma getHumanoide();
	
	//-----------------estad�sticas---------------//
	@Override
	public int getDistanciaAtaque() {
		return forma.getDistanciaAtaque();
	}

	@Override
	public int getPuntosAtaque() {
		return forma.getPuntosAtaque();
	}

	@Override
	public int getDistanciaMovimiento() {
		return forma.getDistanciaMovimiento();
	}

	public boolean esVehiculo() {
		return forma.esVehiculo();
	}
	
	public boolean esHumanoide(){
		return forma.esHumanoide();
		
	}

    
    public abstract boolean esTerrestre() ;

    @Override
    public Forma getFormaActual() {
        return this.forma;
    }


}
