package fiuba.algo3.test.modificadores;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.modificadores.ContenedorModificadores;
import fiuba.algo3.modelo.modificadores.ModificadorBurbuja;
import fiuba.algo3.modelo.modificadores.ModificadorDoble;
import fiuba.algo3.modelo.modificadores.ModificadorFlash;
import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.modificadores.ModificadorPsionica;

public class ContenedorModificadoresTest {
 
	@Test
	public void testModificadorBurbujaSeExtiende() {
		ContenedorModificadores c = new ContenedorModificadores();
		Assert.assertTrue(c.recibeDanio());
		c.agregar(new ModificadorBurbuja());
		Assert.assertFalse(c.recibeDanio());
		c.pasaTurno();
		c.agregar(new ModificadorBurbuja());
		c.pasaTurno();
		Assert.assertFalse(c.recibeDanio());
		c.pasaTurno();
		Assert.assertTrue(c.recibeDanio());
	}
	
	@Test
	public void testModificadorDobleSeApila(){
		ContenedorModificadores c = new ContenedorModificadores();
		Assert.assertEquals(c.coeficienteAtaque(), 1,0.001);
		c.agregar(new ModificadorDoble());
		Assert.assertEquals(c.coeficienteAtaque(), 2,0.001);
		c.pasaTurno();
		c.agregar(new ModificadorDoble());
		Assert.assertEquals(c.coeficienteAtaque(), 4,0.001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteAtaque(), 4,0.001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteAtaque(), 2,0.001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteAtaque(), 1,0.001);
	}
	
	@Test
	public void testModificadorFlashSeApila(){
		ContenedorModificadores c = new ContenedorModificadores();
		Assert.assertEquals(c.coeficienteVelocidad(), 1,0.001);
		c.agregar(new ModificadorFlash());
		Assert.assertEquals(c.coeficienteVelocidad(), 3,0.001);
		c.pasaTurno();
		c.agregar(new ModificadorFlash());
		Assert.assertEquals(c.coeficienteVelocidad(), 9,0.001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteVelocidad(), 9,0.001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteVelocidad(), 3,0.001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteVelocidad(), 1,0.001);
	}
	
	@Test
	public void testModificadorNebulosaSeApila(){
		ContenedorModificadores c = new ContenedorModificadores();
		Assert.assertEquals(c.puedeMoverse(), true);
		c.agregar(new ModificadorNebulosa());
		Assert.assertEquals(c.puedeMoverse(), false);
		c.pasaTurno();
		c.agregar(new ModificadorNebulosa());
		Assert.assertEquals(c.puedeMoverse(), false);
		c.pasaTurno();
		Assert.assertEquals(c.puedeMoverse(), false);
		c.pasaTurno();
		Assert.assertEquals(c.puedeMoverse(), false);
		c.pasaTurno();
		Assert.assertEquals(c.puedeMoverse(), true);
	}
	
	@Test
	public void testModificadorPsionicaNoSeApila(){
		ContenedorModificadores c = new ContenedorModificadores();
		Assert.assertEquals(c.afectadoPorPsionica(), false);
		Assert.assertEquals(c.coeficienteAtaqueModoVehiculo(), 1,0.001);
		c.agregar(new ModificadorPsionica());
		Assert.assertEquals(c.afectadoPorPsionica(), true);
		Assert.assertEquals(c.coeficienteAtaqueModoVehiculo(), 0.4,0.00001);
		c.pasaTurno();
		c.agregar(new ModificadorPsionica());
		Assert.assertEquals(c.afectadoPorPsionica(), true);
		Assert.assertEquals(c.coeficienteAtaqueModoVehiculo(), 0.4,0.00001);
		c.pasaTurno();
		Assert.assertEquals(c.coeficienteAtaqueModoVehiculo(), 0.4,0.00001);
		for(int i=0; i<50; i++){
			c.pasaTurno();
		}
		Assert.assertEquals(c.coeficienteAtaqueModoVehiculo(), 0.4,0.00001);
		c.pasaTurno();
		Assert.assertEquals(c.afectadoPorPsionica(), true);
	}
	
	@Test
	public void testModificadoresDistintosSeApilan(){
		ContenedorModificadores c = new ContenedorModificadores();
		
		c.agregar(new ModificadorBurbuja());
		c.agregar(new ModificadorDoble());
		c.agregar(new ModificadorFlash());
		c.agregar(new ModificadorNebulosa());
		c.agregar(new ModificadorPsionica());
		
		Assert.assertEquals(c.coeficienteAtaque(), 2,0.001);
		Assert.assertEquals(c.coeficienteAtaqueModoVehiculo(), 0.4,0.00001);
		Assert.assertEquals(c.afectadoPorPsionica(), true);
		Assert.assertEquals(c.puedeMoverse(), false);
		Assert.assertEquals(c.coeficienteVelocidad(), 3,0.001);
		Assert.assertFalse(c.recibeDanio());
	}

}
