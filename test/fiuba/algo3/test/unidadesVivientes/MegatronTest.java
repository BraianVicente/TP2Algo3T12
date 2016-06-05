package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
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
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==8);
		
		transformer.transformar();
		
		Assert.assertTrue(transformer.getDistanciaMovimiento()==1);
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
