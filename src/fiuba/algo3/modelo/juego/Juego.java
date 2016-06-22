/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.juego;

import fiuba.algo3.modelo.tablero.Escenario;
import java.util.ArrayList;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.posicion.PosicionEnElPlano;
import fiuba.algo3.modelo.posicion.PosicionLibreException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Bonecrusher;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Frenzy;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.modelo.unidades.Ratchet;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.Casillero;

/**
 *
 * @author brahvic
 */
public class Juego {
    
    
    private Jugador jugadorDecepticons ;
    private Jugador jugadorAutobots ;
    private Jugador enTurno ;
    private Jugador enEspera ;
    private Tablero tablero ;
    private Jugador ganador;
    
    private Unidad unidadSeleccionada;
    

    public Juego(Tablero tab,Jugador autobots,Jugador decepticons ){
        // el juego debe iniciarse vacio, ir agregando primero los jugadores
        // y una vez tenga el tablero, las unidades en sus correspondientes cuadrantes
        this.tablero = tab;
        this.tablero.colocarChispa();
        this.jugadorAutobots = autobots;
        this.enTurno = autobots ;   
        this.jugadorDecepticons = decepticons;
        this.enEspera = decepticons ;
        this.unidadSeleccionada = null;
        
    }
    
   
    public void crearTablero(Escenario escenarioCreator){
        this.tablero = new Tablero(escenarioCreator);
        this.tablero.colocarChispa();
    }
    
    public void agregarJugadorDecepticons(String nombre){
        this.jugadorDecepticons = new Jugador(nombre,new Decepticons());
        // La idea es cambiarlo para que el jugador lo agrege aleatoriamente 
        // en una zona predeterminada segun el tamanio del tablero
        // se va a agregar el comportamiento con nuevos Tableros
        tablero.agregarUnidad(new Posicion(0,0,Plano.AEREO), new Megatron());
        tablero.agregarUnidad(new Posicion(2,2,Plano.AEREO), new Bonecrusher());
        tablero.agregarUnidad(new Posicion(3,1,Plano.TERRESTRE), new Frenzy());
        
    }
    
    public void agregarJugadorAutobots(String nombre){
        this.jugadorAutobots = new Jugador(nombre,new Decepticons());
        // La idea es cambiarlo para que el jugador lo agrege aleatoriamente 
        // en una zona predeterminada segun el tamanio del tablero
        // se va a agregae el comportamiento con nuevos tableros
        tablero.agregarUnidad(new Posicion(8,9,Plano.TERRESTRE), new Optimusprime());
        tablero.agregarUnidad(new Posicion(8,8,Plano.TERRESTRE), new Bumblebee());
        tablero.agregarUnidad(new Posicion(7,8,Plano.AEREO), new Ratchet());
    }
    
    public void avanzarTurno(){
        this.cambiarTurno();
        this.enTurno.pasarTurno();
    }

    public Jugador jugadorEnTurno(){
        return enTurno;
    }
    
    public void moverUnidad(Posicion origen,Posicion destino){
        if (enTurno.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.mover(tablero.obtenerUnidad(origen), destino);
        }
    }

    public void atacarUnidad(Posicion origen, Posicion destino){
        if (enTurno.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.atacar(tablero.obtenerUnidad(origen), tablero.obtenerUnidad(destino));
        }
    }
    
    public void transformarUnidad(Posicion origen){
    	if (enTurno.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.transformar(tablero.obtenerUnidad(origen));
        }
    }
    
    public void combinarUnidades(){
        enTurno.combinarUnidades();
    }

    private void cambiarTurno() {
        Jugador aux = enTurno ;
        enTurno = enEspera;
        enEspera = aux ;
    }

	public Unidad obtenerUnidad(Posicion posActual) {
		return tablero.obtenerUnidad(posActual);
		
	}

	public ArrayList<Unidad> obtenerUnidades() {
		return tablero.obtenerUnidades();
	}

	public Posicion posicion(Unidad u) {
		return tablero.posicion(u);
	}

	public Superficie obtenerSuperficie(Posicion seleccionada) {
		return tablero.obtenerSuperficie(seleccionada);
	}

	public void agregarUnidad(Posicion posicion, Unidad unidad) {
		tablero.agregarUnidad(posicion,unidad);
		
	}

	public Posicion obtenerPosicion(Unidad unidad) {
		// TODO Auto-generated method stub
		return tablero.posicion(unidad);
	}

	public int obtenerAncho() {
		return tablero.obtenerAncho();
	}

	public int obtenerAlto() {
		return tablero.obtenerAlto();
	}

	public boolean enTablero(Posicion p) {
		return tablero.enLimites(p);
	}
	
	public boolean enTablero(PosicionEnElPlano p) {
		return tablero.enLimites(p);
	}
    
    public boolean jugadorGanadorEs(Jugador jugador) {
        return jugador.equals(this.ganador) ;
    }

    public Jugador getGanador() {
    	return ganador;
    }
    
    public void jugadorGanador(Equipo equipo) {
    	this.ganador = this.jugadorDe(equipo) ;
    }

    public void jugadorDerrotado(Equipo equipo) {
        this.ganador = this.jugadorDe(equipo.equipoContrario()) ;
    }
    

    private Jugador jugadorDe(Equipo e) {
		if(this.enTurno.getEquipo().equals(e)) return enTurno;
		else return enEspera;
	}


