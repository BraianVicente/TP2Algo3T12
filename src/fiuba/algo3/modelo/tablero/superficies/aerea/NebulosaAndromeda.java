/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.superficies.aerea;

import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.FormaHumanoide;
import fiuba.algo3.modelo.formas.FormaVehiculo;
import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;

/**
 *
 * @author brahvic
 */
public class NebulosaAndromeda extends Superficie {

	public void afectarA(Unidad unidadConVida) {
		unidadConVida.serAfectadoPor(this);
		
	}

	@Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/cielo/nebulosa.png";
	}

	@Override
	public float obtenerVelocidadParaForma(Forma forma) {
		return (new ModificadorNebulosa()).coeficienteVelocidadPorForma(forma);
	}
	
	@Override
	public float obtenerVelocidadParaForma(FormaVehiculo forma) {
		return (new ModificadorNebulosa()).coeficienteVelocidadPorForma(forma);
	}
	
	@Override
	public float obtenerVelocidadParaForma(FormaHumanoide forma) {
		return (new ModificadorNebulosa()).coeficienteVelocidadPorForma(forma);
	}

    @Override
    public String efecto() {
        return "Nebulosa de Andromeda, aqui la unidad aerea quedara retenira durante 3 turnos" ;
    }

}
