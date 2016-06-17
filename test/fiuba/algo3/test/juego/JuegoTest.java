package fiuba.algo3.test.juego;

import fiuba.algo3.modelo.AgarrarChispa;
import fiuba.algo3.modelo.EscenarioDefault;
import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.EquipoInvalidoException;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.Death;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.TransformacionInvalida;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Frenzy;
import fiuba.algo3.modelo.unidades.FriendlyFireException;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Optimusprime;
import fiuba.algo3.modelo.unidades.Ratchet;
import fiuba.algo3.modelo.unidades.Unidad;
import org.junit.Assert;
import org.junit.Test;

public class JuegoTest {

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
    
    
    @Test(expected=TransformacionInvalida.class)
    public void testJugadorTransormaUnidadSoloUnaVezPorTurno(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.transformarUnidad(new Posicion(8,8));
        juego.transformarUnidad(new Posicion(8,8));
        Unidad bumblee = new Bumblebee();
        Assert.assertNotEquals(bumblee.getVelocidad(), tab.obtenerUnidad(new Posicion(8,8)).getVelocidad());
    }
    
    @Test
    public void testPermiteTransformarUnidadSiguienteTurno(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.transformarUnidad(new Posicion(8,8));
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.transformarUnidad(new Posicion(8,8));
        Unidad bumblee = new Bumblebee();
        Assert.assertEquals(bumblee.getVelocidad(), tab.obtenerUnidad(new Posicion(8,8)).getVelocidad());
    
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

    @Test
    public void testJugadorCombinaUnidades(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        Posicion p1= new Posicion(8,8);
        Posicion p2=new Posicion(8,9);
        Posicion p3= new Posicion(9,9);
        j1.agregarUnidad(p1, new Bumblebee());
        j1.agregarUnidad(p2, new Optimusprime());
        j1.agregarUnidad(p3, new Ratchet());
        juego.combinarUnidades();
        boolean todasVacias= tab.isEmpty(p1)&&tab.isEmpty(p2)&&tab.isEmpty(p3);
        boolean dosEstanVacias= (tab.isEmpty(p1)&&tab.isEmpty(p2))||
        						(tab.isEmpty(p2)&&tab.isEmpty(p3))||
        						(tab.isEmpty(p1)&&tab.isEmpty(p3));
        Assert.assertFalse(!todasVacias&&dosEstanVacias);
        juego.avanzarTurno();
        juego.avanzarTurno();
        todasVacias= tab.isEmpty(p1)&&tab.isEmpty(p2)&&tab.isEmpty(p3);
        dosEstanVacias= (tab.isEmpty(p1)&&tab.isEmpty(p2))||
        						(tab.isEmpty(p2)&&tab.isEmpty(p3))||
        						(tab.isEmpty(p1)&&tab.isEmpty(p3));
        Assert.assertFalse(!todasVacias&&dosEstanVacias);
        juego.avanzarTurno();
        juego.avanzarTurno();
        todasVacias= tab.isEmpty(p1)&&tab.isEmpty(p2)&&tab.isEmpty(p3);
        dosEstanVacias= (tab.isEmpty(p1)&&tab.isEmpty(p2))||
        						(tab.isEmpty(p2)&&tab.isEmpty(p3))||
        						(tab.isEmpty(p1)&&tab.isEmpty(p3));
        Assert.assertFalse(!todasVacias&&dosEstanVacias);

        //una llena, las otras dos vacias no importa cuales
    }
    @Test
    public void testJugadorQueConsigueChispaGana(){
        AgarrarChispa condition = new AgarrarChispa();
        Tablero tab = new Tablero(new EscenarioDefault(),condition);
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        condition.setJuego(juego);
        condition.setTablero(tab);
        tab.agregarUnidad(new Posicion(6,6), new Bumblebee());
        juego.moverUnidad(new Posicion(6,6), new Posicion(5,5));
        Assert.assertTrue(juego.jugadorGanadorEs(j1));
    }

    @Test
    public void testJugadorConUnidadesMuertasPierde(){
        AgarrarChispa condition = new AgarrarChispa();
        Tablero tab = new Tablero(new EscenarioDefault(),condition);
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        condition.setJuego(juego);
        condition.setTablero(tab);
        Posicion origen = new Posicion(6,6);
        Posicion destino = new Posicion(7,7) ;
        Ratchet uniJ1 = new Ratchet(new Death(tab));
        Megatron uniJ2 = new Megatron(new Death(tab));
        j1.agregarUnidad(origen, uniJ1);
        
        j2.agregarUnidad(destino,uniJ2 );
        juego.atacarUnidad(origen,destino);
        
        juego.avanzarTurno();
        juego.atacarUnidad(destino, origen);
        juego.avanzarTurno();
        juego.atacarUnidad(origen,destino);
        juego.avanzarTurno();
        juego.atacarUnidad(destino, origen);
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.atacarUnidad(destino, origen);
        
        Assert.assertTrue(juego.jugadorGanadorEs(j2));
    }

}