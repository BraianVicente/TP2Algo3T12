/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Tablero;

/**
 *
 * @author brahvic
 */
public class VictoriaMontePerdicion implements WinListener{

    private Tablero tablero;
    private Juego juego;

    
    
    private boolean gano(Equipo equipo) {
        return ( (this.tablero.unidadesContieneChispa(equipo)) 
            && (this.chispaSeEncuentraEnMonterPerdicion())||this.perdio(equipo.obtenerEquipoContrario()));
       
    }

    private boolean perdio(Equipo equipo) {
       return ! this.tablero.existenUnidadeDeEquipo(equipo)    ;
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

	@Override
	public Equipo determinarEquipoGanador() {
		if(!alguienGano()) throw new NoGanoNadieException();
		if(gano(new Autobots())) return new Autobots();
		else return new Decepticons();
	}

	@Override
	public boolean alguienGano() {
		return gano(new Autobots())||gano(new Decepticons());
	}
    
    
    
}
