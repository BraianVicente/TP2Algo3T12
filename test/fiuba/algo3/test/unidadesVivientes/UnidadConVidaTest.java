package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidades.FriendlyFireException;
/**
 * Testeo UnidadConVida con dos clases de mentira que no son mock creo, esta bin esto? 
 * Como deberia hacerse?
 * 
 * @author Jose Sb
 *
 */
public class UnidadConVidaTest {

	@Test
	public void testDistanciaAtaque() {
		MentiPrime prime = new MentiPrime();//distancia de ataque: 3
		Assert.assertTrue(prime.getDistanciaAtaque()==3);
		

	}
	
	@Test
	public void testDistanciaMovimiento() {
		MentiTron tron = new MentiTron();//distancia de movimiento: 2
		Assert.assertTrue(tron.getDistanciaMovimiento()==2);
		

	}
	@Test
	public void testMentiPrimeAtacaAMentiTron() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();//vida: 100, ataque: 10
		MentiTron tron = new MentiTron();//vida:50, ataque: 15
		prime.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-10);
	}
	@Test
	public void testMentiTronAtacaAMentiPrime() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();//vida: 100, ataque: 10
		MentiTron tron = new MentiTron();//vida:50, ataque: 15
		tron.atacarA(prime);
		Assert.assertEquals(prime.getVida(), 100-15);
	}
	
	@Test(expected=FriendlyFireException.class)
	public void testMentiTronAtacaAMentiTronCausaError() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiPrime prime = new MentiPrime();//vida: 100, ataque: 10
		MentiPrime tron = new MentiPrime();//vida: 100, ataque: 10
		tron.atacarA(prime);
		Assert.fail("Se deberia haber lanzado la excepcion");
	}

}
