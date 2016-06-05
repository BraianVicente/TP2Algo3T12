/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Ninguno;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;

/**
 *
 * @author brahvic
 */
public class UnidadVacia extends Unidad {

    public UnidadVacia() {
        super(new Ninguno());
    }

    @Override
    public boolean existe() {
        return false;
    }

    @Override
    public boolean puedeAtacar(PosicionEnElPlano a, PosicionEnElPlano desde) {
        return false;
    }

    @Override
    public boolean puedeMoverse(PosicionEnElPlano a, PosicionEnElPlano desde) {
        return false;
    }

    @Override
    public void recibirDanio(Unidad atacante, int danio) {
    }
    //no hace nada!

    @Override
    public boolean tieneChispa() {
        return false;
    }

    @Override
    public Forma getFormaActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public int getVida() {
		return 0;
	}
}
