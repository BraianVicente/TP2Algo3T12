package fiuba.algo3.test.tablero;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.AtaqueInvalidoException;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.unidades.Bonecrusher;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Frenzy;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.Ratchet;

public class TableroAtaqueTest {
	   @Test(expected=AtaqueInvalidoException.class)
	    public void test01QuieroAtacarYAlguienInterfiere(){
	        Tablero tab = new Tablero();
	        Bumblebee bum=new Bumblebee();
	        Frenzy frenzy=new Frenzy();
	        tab.agregarUnidad(new Posicion(3,5),bum);
	        tab.agregarUnidad(new Posicion(5,5),frenzy);
	        tab.agregarUnidad(new Posicion(4,5),new Bonecrusher());
	        tab.atacar(bum, frenzy);
	    }
	   @Test
	    public void test02QuieroAtacarYNadieInterfiere(){
	        Tablero tab = new Tablero();
	        Bumblebee bum=new Bumblebee();
	        Frenzy frenzy=new Frenzy();
	        int vidaOriginal=frenzy.getVida();
	        tab.agregarUnidad(new Posicion(3,5),bum);
	        tab.agregarUnidad(new Posicion(5,5),frenzy);
	        tab.agregarUnidad(new Posicion(4,6),new Bonecrusher());
	        tab.agregarUnidad(new Posicion(4,4),new Megatron());
	        tab.atacar(bum, frenzy);
	        Assert.assertEquals(vidaOriginal-bum.getPuntosAtaque(),frenzy.getVida());
	    }
	   @Test
	    public void test03QuieroAtacarAOtroPlanoYNadieInterfiere(){
	        Tablero tab = new Tablero();
	        Bumblebee bum=new Bumblebee();
	        Frenzy frenzy=new Frenzy();
	        Megatron megatron=new Megatron();
	        Ratchet ratchet=new Ratchet();
	        int vidaOriginal=megatron.getVida();
	        tab.agregarUnidad(new Posicion(3,5,Plano.TERRESTRE),bum);
	        tab.agregarUnidad(new Posicion(5,5,Plano.AEREO),megatron);
	        tab.agregarUnidad(new Posicion(4,5,Plano.TERRESTRE),frenzy);
	        tab.agregarUnidad(new Posicion(4,4,Plano.AEREO),ratchet);
	        tab.atacar(bum, megatron);
	        Assert.assertEquals(vidaOriginal-bum.getPuntosAtaque(),megatron.getVida());
	    }
	   @Test(expected=AtaqueInvalidoException.class)
	    public void test04QuieroAtacarAOtroPlanoYAlguienInterfiere(){
	        Tablero tab = new Tablero();
	        Bumblebee bum=new Bumblebee();
	        Frenzy frenzy=new Frenzy();
	        Megatron megatron=new Megatron();
	        Ratchet ratchet=new Ratchet();
	        int vidaOriginal=megatron.getVida();
	        tab.agregarUnidad(new Posicion(3,5,Plano.TERRESTRE),bum);
	        tab.agregarUnidad(new Posicion(5,5,Plano.AEREO),megatron);
	        tab.agregarUnidad(new Posicion(4,5,Plano.AEREO),ratchet);
	        tab.atacar(bum, megatron);
	        Assert.assertEquals(vidaOriginal-bum.getPuntosAtaque(),megatron.getVida());
	    }
}
