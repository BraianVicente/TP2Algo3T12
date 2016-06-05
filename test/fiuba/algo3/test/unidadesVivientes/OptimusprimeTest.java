package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Optimusprime;

public class OptimusprimeTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException, NoSeEncuentraUnidadException {
		MentiTron tron= new MentiTron();//arranca con 50 de vida
		Optimusprime transformer = new Optimusprime();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-15);
		transformer.transformar();
		
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-15-50);
	}
	@Test
	public void testCambiaMovilidad(){
		Optimusprime transformer = new Optimusprime();
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==5);
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==2);
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();
		Optimusprime transformer = new Optimusprime();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();
		Optimusprime transformer = new Optimusprime();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
