/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.superficies.Superficie;

/**
 *
 * @author brahvic
 */
public interface Escenario {

    public Integer getLargoEscenario();
    public Integer getAnchoEscenario();
    
    public Superficie agregarSuperficieAerea();
    public Superficie agregarSuperficieTerrestre();
    public Superficie agregarSuperficieAereaAleatoria(Integer posX, Integer posY);
    public Superficie agregarSuperficieTerrestreAleatoria(Integer posX, Integer posY);
    
}
