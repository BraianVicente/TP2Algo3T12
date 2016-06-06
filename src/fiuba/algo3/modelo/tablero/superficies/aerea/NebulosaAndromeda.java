/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.superficies.aerea;

import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

/**
 *
 * @author brahvic
 */
public class NebulosaAndromeda extends Superficie {

	public void afectarA(UnidadConVida unidadConVida) {
		unidadConVida.serAfectadoPor(this);
		
	}

}
