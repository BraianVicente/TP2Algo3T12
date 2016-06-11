package fiuba.algo3.test.tablero.contenedorUnidades;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.contenedorUnidades.ContenedorUnidades;
import fiuba.algo3.modelo.tablero.contenedorUnidades.UnidadNoContenidaException;
import fiuba.algo3.modelo.tablero.contenedorUnidades.UnidadYaContenidaException;
import fiuba.algo3.modelo.unidades.Bumblebee;

public class ContenedorUnidadesTest {
	private final Posicion pos1 = new Posicion(0,0);
	private final Posicion pos2 = new Posicion(1,0);
	private final Posicion pos1b = new Posicion(0,0);
	
	private final Bumblebee trans1 = new Bumblebee(new IgnorarMuerte());
	private final Bumblebee trans2 = new Bumblebee(new IgnorarMuerte());
	
	@Test
	public void testDosBumblebeeDistintosSePuedenAgregar() throws UnidadYaContenidaException {
		ContenedorUnidades c = new ContenedorUnidades();
		c.agregarUnidad(trans1, pos1);
		Assert.assertTrue(c.contiene(trans1));
		Assert.assertTrue(c.ocupada(pos1));
		Assert.assertFalse(c.contiene(trans2));
		c.agregarUnidad(trans2, pos2);
		Assert.assertTrue(c.contiene(trans2));
		Assert.assertTrue(c.ocupada(pos2));
	}
	
	@Test(expected = UnidadYaContenidaException.class)
	public void testElMismoDosVecesCausaError() throws UnidadYaContenidaException {
		ContenedorUnidades c = new ContenedorUnidades();
		c.agregarUnidad(trans1, pos1);
		c.agregarUnidad(trans1, pos2);
	}
	@Test(expected = PosicionOcupadaException.class)
	public void testElMismoLugarCausaError()throws PosicionOcupadaException, UnidadYaContenidaException{
		ContenedorUnidades c = new ContenedorUnidades();
		c.agregarUnidad(trans1, pos1);
		c.agregarUnidad(trans2, pos1b);
	}
	
	@Test(expected = UnidadNoContenidaException.class)
	public void testPedirNoContenidaCausaError()throws UnidadNoContenidaException, UnidadYaContenidaException{
		ContenedorUnidades c = new ContenedorUnidades();
		c.agregarUnidad(trans1, pos1);
		c.obtenerPosicion(trans2);
	}
	
	
	@Test(expected = PosicionLibreException.class)
	public void testPedirPosicionNoContenidaCausaError()throws PosicionLibreException, UnidadYaContenidaException{
		ContenedorUnidades c = new ContenedorUnidades();
		c.agregarUnidad(trans1, pos1);
		c.obtenerUnidad(pos2);
	}
	
	public void testDistintasReferenciasMismaPosicion()throws PosicionLibreException, UnidadYaContenidaException{
		ContenedorUnidades c = new ContenedorUnidades();
		c.agregarUnidad(trans1, pos1);
		Assert.assertTrue(c.ocupada(pos1b));
	}
}
