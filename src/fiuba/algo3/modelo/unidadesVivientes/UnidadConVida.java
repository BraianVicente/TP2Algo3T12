package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.chispa.*;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;

public abstract class UnidadConVida extends Unidad{
	
	private Chispa chispa;
	private DeathListener command;
	
	protected UnidadConVida(Equipo equipo, DeathListener command) {
		super(equipo);
		vida = getVidaMaxima();
		chispa = new ChispaHolder();
		this.command = command;
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
	public void recibirDanio(Unidad atacante, int danio) throws FriendlyFireException, NoSeEncuentraUnidadException {
    	if(atacante.es(equipo)){//Este if est� mal, c�mo puedo volarlo?
    		throw new FriendlyFireException();
    	}
        this.disminuirVida(danio);
       
    	}
    }
    
    //------------------ataque-----------------
    public boolean puedeAtacar(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaAtaque();
    }
    
    public void atacarA(Unidad receptor) throws FriendlyFireException, NoSeEncuentraUnidadException{
    	    	receptor.recibirDanio(this,getPuntosAtaque());
    }
    public void atacarA(Unidad receptor,Posicion a, Posicion desde) throws FriendlyFireException,AtaqueInvalidoPorDistanciaException, NoSeEncuentraUnidadException{
    	if(!this.puedeAtacar(a, desde)) throw new AtaqueInvalidoPorDistanciaException();
    	this.atacarA(receptor);
    }
    protected abstract int getDistanciaAtaque();
    protected abstract int getPuntosAtaque();
    
	//-----------------movimiento--------------
    public boolean puedeMoverse(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaMovimiento();
    }
    protected abstract int getDistanciaMovimiento();

    public void serAfectadoPorSuperficie(Rocosa rocosa){
    	
    }
    public void serAfectadoPorSuperficie(Nubes nubes){
    	
    }
    
    public void serAfectadoPorSuperficie(NebulosaAndromeda nebulosa){
    	throw new Should;
    }
    public void serAfectadoPorSuperficie(Espinas espinas){
    	this.disminuirVida(vida*5/100);
    }
    
    public void serAfectadoPorSuperficie(Pantano pantano){
    	
    }
    
    public void serAfectadoPorSuperficie(TormentaPsionica tormenta){
    	
    }

    private void disminuirVida(int danio) {
        vida -= danio;
        if (getVida() <= 0)	command.murio(this);//Death.getInstance().unidadMuerta(a);
    }
}
