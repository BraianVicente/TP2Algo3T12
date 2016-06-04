package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;

public class BumblebeeTest {

	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException, NoSeEncuentraUnidadException {
		MentiTron tron= new MentiTron();//arranca con 50 de vida
		Bumblebee transformer = new Bumblebee();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-20);
		transformer.transformar();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-20-40);
	}
	@Test
	public void testCambiaMovilidad(){
		Bumblebee transformer = new Bumblebee();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,4)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,5)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,6)));
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,1)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,2)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,3)));
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();
		Bumblebee transformer = new Bumblebee();
		transformer.atacarA(prime);
		  
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();
		Bumblebee transformer = new Bumblebee();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
