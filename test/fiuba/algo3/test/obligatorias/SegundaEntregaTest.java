package fiuba.algo3.test.obligatorias;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidades.Ratchet;

public class SegundaEntregaTest {

    @Test
    public void testBumblebeeAtraviesaRocosaSinProblemas() {
        Tablero t = new Tablero();
        for(int x = 0; x<10;x++){
            t.configurarSuperficie(new Posicion(x,8), new Rocosa());
        }
        Bumblebee bee = new Bumblebee();
        t.agregarUnidad(new Posicion(5,7), bee);
        t.mover(bee, new Posicion(5,9));
        Assert.assertFalse(t.isEmpty(new Posicion(5,9)));
    }
        
        
    @Test
    public void testRatchetAtraviezanZonaEspinaSinProblemas(){

        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new Espinas());
        }
        Ratchet rat = new Ratchet(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,5,Plano.AEREO),rat );
        tab.mover(rat,new Posicion(5,6,Plano.AEREO));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6,Plano.AEREO)));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,6,Plano.AEREO)).getVida());

        tab.mover(rat,new Posicion(5,5,Plano.AEREO));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,5,Plano.AEREO)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5,Plano.AEREO)));
        
    }
    
    @Test
    public void testMegatronAtraviezaEspinasSinProblemas(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,10,Plano.AEREO), new Espinas());
        }
        Megatron uAerea = new Megatron(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,5,Plano.AEREO),uAerea );
        tab.mover(uAerea,new Posicion(5,10,Plano.AEREO));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,10,Plano.AEREO)));
        Assert.assertEquals(550, tab.obtenerUnidad(new Posicion(5,10,Plano.AEREO)).getVida());
        tab.mover(uAerea,new Posicion(5,9,Plano.AEREO));
        Assert.assertEquals(550, tab.obtenerUnidad(new Posicion(5,9,Plano.AEREO)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,9,Plano.AEREO)));
    }
    
    @Test
    public void testRatchetAtraviezanNubesSinProblemas(){

        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5,Plano.AEREO), new Nubes());
        }
        Ratchet rat = new Ratchet(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,5,Plano.AEREO),rat );
        tab.mover(rat,new Posicion(5,6,Plano.AEREO));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6,Plano.AEREO)));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,6,Plano.AEREO)).getVida());
        tab.mover(rat,new Posicion(5,5,Plano.AEREO));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,5,Plano.AEREO)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5,Plano.AEREO)));
        
    }
    
    @Test
    public void testMegatronAtraviezaNubesSinProblemas(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,10,Plano.AEREO), new Nubes());
        }
        Megatron uAerea = new Megatron(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,5,Plano.AEREO),uAerea );
        tab.mover(uAerea,new Posicion(5,10,Plano.AEREO));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,10,Plano.AEREO)));
        Assert.assertEquals(550, tab.obtenerUnidad(new Posicion(5,10,Plano.AEREO)).getVida());
        tab.mover(uAerea,new Posicion(5,9,Plano.AEREO));
        Assert.assertEquals(550, tab.obtenerUnidad(new Posicion(5,9,Plano.AEREO)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,9,Plano.AEREO)));
    }
    
    @Test(expected=MovimientoInvalidoException.class)
    public void testRatchetEnModoAlternoQuedaAtrapadoEnNebulosa(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new NebulosaAndromeda());
        }
        Ratchet rat = new Ratchet(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,4),rat );
        tab.mover(rat,new Posicion(5,5));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5)));
        tab.mover(rat,new Posicion(5,6));
        rat.avanzarTurno();
        rat.avanzarTurno();
        rat.avanzarTurno();
        tab.mover(rat,new Posicion(5,6));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,6)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6)));
    }

    @Test(expected=MovimientoInvalidoException.class)
    public void testRatchetEnModoHumanoideQuedaAtrapadoEnEnbulosa(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new NebulosaAndromeda());
        }
        Ratchet rat = new Ratchet(new IgnorarMuerte()) ;
        rat.transformar();
        tab.agregarUnidad(new Posicion(5,4),rat );
        tab.mover(rat,new Posicion(5,5));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5)));
        tab.mover(rat,new Posicion(5,6));
        rat.avanzarTurno();
        rat.avanzarTurno();
        rat.avanzarTurno();
        tab.mover(rat,new Posicion(5,6));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,6)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6)));
        
    }
 
    @Test(expected=MovimientoInvalidoException.class)
    public void testMegatronEnModoAlternoQuedaAtrapadOEnNebulosa() {
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new NebulosaAndromeda());
        }
        Megatron uAerea = new Megatron(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,4),uAerea );
        tab.mover(uAerea,new Posicion(5,5));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5)));
        tab.mover(uAerea,new Posicion(5,6));
        uAerea.avanzarTurno();
        uAerea.avanzarTurno();
        uAerea.avanzarTurno();
        tab.mover(uAerea,new Posicion(5,6));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,6)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6)));
        
    }
    
    @Test(expected=MovimientoInvalidoException.class)
    public void testMegatronEnModoHumanoideQuedaAtrapadOEnNebulosa() {
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new NebulosaAndromeda());
        }
        Megatron uAerea = new Megatron(new IgnorarMuerte()) ;
        uAerea.transformar();
        tab.agregarUnidad(new Posicion(5,4),uAerea );
        tab.mover(uAerea,new Posicion(5,5));
        Assert.assertFalse(tab.isEmpty(new Posicion(5,5)));
        tab.mover(uAerea,new Posicion(5,6));
        uAerea.avanzarTurno();
        uAerea.avanzarTurno();
        uAerea.avanzarTurno();
        tab.mover(uAerea,new Posicion(5,6));
        Assert.assertEquals(150, tab.obtenerUnidad(new Posicion(5,6)).getVida());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6)));
        
    }

    @Test
    public void testRatchetAleternoAtraviesaTormentaDisminuyeAtaque(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new TormentaPsionica());
        }
        Ratchet rat = new Ratchet(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,4),rat );
        tab.mover(rat,new Posicion(5,5));
        tab.mover(rat,new Posicion(5,6));
        
        Assert.assertEquals(14, rat.getPuntosAtaque());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6)));
    }
    @Test

    public void testMegatronAleternoAtraviesaTormentaDisminuyeAtaque(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new TormentaPsionica());
        }
        Megatron rat = new Megatron(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,4),rat );
        tab.mover(rat,new Posicion(5,5));
        tab.mover(rat,new Posicion(5,6));
        
        Assert.assertEquals(22, rat.getPuntosAtaque());
        Assert.assertFalse(tab.isEmpty(new Posicion(5,6)));
    }

    
    @Test
    public void testMegatronAleternoAtraviesaTormentaDisminuyeAtaqueUnicaVez(){
        Tablero tab = new Tablero();
        for(int x = 0; x<10;x++){
            tab.configurarSuperficie(new Posicion(x,5), new TormentaPsionica());
        }
        Megatron rat = new Megatron(new IgnorarMuerte()) ;
        tab.agregarUnidad(new Posicion(5,4),rat );
        tab.mover(rat,new Posicion(5,5));
        tab.mover(rat,new Posicion(5,6));
        tab.mover(rat,new Posicion(6,5));
        tab.mover(rat,new Posicion(6,4));
        Assert.assertEquals(22, rat.getPuntosAtaque());
        Assert.assertFalse(tab.isEmpty(new Posicion(6,4)));
    }
    
}


