/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.test.jugador;

import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.jugador.EquipoInvalidoException;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Frenzy;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author brahvic
 */
public class JugadorTest {
    
    
    @Test
    public void testCrearJugador(){
        Jugador autobots = new Jugador("Autobot",new Autobots());
        Assert.assertTrue(autobots.equals(new Jugador("Autobot",new Autobots())));
    }
    
    @Test
    public void testUnidadPerteneceJugador(){
        Jugador autobots = new Jugador("Autobot",new Autobots());
        Assert.assertTrue(autobots.perteneceEquipo(new Bumblebee()));
    }
    
    @Test(expected=EquipoInvalidoException.class)
    public void testUnidadNoPerteneceJugador(){
        Jugador autobots = new Jugador("Autobot",new Autobots());
        Assert.assertFalse(autobots.perteneceEquipo(new Frenzy()));
    }
    
}

