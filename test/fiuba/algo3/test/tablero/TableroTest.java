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
import fiuba.algo3.modelo.unidadesVivientes.Bonecrusher;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;
import fiuba.algo3.modelo.unidadesVivientes.Frenzy;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Megatron;
import fiuba.algo3.modelo.unidadesVivientes.Menasor;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Optimusprime;
import fiuba.algo3.modelo.unidadesVivientes.Ratchet;
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
        Bumblebee bum= new Bumblebee();
        tab.agregarUnidad(new Posicion(5,5),bum);    
        tab.mover(bum, new Posicion(6,6));
        Assert.assertTrue(tab.isEmpty(new Posicion(5,5)));
        Assert.assertFalse(tab.isEmpty(new Posicion(6,6)));
    }
    
    @Test(expected=MovimientoInvalidoException.class)
    public void test05NoSePuedeMoverAPosicionOcupada(){
        Tablero tab = new Tablero();
        Bumblebee bum=new Bumblebee();
        tab.agregarUnidad(new Posicion(5,5), bum);
        tab.agregarUnidad(new Posicion(6,6),new Frenzy());    
        tab.mover(bum, new Posicion(6,6));
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
    		tab.atacar(tron,bee);
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
    		tab.atacar(tron, bee);
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
    
    @Test
    public void test09CombinacionSuperionConChispa() {
    	Tablero tab = new Tablero();
    	Posicion posa = new Posicion(5, 5), posb = new Posicion(6,6), posc = new Posicion(6,5);
    	Optimusprime op = new Optimusprime();
    	Bumblebee bee = new Bumblebee();
    	Ratchet rat = new Ratchet();
    	
    	op.darChispa();
    	
    	tab.agregarUnidad(posa, op);
    	tab.agregarUnidad(posb, bee);
    	tab.agregarUnidad(posc, rat);
    	
    	tab.combinar(posa, posb, posc);
    	
    	Assert.assertEquals(true, tab.isEmpty(posb));
    	Assert.assertEquals(true, tab.isEmpty(posc));
    	Assert.assertEquals(true, tab.obtenerUnidad(posa) instanceof Superion);
    	Assert.assertEquals(true, tab.obtenerUnidad(posa).tieneChispa());
    }
    
    @Test
    public void test10CombinacionMenasorSinChispa() {
    	Tablero tab = new Tablero();
    	Posicion posa = new Posicion(5, 5), posb = new Posicion(6,6), posc = new Posicion(6,5);
    	Megatron mega = new Megatron();
    	Bonecrusher bone = new Bonecrusher();
    	Frenzy fren = new Frenzy();
    	
    	tab.agregarUnidad(posa, mega);
    	tab.agregarUnidad(posb, bone);
    	tab.agregarUnidad(posc, fren);
    	
    	tab.combinar(posa, posb, posc);
    	
    	Assert.assertEquals(true, tab.isEmpty(posb));
    	Assert.assertEquals(true, tab.isEmpty(posc));
    	Assert.assertEquals(false, tab.obtenerUnidad(posa) instanceof Superion);
    	Assert.assertEquals(true, tab.obtenerUnidad(posa) instanceof Menasor);
    }
    
    @Test
    public void test11CombinacionMenasorConChispa() {
    	Tablero tab = new Tablero();
    	Posicion posa = new Posicion(5, 5), posb = new Posicion(6,6), posc = new Posicion(6,5);
    	Megatron mega = new Megatron();
    	Bonecrusher bone = new Bonecrusher();
    	Frenzy fren = new Frenzy();
    	
    	tab.agregarUnidad(posa, mega);
    	tab.agregarUnidad(posb, bone);
    	tab.agregarUnidad(posc, fren);
    	
    	mega.darChispa();
    	
    	tab.combinar(posa, posb, posc);
    	
    	Assert.assertEquals(true, tab.isEmpty(posb));
    	Assert.assertEquals(true, tab.isEmpty(posc));
    	Assert.assertEquals(false, tab.obtenerUnidad(posa) instanceof Superion);
    	Assert.assertEquals(true, tab.obtenerUnidad(posa) instanceof Menasor);
    	Assert.assertEquals(true, tab.obtenerUnidad(posa).tieneChispa());
    }
}

