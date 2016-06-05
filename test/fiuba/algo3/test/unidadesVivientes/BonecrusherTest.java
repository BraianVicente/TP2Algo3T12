package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.Bonecrusher;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;

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
