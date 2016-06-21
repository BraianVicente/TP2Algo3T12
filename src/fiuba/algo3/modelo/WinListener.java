/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.jugador.Jugador;

/**
 *
 * @author brahvic
 */
public interface WinListener {
    

	public Equipo determinarEquipoGanador();
	public boolean alguienGano();
    
}
