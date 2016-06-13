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

/**
 *
 * @author brahvic
 */
public class Juego {
    
    
    private Jugador jugadorDecepticons ;
    private Jugador jugadorAutobots ;
    private Jugador turnoDe ;
    private Tablero tablero ;
    private WinListener condVictoria ;
    private Jugador ganador;
    
    public Juego(Tablero tab,Jugador autobots,Jugador decepticons){
        // el juego debe iniciarse vacio, ir agregando primero los jugadores
        // y una vez tenga el tablero, las unidades en sus correspondientes cuadrantes
        this.tablero = tab;
        this.tablero.colocarChispa();
        this.jugadorAutobots = autobots;
        this.jugadorDecepticons = decepticons;
        this.turnoDe = autobots;
    }
    
    public Juego(Tablero tab,Jugador autobots,Jugador decepticons,WinListener victoria){
        this.tablero = tab;
        this.tablero.colocarChispa();
        this.jugadorAutobots = autobots;
        this.jugadorDecepticons = decepticons;
        this.turnoDe = autobots;
        this.condVictoria = victoria;
    }
    
    public Juego(){
        
    }
    
    public void crearTablero(Escenario escenarioCreator){
        this.tablero = new Tablero(escenarioCreator);
        this.tablero.colocarChispa();
    }
    
    public void agregarJugadorDecepticons(String nombre,Tablero tablero){
        this.jugadorDecepticons = new Jugador(nombre,new Decepticons());
        // La idea es cambiarlo para que el jugador lo agrege aleatoriamente 
        // en una zona predeterminada segun el tamanio del tablero
        // se va a agregar el comportamiento con nuevos Tableros
        tablero.agregarUnidad(new Posicion(0,0), new Megatron());
        tablero.agregarUnidad(new Posicion(2,2), new Bonecrusher());
        tablero.agregarUnidad(new Posicion(3,1), new Frenzy());
        
    }
    
    public void agregarJugadorAutobots(String nombre, Tablero tablero){
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
        if(this.turnoDe.esDerrotado() || victorioso.esVictorioso()){
            this.ganador = victorioso ;
            // throw new JuegoFinalizadoException(Jugador ganador) ;
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
        this.jugadorEnTurno().esVictorioso();
    }

    public void atacarUnidad(Posicion origen, Posicion destino){
        if (turnoDe.perteneceEquipo(tablero.obtenerUnidad(origen))){
            tablero.atacar(tablero.obtenerUnidad(origen), tablero.obtenerUnidad(destino));
        }
        this.jugadorEnTurno().esVictorioso();
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
    
}
