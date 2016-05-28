package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;

public abstract class Transformer extends UnidadConVida {
	Forma forma;
	
	Transformer(Equipo equipo){
		super(equipo);
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
	protected int getDistanciaAtaque() {
		return forma.getDistanciaAtaque();
	}

	@Override
	protected int getPuntosAtaque() {
		return forma.getPuntosAtaque();
	}

	@Override
	protected int getDistanciaMovimiento() {
		return forma.getDistanciaMovimiento();
	}

}
