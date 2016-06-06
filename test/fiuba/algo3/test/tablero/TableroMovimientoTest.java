package fiuba.algo3.test.tablero;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;
import fiuba.algo3.modelo.unidadesVivientes.Frenzy;
import fiuba.algo3.modelo.unidadesVivientes.Megatron;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Ratchet;

public class TableroMovimientoTest {
	@Test
    public void test01QuieroMovermeEnRocosa(){
        Tablero tab = new Tablero();
        Bumblebee bum=new Bumblebee();
        tab.agregarUnidad(new Posicion(3,5,Plano.TERRESTRE),bum);
        Assert.assertTrue(tab.isEmpty(new Posicion(3,7,Plano.TERRESTRE)));
        tab.mover(bum, new Posicion(3,7,Plano.TERRESTRE));
        Assert.assertFalse(tab.isEmpty(new Posicion(3,7,Plano.TERRESTRE)));

    }
	
	@Test
    public void test02QuieroMovermeEnRocosaMovimientoMasComplejo(){
        Tablero tab = new Tablero();
        Bumblebee bum=new Bumblebee();
        tab.agregarUnidad(new Posicion(3,5,Plano.TERRESTRE),bum);
        Assert.assertTrue(tab.isEmpty(new Posicion(7,8,Plano.TERRESTRE)));
        tab.mover(bum, new Posicion(7,8,Plano.TERRESTRE));
        Assert.assertFalse(tab.isEmpty(new Posicion(7,8,Plano.TERRESTRE)));

    }
	

	@Test(expected=MovimientoInvalidoException.class)
    public void test03QuieroMovermePeroAlguienInterfiere(){
        Tablero tab = new Tablero();
        Bumblebee bum=new Bumblebee();
        tab.agregarUnidad(new Posicion(3,5,Plano.TERRESTRE),bum);
        Assert.assertTrue(tab.isEmpty(new Posicion(7,8,Plano.TERRESTRE)));
        tab.agregarUnidad(new Posicion(6,8,Plano.TERRESTRE),new Megatron()); 
        tab.mover(bum, new Posicion(7,8,Plano.TERRESTRE));
        Assert.assertFalse(tab.isEmpty(new Posicion(7,8,Plano.TERRESTRE)));

    }
}
