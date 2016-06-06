package fiuba.algo3.test.obligatorias;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;

public class SegundaEntregaTest {

	@Test
	public void testBumblebeeAtraviesaRocosaSinProblemas() {
		Tablero t = new Tablero();
		for(int x = 0; x<10;x++){
			t.configurarSuperficie(new Posicion(x,10), new Rocosa());
		}
		Bumblebee bee = new Bumblebee(new IgnorarMuerte());
		t.agregarUnidad(new Posicion(5,9), bee);
		t.mover(bee, new Posicion(5,11));
		Assert.assertFalse(t.isEmpty(new Posicion(5,11)));
	}

}
