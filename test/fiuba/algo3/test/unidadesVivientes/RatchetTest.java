package fiuba.algo3.test.unidadesVivientes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.Ratchet;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;

public class RatchetTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException {
		MentiTron tron= new MentiTron();//arranca con 50 de vida
		Ratchet transformer = new Ratchet();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-35);
		transformer.transformar();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-35-5);
	}
	@Test
	public void testCambiaMovilidad(){
		Ratchet transformer = new Ratchet();
		
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
		MentiPrime prime = new MentiPrime();
		Ratchet transformer = new Ratchet();
		transformer.atacarA(prime);
		Assert.fail("No se lanzó la excepción como debía ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException{
		MentiPrime prime = new MentiPrime();
		Ratchet transformer = new Ratchet();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzó la excepción como debía ser");
	}

}
