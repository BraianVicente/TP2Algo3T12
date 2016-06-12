/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Bonecrusher;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Frenzy;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.modelo.unidades.Ratchet;
import fiuba.algo3.modelo.unidades.Unidad;

/**
 *
 * @author brahvic
 */
public class Juego {
    
    
    private Jugador jugadorDecepticons ;
    private Jugador jugadorAutobots ;
    private Jugador turnoDe ;
    private Tablero tablero ;

    public Juego(Tablero tab,Jugador autobots,Jugador decepticons){
        // el juego debe iniciarse vacio, ir agregando primero los jugadores
        // y una vez tenga el tablero, las unidades en sus correspondientes cuadrantes
        this.tablero = tab;
        this.tablero.agregarChispa(new Posicion(5,5));
        this.jugadorAutobots = autobots;
        this.jugadorDecepticons = decepticons;
        this.turnoDe = autobots;
    }
    
    public void crearTablero(){
        this.tablero = new Tablero();
        tablero.agregarChispa(new Posicion(5,5));
    }
    
    public void agregarJugadorDecepticons(String nombre){
        this.jugadorDecepticons = new Jugador(nombre,new Decepticons());
        // La idea es cambiarlo para que el jugador lo agrege aleatoriamente 
        // en una zona predeterminada segun el tamanio del tablero
        // se va a agregar el comportamiento con nuevos Tableros
        tablero.agregarUnidad(new Posicion(0,0), new Megatron());
        tablero.agregarUnidad(new Posicion(2,2), new Bonecrusher());
        tablero.agregarUnidad(new Posicion(3,1), new Frenzy());
        
    }
    
    public void agregarJugadorAutobots(String nombre){
        this.jugadorAutobots = new Jugador(nombre,new Decepticons());
        // La idea es cambiarlo para que el jugador lo agrege aleatoriamente 
        // en una zona predeterminada segun el tamanio del tablero
        // se va a agregae el comportamiento con nuevos tableros
        tablero.agregarUnidad(new Posicion(8,9), new Optimusprime());
        tablero.agregarUnidad(new Posicion(8,8), new Bumblebee());
        tablero.agregarUnidad(new Posicion(7,8), new Ratchet());
    }
    
    public void avanzarTurno(){
        Jugador victorioso = this.jugadorEnTurno();
        this.cambiarTurno();
        if(this.turnoDe.derrotado()){
            throw new VictoriaException(victorioso);
        }
        this.turnoDe.pasarTurno();
        
       
    }

    public Jugador jugadorEnTurno(){
        return turnoDe;
    }
    
    public void moverUnidad(Posicion origen,Posicion destino){
        if (turnoDe.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.mover(tablero.obtenerUnidad(origen), destino);
        }
    }

    public void atacarUnidad(Posicion origen, Posicion destino){
        if (turnoDe.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.atacar(tablero.obtenerUnidad(origen), tablero.obtenerUnidad(destino));
        }
    }
    
    public void transformarUnidad(Posicion origen){
        if (turnoDe.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.transformar(tablero.obtenerUnidad(origen));
        }
    }
    
    public void combinarUnidades(){
        turnoDe.combinarUnidades();
    }

    public void cambiarTurno() {
        if (turnoDe.equals(jugadorDecepticons)) {
            turnoDe = jugadorAutobots;
        } else {
            turnoDe = jugadorDecepticons;
        }     
    }

	public Unidad obtenerUnidad(Posicion posActual) {
		return tablero.obtenerUnidad(posActual);
		
	}
    
}
