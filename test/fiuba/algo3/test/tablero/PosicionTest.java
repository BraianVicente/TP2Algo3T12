/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.test.tablero;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.PosicionEnElPlano;

/**
 *
 * @author brahvic
 */
public class PosicionTest {
    
    @Test
    public void test01CrearPosicion(){
        PosicionEnElPlano pos = new PosicionEnElPlano(1,5);
        Assert.assertTrue(pos.equals(new PosicionEnElPlano(1,5)));      
    }
    
    @Test
    public void test02PosicionEsDistinta(){
        PosicionEnElPlano pos = new PosicionEnElPlano(1,5);
        Assert.assertFalse(pos.equals(new PosicionEnElPlano(0,0)));
    }
    
    @Test
    public void test03PosicionADiscanciaCorrectaConXYIguales(){
        PosicionEnElPlano pos = new PosicionEnElPlano(0,0);
        Assert.assertTrue(pos.distanciaA(new PosicionEnElPlano(2,2)).equals(2));
    }
    
    @Test
    public void test04PosicionADiscanciaCorrectaConXYDistintos(){
        PosicionEnElPlano pos = new PosicionEnElPlano(13,4);
        Assert.assertTrue(pos.distanciaA(new PosicionEnElPlano(1,3)).equals(12));
    }
    
    @Test
    public void test05PosicionADiscanciaIncorrectaConXY(){
        PosicionEnElPlano pos = new PosicionEnElPlano(0,0);
        Assert.assertFalse(pos.distanciaA(new PosicionEnElPlano(2,3)).equals(2));
    }
    
    @Test
    public void test06PosicionesIgualesDistanciaCero(){
        PosicionEnElPlano pos = new PosicionEnElPlano(13,4);
        Assert.assertTrue(pos.distanciaA(new PosicionEnElPlano(13,4)).equals(0));
    }
    
}
