package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Ratchet;

public class RatchetTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException, NoSeEncuentraUnidadException {
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
		
		Assert.assertTrue(transformer.puedeMoverse(new PosicionEnElPlano(0,0), new PosicionEnElPlano(0,7)));
		Assert.assertTrue(transformer.puedeMoverse(new PosicionEnElPlano(0,0), new PosicionEnElPlano(0,8)));
		Assert.assertFalse(transformer.puedeMoverse(new PosicionEnElPlano(0,0), new PosicionEnElPlano(0,9)));
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.puedeMoverse(new PosicionEnElPlano(0,0), new PosicionEnElPlano(0,0)));
		Assert.assertTrue(transformer.puedeMoverse(new PosicionEnElPlano(0,0), new PosicionEnElPlano(0,1)));
		Assert.assertFalse(transformer.puedeMoverse(new PosicionEnElPlano(0,0), new PosicionEnElPlano(0,2)));
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();
		Ratchet transformer = new Ratchet();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();
		Ratchet transformer = new Ratchet();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
