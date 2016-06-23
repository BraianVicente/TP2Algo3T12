package fiuba.algo3.test.equipos;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Ninguno;

public class EquipoTest {

	@Test
	public void testAutobotsSonDelMismo() {
		Assert.assertEquals(new Autobots(),new Autobots());
	}
	
	@Test
	public void testDecepticonsSonDelMismo() {
		Assert.assertEquals(new Decepticons(),new Decepticons());
	}
	
	@Test
	public void testAsignadosSonDistintos() {
		Assert.assertFalse(new Autobots().equals(new Decepticons()));
		Assert.assertFalse(new Decepticons().equals(new Autobots()));
	}
	
	@Test
	public void testDecepticonNoESNinguno() {
		Assert.assertFalse(new Ninguno().equals(new Decepticons()));
		Assert.assertFalse(new Decepticons().equals(new Ninguno()));
	}
	
	@Test
	public void testAutobotNoESNinguno() {
		Assert.assertFalse(new Ninguno().equals(new Autobots()));
		Assert.assertFalse(new Autobots().equals(new Ninguno()));
	}

}
