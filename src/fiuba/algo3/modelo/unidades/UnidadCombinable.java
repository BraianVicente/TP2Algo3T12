/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;

/**
 *
 * @author brahvic
 */
public abstract class UnidadCombinable extends Unidad {

    private Integer turnosCreacion ;
    public UnidadCombinable(Equipo equipo, DeathListener command) {
        super(equipo, command);
        this.turnosCreacion = 2 ;
    }

    public boolean creacionFinalizada(){
        if (this.turnosCreacion > 0 ){
            this.turnosCreacion-- ;
            return false ;
        }
        return true ;       
        
    }
    
    @Override
    public boolean existe(){
        return (this.turnosCreacion == 0) ;
    }
    
}
