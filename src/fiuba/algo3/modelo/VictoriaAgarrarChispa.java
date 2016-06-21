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
    
    public boolean perdio(Equipo equipo) {
        return ! this.tablero.existenUnidadeDeEquipo(equipo)  ;
            
       
    }

    public boolean gano(Equipo equipo) {
       return this.tablero.unidadesContieneChispa(equipo)||this.perdio(equipo.obtenerEquipoContrario());
        
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
