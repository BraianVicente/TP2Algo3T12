/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.superficies.terrestre;

import fiuba.algo3.modelo.formas.FormaTerrestre;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;

/**
 *
 * @author brahvic
 */
public class Pantano extends Superficie {

    @Override
    public void aplicarPenalizacion(Transformer unidad) {
        if (unidad.esTerrestre() ){
            if ( unidad.esVehiculo()){
                FormaTerrestre terrestre = (FormaTerrestre)unidad.getFormaActual();
                terrestre.movimientoPenalizado();
            } 
            throw new MovimientoInvalidoException() ;
        }
    }
    
}
