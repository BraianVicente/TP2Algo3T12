package fiuba.algo3.test.juego;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.EquipoInvalidoException;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Frenzy;
import fiuba.algo3.modelo.unidades.FriendlyFireException;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.modelo.unidades.Unidad;
import org.junit.Assert;
import org.junit.Test;

public class JuegoTets {
    
    @Test
    public void testJuegoCreaJuego(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots());
        Jugador j2 = new Jugador("J2",new Decepticons());
        Juego juego = new Juego(tab,j1,j2);
        Assert.assertEquals(juego.jugadorEnTurno(),j1);
    }
    
    @Test
    public void testJuegoPasaTurno(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        juego.cambiarTurno();
        Assert.assertEquals(juego.jugadorEnTurno(),j2);
    }

    @Test
    public void testJugadorSinUnidadesPierde(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        Assert.assertTrue(j1.derrotado());
    }
    
    @Test
    public void testJuegoAgregaUnidadesJugadorEnTablero(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        juego.agregarJugadorAutobots("J1", tab);
        Assert.assertFalse(j1.derrotado());
    }
    
    
    @Test
    public void testJugadorRealizaMovimientoUnidad(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.moverUnidad(new Posicion(8,8), new Posicion(8,9));
        Assert.assertTrue(tab.isEmpty(new Posicion(8,8)));
    }
    
    
    @Test(expected=EquipoInvalidoException.class)
    public void testJugadorNoPuedeRealizarMovimientoUnidadContraria(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j2.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.moverUnidad(new Posicion(8,8), new Posicion(8,9));
        Assert.assertFalse(tab.isEmpty(new Posicion(8,8)));
    }
    
    @Test
    public void testJugadorPuedeAtacarUnidadContraria(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        j2.agregarUnidad(new Posicion(7,7), new Frenzy());
        juego.atacarUnidad(new Posicion(8,8), new Posicion(7,7));
        Assert.assertNotEquals(tab.obtenerUnidad(new Posicion(7,7)).getVidaMaxima(), tab.obtenerUnidad(new Posicion(7,7)).getVida());
    }
    
    @Test(expected=FriendlyFireException.class)
    public void testJugadorNoPuedeAtacarUnidadPropia(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        j1.agregarUnidad(new Posicion(7,7), new Optimusprime());
        juego.atacarUnidad(new Posicion(8,8), new Posicion(7,7));
        Assert.assertEquals(tab.obtenerUnidad(new Posicion(7,7)).getVidaMaxima(), tab.obtenerUnidad(new Posicion(7,7)).getVida());
    }
    
    @Test(expected=EquipoInvalidoException.class)
    public void testJugadorNoPuedeAtacarConUnidadAjena(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        j2.agregarUnidad(new Posicion(7,7), new Frenzy());
        juego.atacarUnidad(new Posicion(7,7), new Posicion(8,8));
        Assert.assertEquals(tab.obtenerUnidad(new Posicion(7,7)).getVidaMaxima(), tab.obtenerUnidad(new Posicion(7,7)).getVida());
    }
    
    @Test
    public void testJugadorTransformaUnidad(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.transformarUnidad(new Posicion(8,8));
        Unidad bumblee = new Bumblebee();
        Assert.assertNotEquals(bumblee.getVelocidad(), tab.obtenerUnidad(new Posicion(8,8)).getVelocidad());
    }
    
    @Test(expected=EquipoInvalidoException.class)
    public void testJugadorNoTransformaUnidadAjena(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.cambiarTurno();
        juego.transformarUnidad(new Posicion(8,8));
        
    }
    

    
    
}
