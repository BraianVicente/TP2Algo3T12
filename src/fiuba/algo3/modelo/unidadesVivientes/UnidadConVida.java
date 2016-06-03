package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.Death;
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
    
    @Override
    public int getVida(){
    	return vida;
    }
    @Override
	public void recibirDanio(Unidad atacante, int danio) throws FriendlyFireException {
    	if(atacante.es(equipo)){//Este if est� mal, c�mo puedo volarlo?
    		throw new FriendlyFireException();
    	}
        this.disminuirVida(danio);
    }
    
    //------------------ataque-----------------
    public boolean puedeAtacar(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaAtaque();
    }
    
    public void atacarA(Unidad receptor) throws FriendlyFireException{
    	    	receptor.recibirDanio(this,getPuntosAtaque());
    }
    public void atacarA(Unidad receptor,Posicion a, Posicion desde) throws FriendlyFireException,AtaqueInvalidoPorDistanciaException{
    	if(!this.puedeAtacar(a, desde)) throw new AtaqueInvalidoPorDistanciaException();
    	this.atacarA(receptor);
    	if (receptor.getVida() <= 0) Death.getInstance().unidadMuerta(a);
    }
    protected abstract int getDistanciaAtaque();
    protected abstract int getPuntosAtaque();
    
	//-----------------movimiento--------------
    public boolean puedeMoverse(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaMovimiento();
    }
    protected abstract int getDistanciaMovimiento();

    public void recibirAtaqueEspinas(){
        this.disminuirVida((vida*5)/100);
    }

    private void disminuirVida(int danio) {
        vida -= danio;
    }
}
