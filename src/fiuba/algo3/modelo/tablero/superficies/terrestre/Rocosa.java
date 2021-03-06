/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.superficies.terrestre;

import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;

/**
 *
 * @author brahvic
 */
public class Rocosa extends Superficie {

        @Override
	public void afectarA(Unidad unidadConVida) {
		unidadConVida.serAfectadoPor(this);
		
	}
        
        @Override
    	public String nombreImagen() {
    		return "/fiuba/algo3/vista/imagenes/tierra/piedras.png";
    	}

    @Override
    public String efecto() {
        return "Rocas: ninguna unidad se vera afectada";    
    }

}
