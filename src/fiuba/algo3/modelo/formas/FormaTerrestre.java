/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.formas;

/**
 *
 * @author brahvic
 */
public abstract class FormaTerrestre extends FormaVehiculo {
	@Override
    public float coeficienteMovimientoEnPantano(){
		return 0;
	}
	@Override
	public float danioRealPorEspinas(float danioPosible){
		return danioPosible;
	}

    
}
