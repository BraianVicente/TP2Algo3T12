package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidades.Bonecrusher;
import fiuba.algo3.modelo.unidades.FriendlyFireException;

public class BonecrusherTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException, NoSeEncuentraUnidadException {
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
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==8);
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==1);
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiTron prime = new MentiTron();
		Bonecrusher transformer = new Bonecrusher();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiTron prime = new MentiTron();
		Bonecrusher transformer = new Bonecrusher();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
