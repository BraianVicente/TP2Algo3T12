/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.test.tablero;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.Death;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.AtaqueInvalidoPorDistanciaException;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;
import fiuba.algo3.modelo.unidadesVivientes.Frenzy;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Megatron;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Optimusprime;
import fiuba.algo3.modelo.unidadesVivientes.Ratchet;
import fiuba.algo3.test.unidadesVivientes.MentiTron;
import fiuba.algo3.modelo.unidadesVivientes.Superion;

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
    
    @Test
    public void test06UnitDeathSinChispa() throws FriendlyFireException, AtaqueInvalidoPorDistanciaException, NoSeEncuentraUnidadException {
    	Tablero tab = new Tablero();
    	Death command = new Death(tab); 
    	Posicion posB = new Posicion(5, 5);
    	Posicion posM = new Posicion(4, 4);
    	Bumblebee bee = new Bumblebee(command);
    	Megatron tron = new Megatron(command);
    	tab.agregarUnidad(posB, bee);
    	tab.agregarUnidad(posM, tron);
    	while(bee.getVida()>0)
    		tron.atacarA(bee, posB, posM);
    	Assert.assertEquals(true, tab.isEmpty(posB));
    }
    
    @Test
    public void test07UnitDeathConChispa() throws FriendlyFireException, AtaqueInvalidoPorDistanciaException, NoSeEncuentraUnidadException {
    	Tablero tab = new Tablero();
    	Posicion pos = new Posicion(5, 5);
    	Death command = new Death(tab); 
    	
    	Bumblebee bee = new Bumblebee(command);
    	Megatron tron = new Megatron(command);
    	Posicion posM = new Posicion(4, 4);
    	bee.darChispa();
    	tab.agregarUnidad(pos, bee);
    	tab.agregarUnidad(posM, tron);
    	
    	while(bee.getVida()>0)
    		tron.atacarA(bee, pos, posM);
    	Assert.assertEquals(true, tab.isEmpty(pos));
    	Assert.assertEquals(true, tab.tieneChispa(pos));
    }
    
    @Test
    public void test08CombinacionSuperionSinChispa() {
    	Tablero tab = new Tablero();
    	Posicion posa = new Posicion(5, 5), posb = new Posicion(6,6), posc = new Posicion(6,5);
    	Optimusprime op = new Optimusprime();
    	Bumblebee bee = new Bumblebee();
    	Ratchet rat = new Ratchet();
    	
    	tab.agregarUnidad(posa, op);
    	tab.agregarUnidad(posb, bee);
    	tab.agregarUnidad(posc, rat);
    	
    	tab.combinar(posa, posb, posc);
    	
    	Assert.assertEquals(true, tab.isEmpty(posb));
    	Assert.assertEquals(true, tab.isEmpty(posc));
    	Assert.assertEquals(true, tab.obtenerUnidad(posa) instanceof Superion);
    }
}

