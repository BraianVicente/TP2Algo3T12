/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.test.tablero;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;
import fiuba.algo3.modelo.unidadesVivientes.Frenzy;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author brahvic
 */
public class TableroTest {
        
    @Test
    public void test01crearTablero(){
        Tablero tablero = new Tablero();
        Assert.assertTrue(tablero.isEmpty(new Posicion(0,0)));
    }
    
    @Test
    public void test02ocuparPosicionTablero(){
        Tablero tab = new Tablero();
        tab.agregarUnidad(new Posicion(2,2),new Bumblebee());
        Assert.assertFalse(tab.isEmpty(new Posicion(2,2)));
    }
    
    @Test(expected=PosicionOcupadaException.class)
    public void test03PosicionOcupadaNoSePuedeOcupar(){
        Tablero tab = new Tablero();
        tab.agregarUnidad(new Posicion(5,5), new Bumblebee());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5)));
        tab.agregarUnidad(new Posicion(5,5),new Frenzy());
    }
    
    @Test
    public void test04SeMueveUnidad(){
        Tablero tab = new Tablero();
        tab.agregarUnidad(new Posicion(5,5), new Bumblebee());    
        tab.mover(new Posicion(5,5), new Posicion(6,6));
        Assert.assertTrue(tab.isEmpty(new Posicion(5,5)));
        Assert.assertFalse(tab.isEmpty(new Posicion(6,6)));
    }
    
    @Test(expected=MovimientoInvalidoException.class)
    public void test05NoSePuedeMoverAPosicionOcupada(){
        Tablero tab = new Tablero();
        tab.agregarUnidad(new Posicion(5,5), new Bumblebee());
        tab.agregarUnidad(new Posicion(6,6),new Frenzy());    
        tab.mover(new Posicion(5,5), new Posicion(6,6));
        Assert.fail("No se lanzo la excepcion como debia ser");
    }
    

    

}

