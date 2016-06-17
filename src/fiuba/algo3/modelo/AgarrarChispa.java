/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Tablero;

/**
 *
 * @author brahvic
 */
public class AgarrarChispa implements WinListener {

    private Tablero tablero;
    private Juego juego;

    public AgarrarChispa(){
        
    }    
    public AgarrarChispa(Tablero tab,Juego juego){
        this.tablero = tab ;
        this.juego = juego ;
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
