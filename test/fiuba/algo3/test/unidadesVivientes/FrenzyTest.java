package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidades.Frenzy;
import fiuba.algo3.modelo.unidades.FriendlyFireException;

public class FrenzyTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException, NoSeEncuentraUnidadException {
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
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==6);
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==2);
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiTron prime = new MentiTron();
		Frenzy transformer = new Frenzy();
		transformer.atacarA(prime);
		Assert.fail("No se lanz� la excepci�n como deb�a ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiTron prime = new MentiTron();
		Frenzy transformer = new Frenzy();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanz� la excepci�n como deb�a ser");
	}

}
