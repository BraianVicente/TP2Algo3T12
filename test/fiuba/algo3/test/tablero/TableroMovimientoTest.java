package fiuba.algo3.test.tablero;

import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Megatron;
import fiuba.algo3.modelo.unidades.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidades.Ratchet;

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
	
	@Test(expected=MovimientoInvalidoException.class)
	public void test05QuieroMoverHumanoideEnPantano(){
		Tablero tab=new Tablero();
		Posicion pos= new Posicion(3,3,Plano.TERRESTRE);
		tab.agregarSuperficie(new Pantano(),pos);
		Bumblebee bum=new Bumblebee();
		bum.transformar();
		tab.agregarUnidad(pos.obtenerMismaPosicionDesplazada(1, 0), bum);
		tab.mover(bum,pos);
		tab.mover(bum, pos.obtenerMismaPosicionDesplazada(1, 0));
		
	}
	@Test(expected=MovimientoInvalidoException.class)
	public void test06QuieroMoverAereoEnNebulosa(){
		Tablero tab=new Tablero();
		Posicion pos= new Posicion(3,3,Plano.AEREO);
		tab.agregarSuperficie(new NebulosaAndromeda(),pos);
		Ratchet bum=new Ratchet();
		tab.agregarUnidad(pos.obtenerMismaPosicionDesplazada(1, 0), bum);
		tab.mover(bum,pos);
		tab.mover(bum, pos.obtenerMismaPosicionDesplazada(1, 0));
		
	}
    
    @Test
    public void test07MoverUnidadDesplazaChispa(){
        Tablero tab = new Tablero();
		Posicion pos = new Posicion(4,4);
		Ratchet bum = new Ratchet();
        bum.transformar();
        tab.agregarUnidad(pos,bum);
        tab.colocarChispa();
        tab.colocarMontePerdicion(new Posicion(6,6));
        tab.mover(bum, new Posicion(5,5));
        tab.pasarTurnoEquipo(bum.equipo());
        tab.mover(bum, new Posicion(6,6));
        Assert.assertTrue(tab.unidadesContieneChispa(bum.equipo()));
        Assert.assertEquals(tab.getPosicionChispa(),tab.posicion(bum));
        
    }
    
    @Test
    public void test08UnidadAgarraChispaEnModoAlterno(){
        
    }
}
