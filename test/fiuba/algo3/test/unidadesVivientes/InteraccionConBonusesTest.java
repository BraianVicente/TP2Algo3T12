package fiuba.algo3.test.unidadesVivientes;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.bonuses.BonusBurbuja;
import fiuba.algo3.modelo.bonuses.BonusDobleCanion;
import fiuba.algo3.modelo.bonuses.BonusFlash;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Megatron;

public class InteraccionConBonusesTest {

	final BonusDobleCanion dobleCanion = new BonusDobleCanion(null);
	final BonusBurbuja burbuja = new BonusBurbuja(null);
	final BonusFlash flash = new BonusFlash(null);

	@Test
	public void testCanion() {
		Bumblebee bee = new Bumblebee(new IgnorarMuerte());//vida: 350, atk:20, mov: 5
		Megatron tron = new Megatron(new IgnorarMuerte());//vida:550, atk:55, mov: 8
		bee.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 550-20);
		bee.recibirBonus(dobleCanion);
		bee.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 550-20-40);
	}

	@Test
	public void testBurbuja() {
		Bumblebee bee = new Bumblebee(new IgnorarMuerte());//vida: 350, atk:20, mov: 5
		Megatron tron = new Megatron(new IgnorarMuerte());//vida:550, atk:55, mov: 8
		bee.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 550-20);
		tron.recibirBonus(burbuja);
		bee.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 550-20);
	}
	

	@Test
	public void testFlash() {
		Bumblebee bee = new Bumblebee(new IgnorarMuerte());//vida: 350, atk:20, mov: 5
		Assert.assertEquals(bee.getVelocidad(), 5);
		bee.recibirBonus(flash);
		Assert.assertEquals(bee.getVelocidad(), 5*3);
	}
	
	@Test
	public void testFlashPierdeEfecto() {
		Bumblebee bee = new Bumblebee(new IgnorarMuerte());//vida: 350, atk:20, mov: 5
		Assert.assertEquals(bee.getVelocidad(), 5);
		bee.recibirBonus(flash);
		Assert.assertEquals(bee.getVelocidad(), 5*3);
		bee.avanzarTurno();
		bee.avanzarTurno();
		bee.avanzarTurno();
		Assert.assertEquals(bee.getVelocidad(), 5);
	}
	
	@Test
	public void testSuperposicion() {
		Bumblebee bee = new Bumblebee(new IgnorarMuerte());//vida: 350, atk:20, mov: 5
		bee.recibirBonus(flash);
		bee.recibirBonus(dobleCanion);
		bee.recibirBonus(burbuja);
		Assert.assertEquals(bee.getVelocidad(), 5*3);
		
		Megatron tron = new Megatron(new IgnorarMuerte());//vida:550, atk:55, mov: 8
		bee.atacarA(tron);
		Assert.assertEquals(tron.getVida(), 550-40);
		
		tron.atacarA(bee);
		Assert.assertEquals(bee.getVida(), 350);
	}

}
