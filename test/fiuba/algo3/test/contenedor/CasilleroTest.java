package fiuba.algo3.test.contenedor;



import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.Casillero;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;

public class CasilleroTest {

	@Test
	public void test01CasilleroCuandoLoCreoEstaVacio() {
		Casillero casillero=new Casillero(new Posicion(0,0));
		Assert.assertTrue(casillero.isEmpty());
	}

	@Test
	public void test02CasilleroCuandoLeAgregoUnaUnidadNoEstaVacio() {
		Casillero casillero=new Casillero(new Posicion(0,0));
		casillero.agregarUnidadConVida(new Bumblebee());
		Assert.assertFalse(casillero.isEmpty());
	}
	
	@Test
	public void test03CasilleroCuandoLeSacoLaUnidadQuedaVacio() {
		Casillero casillero=new Casillero(new Posicion(0,0));
		casillero.agregarUnidadConVida(new Bumblebee());
		casillero.quitarUnidadActual();
		Assert.assertTrue(casillero.isEmpty());
	}
}
