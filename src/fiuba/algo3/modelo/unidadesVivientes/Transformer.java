package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;

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

    
    public abstract boolean esTerrestre();//lo ideal es volar ésto

    //esto también habría que volarlo
    public Forma getFormaActual() {
        return this.forma;
    }


}