	public String accionPosibleEn(PosicionEnElPlano posicion){
		if(!enTablero(posicion)) return "";
		
    	 Casillero cas = construirCasillero(posicion.crearPosicion(Posicion.Plano.AEREO));
    	
    	if(cas.getEsChispa()){
    		return "Chispa suprema:\nLlevala al monte de la perdicon para ganar";
    	}
    	
    	if(cas.getMontePerdicion()){
    		return "Monte de la perdicion:\nTrae la chispa para ganar!";
    	}
    	
    	if(unidadSeleccionada==null){
    		if(cas.getUnidad()!=null && cas.getUnidad().es(enTurno.getEquipo())){
    			return "seleccionar";
    		}else{
    			return "";
    		}
    	}
    	
    	if(cas.getUnidad()!=null && cas.getUnidad().esDelMismoEquipo(unidadSeleccionada)){
    		return "seleccionar";
    	}
    	
    	if(!tablero.contiene(unidadSeleccionada))return "";
    	
   		Unidad u = unidadSeleccionada;
    	String accionPosible="Deseleccionar";
    	if(sePuedeTransformar(u)&&this.obtenerPosicion(u).obtenerPosicionEnElPlano().equals(posicion)) accionPosible="Transformar";
   		if(sePuedeMover(u,new Posicion(posicion,u.getPlanoPerteneciente()))&&
    		!this.obtenerPosicion(u).obtenerPosicionEnElPlano().equals(posicion))accionPosible ="Mover";
    	if((puedeAtacar(u,new Posicion(posicion,Posicion.Plano.AEREO)) ||
    		puedeAtacar(u, new Posicion(posicion, Posicion.Plano.TERRESTRE)))&&
    		!this.obtenerPosicion(u).obtenerPosicionEnElPlano().equals(posicion)) accionPosible="Atacar";
    	return accionPosible;
    	
    }
    
    public boolean sePuedeTransformar(Unidad u){
    	return tablero.sePuedeTransformar(u)&&enTurno.esDeSuEquipo(u);
    }
    public boolean puedeAtacar(Unidad atacante, Posicion posicionObjetivo){
    	return tablero.puedeAtacar(atacante,posicionObjetivo)&&enTurno.esDeSuEquipo(atacante);
    }
    
    public boolean sePuedeMover(Unidad unidad, Posicion posicionFinal){
    	return tablero.sePuedeMover(unidad, posicionFinal)&&enTurno.esDeSuEquipo(unidad);
    }
    
    public boolean posicionVacia(Posicion pos) {
    	return tablero.isEmpty(pos);
    }
    
    public Unidad unidadReferenciada(Casillero c){
    	if(c.getuAerea()!=null){
    		return c.getuAerea();
    	}else if(c.getuTerrestre()!=null){
    		return c.getuTerrestre();
    	}else{
    		return null;
    	}
    }
    
    
    
	public void cambiarUnidadSeleccionada(Unidad referenciada) {
		unidadSeleccionada=referenciada;
	}


	public Casillero construirCasillero(Posicion pos) {

		Posicion terrestre = pos.nuevaPosicionConDistintoPlano(Posicion.Plano.TERRESTRE);
		Posicion aerea = pos.nuevaPosicionConDistintoPlano(Posicion.Plano.AEREO);
		
		//ningun try, si no hay superficie estamos fritos
		Superficie supTerrestre = this.obtenerSuperficie(terrestre);
		Superficie supAerea = this.obtenerSuperficie(aerea);
		
		Unidad uTerrestre;
		try{
			uTerrestre = this.obtenerUnidad(terrestre);
		}catch(PosicionLibreException e){
			uTerrestre=null;
		}
		
		Unidad uAerea;
		try{
			uAerea = this.obtenerUnidad(aerea);
		}catch(PosicionLibreException e){
			uAerea=null;
		}
		
		boolean esChispa= (tablero.tieneChispa(terrestre));
		boolean esMonte = (tablero.getPosicionMontePerdicion().equals(terrestre));
		
		return new Casillero(supAerea,supTerrestre,pos,uAerea,uTerrestre,esChispa,esMonte);
	}
	public void agregarBonus(Bonus bonus, Posicion p) {
		tablero.agregarBonus(bonus, p);
	}


	public ArrayList<Bonus> obtenerBonuses() {
		return tablero.obtenerBonuses();
	}


	public Posicion posicion(Bonus b) {
		return tablero.posicion(b);
	}


	public boolean termino() {
		return (this.jugadorGanadorEs(enEspera)||this.jugadorGanadorEs(enTurno));
	}


	public boolean hayCombinacion(Equipo equipo) {
		return tablero.tieneCombinacion(equipo);
		
	}


	public Posicion posicionChispa() {
		return tablero.getPosicionChispa();
	}


	public Posicion posicionMontePerdicion() {
		return tablero.getPosicionMontePerdicion();
	}


	public String obtenerNombreImagenMontePerdicion() {
		return "/fiuba/algo3/vista/imagenes/tierra/montePerdicion.png";
	}


	public boolean puedeHacerAlgo(Unidad unidad) {
		return unidad.puedeAtacar() || sePuedeTransformar(unidad) || unidad.puedeMoverseUnCasillero();
	}


	public boolean enTablero(Unidad u) {
		return tablero.contiene(u);
	}


	public ArrayList<PosicionEnElPlano> obtenerHaloMovimiento(Unidad u) {
		return tablero.obtenerHaloMovimiento(u);
	}


	public ArrayList<PosicionEnElPlano> obtenerHaloAtaque(Unidad u) {
		return tablero.obtenerHaloAtaque(u);
	}
}
