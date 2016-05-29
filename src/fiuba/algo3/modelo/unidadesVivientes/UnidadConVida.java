package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.chispa.*;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Posicion;

public abstract class UnidadConVida extends Unidad{
	
	private Chispa chispa;
	
	protected UnidadConVida(Equipo equipo) {
		super(equipo);
		vida = getVidaMaxima();
		chispa = new ChispaHolder();
	}
	@Override
	public boolean existe(){
		return true;
	}
	
	@Override
	public boolean tieneChispa() {
		return (chispa instanceof ChispaSuprema);
	}
	
	public void darChispa() {
		chispa = ChispaSuprema.getInstance();
	}
	
	public void quitarChispa() {
		chispa = new ChispaHolder();
	}

	//-------------------vida---------------
    private int vida;
    public abstract int getVidaMaxima();
    public int getVida(){
    	return vida;
    }
    @Override
	public void recibirDanio(Unidad atacante, int danio) throws FriendlyFireException,DeadUnitException {
    	if(atacante.es(equipo)){//Este if est� mal, c�mo puedo volarlo?
    		throw new FriendlyFireException();
    	}
    	vida -= danio;
    	if (vida <= 0) throw new DeadUnitException();
    }
    
    //------------------ataque-----------------
    public boolean puedeAtacar(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaAtaque();
    }
    
    public void atacarA(Unidad receptor) throws FriendlyFireException{
    	receptor.recibirDanio(this,getPuntosAtaque());
    }
    protected abstract int getDistanciaAtaque();
    protected abstract int getPuntosAtaque();
    
	//-----------------movimiento--------------
    public boolean puedeMoverse(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaMovimiento();
    }
    protected abstract int getDistanciaMovimiento();
}
