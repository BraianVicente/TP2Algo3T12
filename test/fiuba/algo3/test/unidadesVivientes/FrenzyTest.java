package fiuba.algo3.test.unidadesVivientes;

import fiuba.algo3.modelo.unidadesVivientes.MentiPrime;
import fiuba.algo3.modelo.unidadesVivientes.MentiTron;
import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Frenzy;

public class FrenzyTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException {
		MentiPrime tron= new MentiPrime();//arranca con 100 de vida
		Frenzy transformer = new Frenzy();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-25);
		transformer.transformar();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-25-10);
	}
	@Test
	public void testCambiaMovilidad(){
		Frenzy transformer = new Frenzy();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,5)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,6)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,7)));
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,1)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,2)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,3)));
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException{
		MentiTron prime = new MentiTron();
		Frenzy transformer = new Frenzy();
		transformer.atacarA(prime);
		Assert.fail("No se lanz� la excepci�n como deb�a ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException{
		MentiTron prime = new MentiTron();
		Frenzy transformer = new Frenzy();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanz� la excepci�n como deb�a ser");
	}

}
