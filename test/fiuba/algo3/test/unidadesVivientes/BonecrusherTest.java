package fiuba.algo3.test.unidadesVivientes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Bonecrusher;

public class BonecrusherTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException {
		MentiPrime tron= new MentiPrime();//arranca con 100 de vida
		Bonecrusher transformer = new Bonecrusher();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-30);
		transformer.transformar();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-30-30);
	}
	@Test
	public void testCambiaMovilidad(){
		Bonecrusher transformer = new Bonecrusher();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,7)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,8)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,9)));
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,0)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,1)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,2)));
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException{
		MentiTron prime = new MentiTron();
		Bonecrusher transformer = new Bonecrusher();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException{
		MentiTron prime = new MentiTron();
		Bonecrusher transformer = new Bonecrusher();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
