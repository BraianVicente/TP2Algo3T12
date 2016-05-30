/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Ninguno;
import fiuba.algo3.modelo.tablero.Posicion;

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
    public boolean puedeAtacar(Posicion a, Posicion desde) {
        return false;
    }

    @Override
    public boolean puedeMoverse(Posicion a, Posicion desde) {
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
}
