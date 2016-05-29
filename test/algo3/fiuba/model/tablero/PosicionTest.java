/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algo3.fiuba.model.tablero;

import fiuba.algo3.modelo.tablero.Posicion;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author brahvic
 */
public class PosicionTest {
    
    @Test
    public void test01CrearPosicion(){
        Posicion pos = new Posicion(1,5);
        Assert.assertTrue(pos.equals(new Posicion(1,5)));      
    }
    
    @Test
    public void test02PosicionEsDistinta(){
        Posicion pos = new Posicion(1,5);
        Assert.assertFalse(pos.equals(new Posicion(0,0)));
    }
    
    @Test
    public void test03PosicionADiscanciaCorrectaConXYIguales(){
        Posicion pos = new Posicion(0,0);
        Assert.assertTrue(pos.distanciaA(new Posicion(2,2)).equals(2));
    }
    
    @Test
    public void test04PosicionADiscanciaCorrectaConXYDistintos(){
        Posicion pos = new Posicion(13,4);
        Assert.assertTrue(pos.distanciaA(new Posicion(1,3)).equals(12));
    }
    
    @Test
    public void test05PosicionADiscanciaIncorrectaConXY(){
        Posicion pos = new Posicion(0,0);
        Assert.assertFalse(pos.distanciaA(new Posicion(2,3)).equals(2));
    }
    
    @Test
    public void test06PosicionesIgualesDistanciaCero(){
        Posicion pos = new Posicion(13,4);
        Assert.assertTrue(pos.distanciaA(new Posicion(13,4)).equals(0));
    }
    
}
