/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.superficies;


import fiuba.algo3.modelo.unidades.Unidad;

/**
 *
 * @author brahvic
 */
public abstract class Superficie {
    
    public Superficie(){ }

    
	public void afectarA(Unidad unidadConVida) {
		unidadConVida.serAfectadoPor(this);
	}
	
	public abstract String nombreImagen(); 

}
