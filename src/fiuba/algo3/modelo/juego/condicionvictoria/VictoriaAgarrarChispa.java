/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.juego.condicionvictoria;

import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Tablero;

/**
 *
 * @author brahvic
 */
public class VictoriaAgarrarChispa implements WinListener {

    private Tablero tablero;
    private Juego juego;


    public VictoriaAgarrarChispa(Tablero tab,Juego juego){
        this.tablero = tab ;
        this.juego = juego ;
    }

    public VictoriaAgarrarChispa() {
    }
    
    public void setTablero(Tablero tab){
        this.tablero = tab ;
    }
    
    public void setJuego(Juego juego){
        this.juego = juego ;
    }
    
    @Override
    public void perdio(Equipo equipo) {
        if( ! this.tablero.existenUnidadeDeEquipo(equipo) ){
            this.juego.jugadorDerrotado(equipo) ;
            
        }
    }

    @Override
    public void gano(Equipo equipo) {
        if (this.tablero.unidadesContieneChispa(equipo)){
            this.juego.jugadorGanador(equipo);
        }
    }
    
    
}
