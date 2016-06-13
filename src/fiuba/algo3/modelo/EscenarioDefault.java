/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;

/**
 *
 * @author brahvic
 */
public class EscenarioDefault implements Escenario {

   
    @Override
    public Integer getLargoEscenario() {
        return 10 ;
    }

    @Override
    public Integer getAnchoEscenario() {
        return 10 ;
    }

    @Override
    public Superficie agregarSuperficieTerrestre() {
        return new Rocosa();
    }

    @Override
    public Superficie agregarSuperficieAerea() {
        return new Nubes();
    }

    @Override
    public Superficie agregarSuperficiesAleatoria(Integer x, Integer y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
