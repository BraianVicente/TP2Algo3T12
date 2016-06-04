package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;
import fiuba.algo3.modelo.unidadesVivientes.Megatron;

public class MegatronTest {


	@Test
	public void testCambiaDanioAtaque() throws FriendlyFireException, NoSeEncuentraUnidadException {
		MentiPrime tron= new MentiPrime();//arranca con 100 de vida
		Megatron transformer = new Megatron();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-55);
		transformer.transformar();
		transformer.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 100-55-10);
	}
	@Test
	public void testCambiaMovilidad(){
		Megatron transformer = new Megatron();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,7)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,8)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,9)));
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,0)));
		Assert.assertTrue(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,1)));
		Assert.assertFalse(transformer.puedeMoverse(new Posicion(0,0), new Posicion(0,2)));
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireVehiculo() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiTron prime = new MentiTron();
		Megatron transformer = new Megatron();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
	
	@Test(expected = FriendlyFireException.class)
	public void testEsFriendlyFireHumanoide() throws FriendlyFireException, NoSeEncuentraUnidadException{
		MentiTron prime = new MentiTron();
		Megatron transformer = new Megatron();
		transformer.transformar();
		transformer.atacarA(prime);
		Assert.fail("No se lanzo la excepcion como debia ser");
	}

}
