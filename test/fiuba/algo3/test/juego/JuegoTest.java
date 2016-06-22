package fiuba.algo3.test.juego;

import fiuba.algo3.modelo.tablero.EscenarioDefault;
import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.jugador.EquipoInvalidoException;
import fiuba.algo3.modelo.jugador.Jugador;
import fiuba.algo3.modelo.unidades.muerte.Death;
import fiuba.algo3.modelo.juego.condicionvictoria.VictoriaAgarrarChispa;
import fiuba.algo3.modelo.juego.condicionvictoria.VictoriaMontePerdicion;
import fiuba.algo3.modelo.posicion.Posicion;
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
        juego.avanzarTurno();
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
        Integer velNormal = bumblee.getVelocidad() ;
        Integer velActual = tab.obtenerUnidad(new Posicion(8,8)).getVelocidad() ;
        Assert.assertEquals(velNormal,velActual );
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

    @Test
    public void testPermiteTransformarVariasVeces(){
        Tablero tab = new Tablero();
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        j1.agregarUnidad(new Posicion(8,8), new Bumblebee());
        juego.transformarUnidad(new Posicion(8,8));
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.transformarUnidad(new Posicion(8,8));
        juego.avanzarTurno();
        juego.avanzarTurno();
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
        juego.avanzarTurno();
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
        Assert.assertTrue(!todasVacias&&dosEstanVacias);
        juego.avanzarTurno();
        juego.avanzarTurno();
        Assert.assertTrue(juego.hayCombinacion(new Autobots()));
        juego.avanzarTurno();
        juego.avanzarTurno();
        Assert.assertFalse(juego.hayCombinacion(new Autobots()));
        //una llena, las otras dos vacias no importa cuales
    }
    @Test
    public void testJugadorQueConsigueChispaGana(){
        VictoriaAgarrarChispa condition = new VictoriaAgarrarChispa();
        Tablero tab = new Tablero(new EscenarioDefault(),condition);
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        condition.setJuego(juego);
        condition.setTablero(tab);
        juego.agregarUnidad(new Posicion(6,6), new Bumblebee());
        juego.transformarUnidad(new Posicion(6,6));
        juego.moverUnidad(new Posicion(6,6), new Posicion(5,5));
        Assert.assertTrue(tab.unidadesContieneChispa(j1.getEquipo()));
        Assert.assertTrue(juego.jugadorGanadorEs(j1));
    }

    @Test
    public void testJugadorConUnidadesMuertasPierde(){
        VictoriaAgarrarChispa condition = new VictoriaAgarrarChispa();
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
    
    
    @Test
    public void testUnidadConChispaLlegAlMontePerdicionGana(){
        VictoriaMontePerdicion condition = new VictoriaMontePerdicion();
        Tablero tab = new Tablero(10,10,condition);

        Posicion origen = new Posicion(4,4);
        Posicion destino = new Posicion(6,6) ;
        
        tab.colocarMontePerdicion(destino);
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        condition.setJuego(juego);
        condition.setTablero(tab);

        Ratchet uniJ1 = new Ratchet(new Death(tab));
        juego.agregarUnidad(origen, uniJ1);
        uniJ1.transformar();
        Assert.assertFalse(tab.unidadesContieneChispa(j1.getEquipo()));
        juego.moverUnidad(origen, new Posicion (5,5));
        Assert.assertTrue(tab.unidadesContieneChispa(j1.getEquipo()));
        
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.moverUnidad(new Posicion(5,5), destino);
        
        Assert.assertTrue(juego.jugadorGanadorEs(j1));
    }

    
    @Test
    public void testUnidadPierdeChispaYLaAgarraElOtroEquipo(){
        VictoriaMontePerdicion condition = new VictoriaMontePerdicion();
        Tablero tab = new Tablero(new EscenarioDefault(),condition);
        tab.colocarMontePerdicion(new Posicion(5,6));
        Jugador j1 = new Jugador("J1", new Autobots(),tab);
        Jugador j2 = new Jugador("J2",new Decepticons(),tab);
        Juego juego = new Juego(tab,j1,j2);
        condition.setJuego(juego);
        condition.setTablero(tab);
        Posicion origen = new Posicion(5,5);
        Posicion destino = new Posicion(6,6) ;
        Ratchet uniJ1 = new Ratchet(new Death(tab));
        Bumblebee otraUni = new Bumblebee(new Death(tab));
        Megatron uniJ2 = new Megatron(new Death(tab));
        
        j1.agregarUnidad(new Posicion(4,4), uniJ1);
        j1.agregarUnidad(new Posicion(1,1),otraUni);
        
        j2.agregarUnidad(destino,uniJ2 );
        
        juego.transformarUnidad(new Posicion(4,4));
        juego.moverUnidad(new Posicion(4,4), origen);
        Assert.assertTrue(tab.unidadesContieneChispa(uniJ1.equipo()));
        juego.avanzarTurno();
        juego.atacarUnidad(destino, origen);
        juego.avanzarTurno();
        juego.atacarUnidad(origen,destino);
        juego.avanzarTurno();
        juego.atacarUnidad(destino, origen);
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.atacarUnidad(destino, origen);
        Assert.assertFalse(tab.unidadesContieneChispa(uniJ1.equipo()));
        juego.transformarUnidad(destino);
        juego.avanzarTurno();
        juego.avanzarTurno();
        juego.moverUnidad(destino,origen);
        Assert.assertTrue(tab.unidadesContieneChispa(uniJ2.equipo()));
        juego.avanzarTurno();
        juego.avanzarTurno();
        
        juego.moverUnidad(origen,new Posicion(5,6));
        Assert.assertTrue(juego.jugadorGanadorEs(j2));
    }
}
