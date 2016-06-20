/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.superficies;

import fiuba.algo3.modelo.tablero.superficies.Superficie;

/**
 *
 * @author brahvic
 */
public class MontePerdicion extends Superficie {

    public MontePerdicion() {
    }

    @Override
    public String nombreImagen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String efecto() {
        return "Monte de la Perdicion: el primer jugador en colocar aqui la chispa suprema gana el juego" ;
    }
    
}
