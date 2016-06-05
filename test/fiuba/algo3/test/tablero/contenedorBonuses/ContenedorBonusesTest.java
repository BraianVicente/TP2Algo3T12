package fiuba.algo3.test.tablero.contenedorBonuses;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.bonuses.BonusBurbuja;
import fiuba.algo3.modelo.bonuses.BonusDobleCanion;
import fiuba.algo3.modelo.bonuses.BonusFlash;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.contenedorBonuses.BonusYaContenidoException;
import fiuba.algo3.modelo.tablero.contenedorBonuses.ContenedorBonuses;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;

public class ContenedorBonusesTest {

	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	*/
	final BonusBurbuja burbuja1=new BonusBurbuja(null);
	final BonusBurbuja burbuja2=new BonusBurbuja(null);
	final BonusBurbuja burbuja3=new BonusBurbuja(null);
	
	final BonusFlash flash1=new BonusFlash(null);
	final BonusDobleCanion canion1=new BonusDobleCanion(null);
	
	final Posicion p1 =new Posicion(-5,10);
	final Posicion p2 =new Posicion(10000000,3);
	final Posicion p3 =new Posicion(0,0);
	
	@Test(expected = BonusYaContenidoException.class)
	public void testElMismoDosVecesDaError() throws BonusYaContenidoException {
		ContenedorBonuses c = new ContenedorBonuses();
		c.agregarBonus(burbuja1, p1);
		c.agregarBonus(burbuja1, p2);
	}
	@Test(expected = PosicionOcupadaException.class)
	public void testAgregarPisandoDaError() throws BonusYaContenidoException{
		ContenedorBonuses c = new ContenedorBonuses();
		c.agregarBonus(burbuja1, p3);
		c.agregarBonus(flash1, p3);
	}
	@Test
	public void testPermiteMismoTipo() throws BonusYaContenidoException{
		ContenedorBonuses c = new ContenedorBonuses();
		c.agregarBonus(burbuja1, p1);
		c.agregarBonus(burbuja2, p2);
		c.agregarBonus(burbuja3, p3);
		
		Assert.assertTrue(c.ocupada(p1));
		Assert.assertTrue(c.ocupada(p2));
		Assert.assertTrue(c.ocupada(p3));
	}
	@Test
	public void testPermiteMuchosTipos() throws BonusYaContenidoException{
		ContenedorBonuses c = new ContenedorBonuses();
		c.agregarBonus(burbuja1, p1);
		c.agregarBonus(flash1, p2);
		c.agregarBonus(canion1, p3);
		
		Assert.assertTrue(c.ocupada(p1));
		Assert.assertTrue(c.ocupada(p2));
		Assert.assertTrue(c.ocupada(p3));
	}

}
