package fiuba.algo3.test.tablero.contenedor;


import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.CasilleroInexistenteException;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.ContenedorCasilleros;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;

public class ContenedorCasilleroTest {

	@Test
	public void test01ContenedorCasillerosCuandoAgregoUnCasilleroEstaVacio() {
		ContenedorCasilleros contenedor=new ContenedorCasilleros();
		PosicionEnElPlano posicion=new PosicionEnElPlano(0,0);
		
		contenedor.agregarCasilleroVacio(posicion);
		Assert.assertTrue(contenedor.isEmpty(posicion));
	}

	@Test
	public void test02ContenedorCasillerosCuandoLeAgregoUnaUnidadEnUnaPosicionNoEstaVacio() {
		ContenedorCasilleros contenedor=new ContenedorCasilleros();
		PosicionEnElPlano posicion=new PosicionEnElPlano(0,0);
		contenedor.agregarCasilleroVacio(posicion);		
		
		contenedor.agregarUnidad(posicion,new Bumblebee());
		Assert.assertFalse(contenedor.isEmpty(posicion));
	}
	
	@Test
	public void test03ContenedorCasillerosCuandoLeSacoLaUnidadAUnaPosiconQuedaVacio() {
		ContenedorCasilleros contenedor=new ContenedorCasilleros();
		PosicionEnElPlano posicion=new PosicionEnElPlano(0,0);
		contenedor.agregarCasilleroVacio(posicion);
		
		contenedor.agregarUnidad(posicion,new Bumblebee());
		contenedor.quitarUnidadActual(posicion);
		Assert.assertTrue(contenedor.isEmpty(posicion));
	}
	
	@Test(expected=CasilleroInexistenteException.class)
	public void test04PidoCasilleroQUeNoCreeDebeArrojarExcepcion(){
		ContenedorCasilleros contenedor=new ContenedorCasilleros();
		PosicionEnElPlano posicion=new PosicionEnElPlano(0,0);
		
		contenedor.agregarUnidad(posicion,new Bumblebee());
		Assert.fail("No se lanzo la excepcion como debia ser");
	}
}
