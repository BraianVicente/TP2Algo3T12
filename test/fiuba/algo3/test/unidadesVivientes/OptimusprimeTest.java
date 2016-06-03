package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.MentiPrime;
import fiuba.algo3.modelo.unidadesVivientes.MentiTron;
import fiuba.algo3.modelo.unidadesVivientes.Optimusprime;

public class OptimusprimeTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException {
		MentiTron tron= new MentiTron();//arranca con 50 de vida
		Optimusprime transformer = new Optimusprime();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-15);
		transformer.transformar();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-15-50);
	}
	@Test
	public void testCambiaMovilidad(){
		Optimusprime transformer = new Optimusprime();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,4)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,5)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,6)));
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,1)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,2)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,3)));
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException{
		MentiPrime prime = new MentiPrime();
		Optimusprime transformer = new Optimusprime();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException{
		MentiPrime prime = new MentiPrime();
		Optimusprime transformer = new Optimusprime();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
