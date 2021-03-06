package fiuba.algo3.modelo.unidades;

import java.util.ArrayList;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.chispa.ChispaHolder;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.modificadores.ContenedorModificadores;
import fiuba.algo3.modelo.modificadores.Modificador;
import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.modificadores.ModificadorPantano;
import fiuba.algo3.modelo.modificadores.ModificadorPsionica;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;

public abstract class Unidad {
	
	protected Chispa chispa;
	protected ArrayList<DeathListener> deathListeners;
	protected ContenedorModificadores modificadores;
	protected float movimientosUsados;
	private boolean yaAtaco;
	
	
	
    //-------------------equipo-------------
    protected final Equipo equipo;
    
	protected Unidad(Equipo equipo) {
		this.equipo=equipo;
		vida = getVidaMaxima();
		chispa = new ChispaHolder();
		modificadores=new ContenedorModificadores();
		deathListeners = new ArrayList<DeathListener>();
    }
	
    public boolean es(Equipo e) {
        return equipo.equals(e);
    }
    
    public Equipo equipo() {
    	return this.equipo;
    }
    public void agregarDeathListener(DeathListener listener){
    	deathListeners.add(listener);
    }
    
	public boolean existe(){
		return true;
	}
	
	public boolean tieneChispa() {
		return (chispa instanceof ChispaSuprema);
	}
	
	public abstract void darChispa();
	
	public void quitarChispa() {
		chispa = new ChispaHolder();
	}
	//-------------------------------------
	public abstract boolean esAerea();
	public abstract boolean esTerrestre();
	public Plano getPlanoPerteneciente() {
		return Plano.TERRESTRE;
	}
	//-------------------vida---------------
    protected int vida;
    public abstract int getVidaMaxima();
    
    public int getVida(){
    	return vida;
    }
    
    protected void setVida(int vida) {
    	this.vida = vida;
    }
    
	public void recibirDanio(Unidad atacante, int danio) throws FriendlyFireException, NoSeEncuentraUnidadException {
    	if(atacante.es(equipo)){//Este if esta mal, como puedo volarlo?
    		throw new FriendlyFireException();
    	}
        this.disminuirVida(danio);
   
    }
    
    //------------------ataque-----------------
	public boolean puedeAtacar(Posicion a, Posicion desde){
    	return a.distanciaA(desde)<=getDistanciaAtaque()&&!yaAtaco;
    }
	
	public boolean puedeAtacar(){
    	return !yaAtaco;
    }
    
    public void atacarA(Unidad receptor) throws FriendlyFireException, NoSeEncuentraUnidadException{
    	int danio = (int)Math.ceil(getPuntosAtaque()*modificadores.coeficienteAtaque());
    	receptor.recibirDanio(this,danio);
    	yaAtaco=true;
    }
    public abstract int getDistanciaAtaque();
    public abstract int getPuntosAtaque();
    
	//-----------------movimiento--------------
    
    public int getVelocidad(){
    	if(modificadores.puedeMoverse()){
    		//capaz ser�a mejor que si est� afectado por la nebulosa coso 
    		//devuelva 0 coeficienteVelocidad, por ahora lo dejo as� por las dudas.
    		return (int) Math.ceil(getDistanciaMovimiento()*modificadores.coeficienteVelocidad());
    	}else{
    		return 0;
    	}
    }
    
  
    protected void disminuirVida(int danio) {
    	if(modificadores.recibeDanio()){
    		vida -= danio;
    	}
    	
    	if (getVida() <= 0){
    		senializarMuerte();
    	}
    }
    
    protected void senializarMuerte(){
    	for(DeathListener d : deathListeners){
			d.murio(this);
		}
    }
    /*
    public float getMovimientosUsados(){
    	return movimientosUsados;
    }
    */
    public void restaurarMovimientosRestantes(float nuevoMovimientosRestantes) {
    	if(nuevoMovimientosRestantes>getDistanciaMovimiento()) throw new IllegalArgumentException();
		movimientosUsados=getDistanciaMovimiento()-nuevoMovimientosRestantes;
		
	}
    public void restaurarMovimientosRestantes() {
  		movimientosUsados=0;
  		
  	}
    public void descontarMovimiento(float movimientosADescontar){
    	if(movimientosUsados+movimientosADescontar>getDistanciaMovimiento()) throw new IllegalArgumentException();
    	movimientosUsados+=movimientosADescontar;
    }
    
    public abstract float getCoeficienteMovimientoActual() ;
    
    public boolean puedeMoverseUnCasillero(){
    	return ((float)this.getDistanciaMovimiento()-movimientosUsados)>=1/(float)getCoeficienteMovimientoActual();
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
	
	public void avanzarTurno() {
		modificadores.pasaTurno();
		restaurarMovimientosRestantes();
		yaAtaco=false;
	}
	
	
	//-------------------------interacci�n con superf�cies--------------------//
	public void serAfectadoPor(Superficie s){
		s.afectarA(this);
	}
	public abstract int getDistanciaMovimiento();


	public void serAfectadoPor(NebulosaAndromeda s){
		modificadores.sacarModificadorPantano();
	    if(esAerea()){//////!!!!!!!!!!
	    	agregarModificador(new ModificadorNebulosa());
	   	}
	}
	public void serAfectadoPor(Nubes s){
		modificadores.sacarModificadorPantano();	
		//nada
	}
		
	public void serAfectadoPor(TormentaPsionica s){
		modificadores.sacarModificadorPantano();
		if(esAerea()){//////!!!!!!!!!!
	    	agregarModificador(new ModificadorPsionica());
	    }
	}
	public void serAfectadoPor(Espinas s){
		modificadores.sacarModificadorPantano();
		disminuirVida(getVidaMaxima()*5/100);
	}
	public void serAfectadoPor(Pantano s){
		modificadores.sacarModificadorPantano();
		modificadores.agregar(new ModificadorPantano());
		//nada
	}
	public void serAfectadoPor(Rocosa s){
		modificadores.sacarModificadorPantano();
	}
	
	public float coeficienteMovimientoEn(Superficie s){
		return 1;
	}
	
	public abstract float coeficienteMovimientoEn(Pantano s);

	public float getMovimientosRestantes() {
		return getDistanciaMovimiento()-movimientosUsados;
		
	}

	public abstract String nombreImagen();

	public boolean esDelMismoEquipo(Unidad objetivo) {
		return objetivo.es(equipo);
	}

	abstract public boolean sePuedeTransformar() ;

	public boolean estaAfectadaPorPantano() {
		return modificadores.afectadoPorPantano();
	}

	abstract public float coeficienteVelocidadParaSuperficie(Superficie superficie);

	public boolean cambiaDePlanoAlTransformase() {
		return false;
	}

	public Plano planoPertenecienteSiguienteForma() {
		return Plano.TERRESTRE;
	}

    public abstract String nombre() ;

	public ArrayList<String> obtenerNombresImagenesModificadores() {
		return modificadores.obtenerNombresImagenesModificadores();
	}

	public boolean esCombinacion() {

		return false;
	}
}
