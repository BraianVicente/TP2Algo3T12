package fiuba.algo3.test.unidadesVivientes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
/**
 * Testeo UnidadConVida con dos clases de mentira que no son mock creo, está bin esto? 
 * Cómo debería hacerse?
 * 
 * @author José Sb
 *
 */
public class UnidadConVidaTest {

	@Test
	public void testDistanciaAtaque() {
		MentiPrime prime = new MentiPrime();//distancia de ataque: 3
		Assert.assertTrue(prime.puedeAtacar(new Posicion(1,1), new Posicion(3,1)));
		Assert.assertTrue(prime.puedeAtacar(new Posicion(1,1), new Posicion(4,1)));
		Assert.assertFalse(prime.puedeAtacar(new Posicion(1,1), new Posicion(5,1)));
	}
	
	@Test
	public void testDistanciaMovimiento() {
		MentiTron tron = new MentiTron();//distancia de movimiento: 2
		Assert.assertTrue(tron.puedeMoverse(new Posicion(1,1), new Posicion(2,2)));
		Assert.assertTrue(tron.puedeMoverse(new Posicion(1,1), new Posicion(2,3)));
		Assert.assertFalse(tron.puedeMoverse(new Posicion(1,1), new Posicion(2,4)));
	}
	@Test
	public void testMentiPrimeAtacaAMentiTron() throws FriendlyFireException{
		MentiPrime prime = new MentiPrime();//vida: 100, ataque: 10
		MentiTron tron = new MentiTron();//vida:50, ataque: 15
		prime.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 50-10);
	}
	@Test
	public void testMentiTronAtacaAMentiPrime() throws FriendlyFireException{
		MentiPrime prime = new MentiPrime();//vida: 100, ataque: 10
		MentiTron tron = new MentiTron();//vida:50, ataque: 15
		tron.atacarA(prime);
		Assert.assertEquals(prime.getVida(), 100-15);
	}
	
	@Test(expected=FriendlyFireException.class)
	public void testMentiTronAtacaAMentiTronCausaError() throws FriendlyFireException{
		MentiPrime prime = new MentiPrime();//vida: 100, ataque: 10
		MentiPrime tron = new MentiPrime();//vida: 100, ataque: 10
		tron.atacarA(prime);
		Assert.fail("Se deberia haber lanzado la excepcion");
	}

}
