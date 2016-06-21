/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import java.util.ArrayList;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.bonuses.BonusBurbuja;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
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
            System.out.println("lalal");
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
		return (0<=p.getX() && p.getX()<tablero.obtenerAncho()) &&
				(0<=p.getY() && p.getY()<tablero.obtenerAlto());
	}
	
	public boolean enTablero(PosicionEnElPlano p) {
		return (0<=p.getX() && p.getX()<tablero.obtenerAncho()) &&
				(0<=p.getY() && p.getY()<tablero.obtenerAlto());
	}
    
    public boolean jugadorGanadorEs(Jugador jugador) {
        return jugador.equals(this.ganador) ;
    }

    public void jugadorGanador(Equipo equipo) {
        if (equipo.equals(jugadorDecepticons.getEquipo())) {
            ganador = jugadorDecepticons;
        } else {
            ganador = jugadorAutobots;
        }
    }

    public void jugadorDerrotado(Equipo equipo) {
        this.ganador = this.enTurno ;
    }
    
    public String accionPosibleEn(PosicionEnElPlano posicion){
    	if(unidadSeleccionada!=null && enTablero(posicion) ){
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
    	
    	return "";
    	
    }
    
    public boolean sePuedeTransformar(Unidad u){
    	return tablero.sePuedeTransformar(u);
    }
    public boolean puedeAtacar(Unidad atacante, Posicion posicionObjetivo){
    	return tablero.puedeAtacar(atacante,posicionObjetivo);
    }
    
    public boolean sePuedeMover(Unidad unidad, Posicion posicionFinal){
    	return tablero.sePuedeMover(unidad, posicionFinal);
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
    
    public void clickeoCasillero(Casillero c,CanvasJuego canvas){
    	//ac� estoy suponiendo que siempre que to�s una unidad la quer�s seleccionar,
    	Unidad referenciada = unidadReferenciada(c);
    	
    	if(unidadSeleccionada == null){
    		unidadSeleccionada =referenciada;
    		//canvas.setHaloAtaque(tablero.posicionesAtacables(unidadSeleccionada));
        	//canvas.setHaloMovimiento(tablero.posicionesMovimiento(unidadSeleccionada));
    	}else{
    		//if(sePuedeAtacar...)atacar(...)
    		//if(sePuedeMover...)mover(...)
    		//if(sePuedeTransformar(...))transformar(...)
    		//Estar�a muy muy lindo una interfaz acci�n ac� pero no hay T
    		
    		canvas.setHaloAtaque(null);
        	canvas.setHaloMovimiento(null);
    	}
    	
    	canvas.seleccionadorEn(c.getPos());
    	//CanvasJuego s�lo sabe de dibujar cositas en la pantalla
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
		
		return new Casillero(supAerea,supTerrestre,pos,uAerea,uTerrestre);
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
}
