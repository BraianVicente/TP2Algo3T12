/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.test.tablero;

import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.PosicionEnElPlano;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


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
    
    @Test
    public void test07RectaQuePasaPor55YPor66SoloDebeTocarDichasPosiciones(){
        Posicion pos1 = new Posicion(5,5);
        Posicion pos2 = new Posicion(6,6);
        List<Posicion> posiciones=pos1.posicionesQueTocaLaRectaQueVaA(pos2);
        Assert.assertTrue(posiciones.size()==2);
        Assert.assertTrue(posiciones.contains(pos1)&&posiciones.contains(pos2));
    }
    
    @Test
    public void test08RectaQuePasaPor65YPor56SoloDebeTocarDichasPosiciones(){
        Posicion pos1 = new Posicion(6,5);
        Posicion pos2 = new Posicion(5,6);
        List<Posicion> posiciones=pos1.posicionesQueTocaLaRectaQueVaA(pos2);
        Assert.assertTrue(posiciones.size()==2);
        Assert.assertTrue(posiciones.contains(pos1)&&posiciones.contains(pos2));
    }
    @Test
    public void test09RectaQuePasaPor65YPor56SoloDebeTocarDichasPosicionesInvertido(){
        Posicion pos1 = new Posicion(5,6);
        Posicion pos2 = new Posicion(6,5);
        List<Posicion> posiciones=pos1.posicionesQueTocaLaRectaQueVaA(pos2);
        Assert.assertTrue(posiciones.size()==2);
        Assert.assertTrue(posiciones.contains(pos1)&&posiciones.contains(pos2));
    }
    @Test
    public void test10RectaQuePasaPor00YPor00SoloDebeEsPosicion(){
        Posicion pos1 = new Posicion(0,0);
        Posicion pos2 = new Posicion(0,0);
        List<Posicion> posiciones=pos1.posicionesQueTocaLaRectaQueVaA(pos2);
        Assert.assertTrue(posiciones.size()==1);
        Assert.assertTrue(posiciones.contains(pos1));
    }
    
    @Test
    public void test11RectaQuePasaPor00YPor90SoloDebeTocarPosicionesCorrespondientes(){
        Posicion pos1 = new Posicion(0,0);
        Posicion pos2 = new Posicion(9,0);
        List<Posicion> posiciones=pos1.posicionesQueTocaLaRectaQueVaA(pos2);
        Assert.assertTrue(posiciones.size()==10);
        for(int i=0;i<=9;i++){
        	 Assert.assertTrue(posiciones.contains(pos1.obtenerMismaPosicionDesplazada(i, 0)));
        }
    }
    @Test
    public void test11RectaQuePasaPor00YPor24SoloDebeTocarPosicionesCorrespondientes(){
        Posicion pos1 = new Posicion(0,0);
        Posicion pos2 = new Posicion(2,4);
        List<Posicion> posiciones=pos1.posicionesQueTocaLaRectaQueVaA(pos2);
        Assert.assertTrue(posiciones.size()==7);
        Assert.assertTrue(posiciones.contains(new Posicion(0,0)));
        Assert.assertTrue(posiciones.contains(new Posicion(0,1)));
        Assert.assertTrue(posiciones.contains(new Posicion(1,1)));
        Assert.assertTrue(posiciones.contains(new Posicion(1,2)));
        Assert.assertTrue(posiciones.contains(new Posicion(1,3)));
        Assert.assertTrue(posiciones.contains(new Posicion(2,3)));
        Assert.assertTrue(posiciones.contains(new Posicion(2,4)));
        
    }
}
