package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.chispa.*;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.modificadores.ContenedorModificadores;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;

public abstract class UnidadConVida extends Unidad{
	
	private Chispa chispa;
	private DeathListener command;
	ContenedorModificadores modificadores;
	
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
    	if(atacante.es(equipo)){//Este if estï¿½ mal, cï¿½mo puedo volarlo?
    		throw new FriendlyFireException();
    	}
    	
    	this.disminuirVida(danio);
        if (getVida() <= 0){
    		command.murio(this);//Death.getInstance().unidadMuerta(a);
    	}
    }
    
    //------------------ataque-----------------
    public boolean puedeAtacar(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaAtaque();
    }
    
    public void atacarA(Unidad receptor) throws FriendlyFireException, NoSeEncuentraUnidadException{
    	int danio = (int)Math.ceil(getPuntosAtaque()*modificadores.coeficienteAtaque());
    	receptor.recibirDanio(this,danio);
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
    public int getVelocidad(){
    	if(modificadores.puedeMoverse()){
    		//capaz sería mejor que si está afectado por la nebulosa coso 
    		//devuelva 0 coeficienteVelocidad, por ahora lo dejo así por las dudas.
    		return (int) Math.ceil(getDistanciaMovimiento()*modificadores.coeficienteVelocidad());
    	}else{
    		return 0;
    	}
    }
    protected abstract int getDistanciaMovimiento();


    protected void disminuirVida(int danio) {
    	if(modificadores.recibeDanio()){
    		vida -= danio;
    	}
    }
    
    //------------------------modificadores------------------//
    
    protected void agregarModificador(Modificador m){
    	modificadores.agregar(m);
    }
    //esto se delega para abajo
    protected float coeficienteAtaqueModoVehiculo(){
    	return modificadores.coeficienteAtaqueModoVehiculo();
    }
	public void recibirBonus(Bonus bonus) {
		agregarModificador(bonus.obtenerModificador());
	}
	
	//-------------------------interacción con superfícies--------------------//
	public void serAfectadoPor(Superficie s){
		s.afectarA(this);
	}
	//debe haber una manera más linda de hacer eesto
	public abstract void serAfectadoPor(NebulosaAndromeda s);
	public abstract void serAfectadoPor(Nubes s);
	public abstract void serAfectadoPor(TormentaPsionica s);
	public abstract void serAfectadoPor(Espinas s);
	public abstract void serAfectadoPor(Pantano s);
	public abstract void serAfectadoPor(Rocosa s);
	
	public float coeficienteMovimientoEn(Superficie s){
		return 1;
	}
	
	public abstract float coeficienteMovimientoEn(Pantano s);
    
}
