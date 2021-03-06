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
import fiuba.algo3.modelo.modificadores.ModificadorPsionica;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;

/**
 *
 * @author brahvic
 */
public class TormentaPsionica extends Superficie{

    @Override
	public void afectarA(Unidad unidadConVida) {
		unidadConVida.serAfectadoPor(this);
		
	}
	
	@Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/cielo/tormenta.png";
	}

	@Override
	public float obtenerVelocidadParaForma(Forma forma) {
		return (new ModificadorPsionica()).coeficienteVelocidadPorForma(forma);
	}
	
	@Override
	public float obtenerVelocidadParaForma(FormaVehiculo forma) {
		return (new ModificadorPsionica()).coeficienteVelocidadPorForma(forma);
	}
	
	@Override
	public float obtenerVelocidadParaForma(FormaHumanoide forma) {
		return (new ModificadorPsionica()).coeficienteVelocidadPorForma(forma);
	}

    @Override
    public String efecto() {
        return "Tormenta Psionica:las unidades aereas veran reducido su poder de ataque al %40";
    }
}
