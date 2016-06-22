/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.Tablero;

/**
 *
 * @author brahvic
 */
public class VictoriaMontePerdicion implements WinListener{

    private Tablero tablero;
    private Juego juego;

	@Override
    public void gano(Equipo equipo) {

        if  ( (this.tablero.unidadesContieneChispa(equipo)) 
            && (this.chispaSeEncuentraEnMonterPerdicion())) {
            this.juego.jugadorGanador(equipo);
        }
    }

    @Override
    public void perdio(Equipo equipo) {
        if( ! this.tablero.existenUnidadeDeEquipo(equipo) ){
            this.juego.jugadorDerrotado(equipo) ;
            
        }
    }

    private boolean chispaSeEncuentraEnMonterPerdicion() {
        return this.tablero.getPosicionChispa().equals(this.tablero.getPosicionMontePerdicion()) ;
    }

    public void setJuego(Juego juego) {
        this.juego = juego ;
    }

    public void setTablero(Tablero tab) {
        this.tablero = tab ;
    }
    
    
    
}
