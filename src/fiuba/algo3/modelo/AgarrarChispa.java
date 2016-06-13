/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Tablero;

/**
 *
 * @author brahvic
 */
public class AgarrarChispa implements WinListener {

    @Override
    public boolean victoria(Tablero tab, Equipo equipo) {
       return tab.unidadesContieneChispa(equipo);
    }

    @Override
    public boolean derrotado(Tablero tab,Equipo equipo) {
        return !tab.existenUnidadeDeEquipo(equipo) ;
    }
    
    
}
