package fiuba.algo3.test.obligatorias;



import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidadesVivientes.Bumblebee;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Optimusprime;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;


public class PrimeraEntregaTest {

	@Test
	public void test01AgregarTransformerYMoverValidamenteEnVehiculo() {
		Transformer bumblebee=new Bumblebee();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(5,3);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(posicionInicio,posicionFin);
		Assert.assertEquals(bumblebee,tablero.obtenerUnidad(posicionFin));
	}
	
	@Test
	public void test02AgregarTransformerYMoverValidamenteEnHumanoide() {
		Transformer bumblebee=new Bumblebee();
		bumblebee.transformar();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(2,1);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(posicionInicio,posicionFin);
		Assert.assertEquals(bumblebee,tablero.obtenerUnidad(posicionFin));
	}
	
	@Test(expected=MovimientoInvalidoException.class)
	public void test03AgregarTransformerYMoverInvalidamenteEnHumanoide() {
		Transformer bumblebee=new Bumblebee();
		bumblebee.transformar();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(3,1);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(posicionInicio,posicionFin);
		fail("Bumblebee en humanoide no se puede mover tanto");
	}
	@Test(expected=MovimientoInvalidoException.class)
	public void test04AgregarTransformerYMoverInvalidamenteEnVehiculo() {
		Transformer bumblebee=new Bumblebee();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(6,1);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(posicionInicio,posicionFin);
		fail("Bumblebee en vehiculo no se puede mover tanto");
	}
	
	@Test(expected=MovimientoInvalidoException.class)
	public void test05AgregarTransformerYMoverInvalidamenteEnVehiculo() {
		Transformer bumblebee=new Bumblebee();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(6,1);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(posicionInicio,posicionFin);
		fail("Bumblebee en vehiculo no se puede mover tanto");
	}

	public void test06AgregarTransformerEmpiezaComoVehiculoYLoTransformoVariasVeces(){
		Transformer optimusPrime = new Optimusprime();
		Assert.assertTrue(optimusPrime.esVehiculo());
		optimusPrime.transformar();
		Assert.assertTrue(optimusPrime.esHumanoide());
		optimusPrime.transformar();
		Assert.assertTrue(optimusPrime.esVehiculo());
		optimusPrime.transformar();
		Assert.assertFalse(optimusPrime.esHumanoide());
	}
}
