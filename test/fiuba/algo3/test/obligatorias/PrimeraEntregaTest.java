package fiuba.algo3.test.obligatorias;



import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.tablero.AtaqueInvalidoException;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.*;


public class PrimeraEntregaTest {

	@Test
	public void test01AgregarTransformerYMoverValidamenteEnVehiculo() {
		Transformer bumblebee=new Bumblebee();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(5,3);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(bumblebee,posicionFin);
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
		
		tablero.mover(bumblebee,posicionFin);
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
		
		tablero.mover(bumblebee,posicionFin);
		fail("Bumblebee en humanoide no se puede mover tanto");
	}
	@Test(expected=MovimientoInvalidoException.class)
	public void test04AgregarTransformerYMoverInvalidamenteEnVehiculo() {
		Transformer bumblebee=new Bumblebee();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(6,1);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(bumblebee,posicionFin);
		fail("Bumblebee en vehiculo no se puede mover tanto");
	}
	
	@Test(expected=MovimientoInvalidoException.class)
	public void test05AgregarTransformerYMoverInvalidamenteEnVehiculo() {
		Transformer bumblebee=new Bumblebee();
		Tablero tablero=new Tablero();
		Posicion posicionInicio=new Posicion(0,0);
		Posicion posicionFin=new Posicion(6,1);
		tablero.agregarUnidad(posicionInicio, bumblebee);
		
		tablero.mover(bumblebee,posicionFin);
		fail("Bumblebee en vehiculo no se puede mover tanto");
	}

	@Test
	public void test06AgregarTransformerEmpiezaComoVehiculoYLoTransformoVariasVeces(){
		Tablero tablero=new Tablero();
		Posicion posicion=new Posicion(0,0);
		Transformer optimusPrime = new Optimusprime();
		tablero.agregarUnidad(posicion, optimusPrime);
		
		Assert.assertTrue(optimusPrime.esVehiculo());
		optimusPrime.transformar();
		Assert.assertTrue(optimusPrime.esHumanoide());
		optimusPrime.transformar();
		Assert.assertTrue(optimusPrime.esVehiculo());
		Assert.assertFalse(optimusPrime.esHumanoide());
	}
	
	@Test
	public void test07AgregarTodosLosTransformersYLaChispa(){
		Tablero tablero=new Tablero();
		Transformer optimusPrime = new Optimusprime();
		Transformer bumblebee = new Bumblebee();
		Transformer ratchet = new Ratchet();
		Transformer megatron = new Megatron();
		Transformer  frenzy= new Frenzy();
		Transformer bonecrusher = new Bonecrusher();
		Chispa chispa=ChispaSuprema.getInstance();
		
		Posicion posicion= new Posicion(1,1);
		tablero.agregarUnidad(posicion, optimusPrime);
		posicion =new Posicion(0,0);
		tablero.agregarUnidad(posicion, bumblebee);
		posicion =new Posicion(0,2);
		tablero.agregarUnidad(posicion, ratchet);
		
		posicion =new Posicion(4,4);
		tablero.agregarChispa(posicion);
		
		posicion =new Posicion(8,8);
		tablero.agregarUnidad(posicion, megatron);
		posicion =new Posicion(9,9);
		tablero.agregarUnidad(posicion,frenzy);
		posicion =new Posicion(9,7);
		tablero.agregarUnidad(posicion, bonecrusher);
		
		Assert.assertEquals(bumblebee,tablero.obtenerUnidad(new Posicion(0,0)));
		Assert.assertEquals(optimusPrime,tablero.obtenerUnidad(new Posicion(1,1)));
		Assert.assertEquals(ratchet,tablero.obtenerUnidad(new Posicion(0,2)));
		Assert.assertEquals(megatron,tablero.obtenerUnidad(new Posicion(8,8)));
		Assert.assertEquals(frenzy,tablero.obtenerUnidad(new Posicion(9,9)));
		Assert.assertEquals(bonecrusher,tablero.obtenerUnidad(new Posicion(9,7)));
		
		
	}
	
	/*Tablero tablero=new Tablero();
	Transformer optimusPrime = new Optimusprime();
	Transformer bumblebee = new Bumblebee();
	Transformer megatron = new Megatron();
	Transformer  frenzy= new Frenzy();
	Posicion posicion= new Posicion(1,1);
	tablero.agregarUnidad(posicion, optimusPrime);
	posicion =new Posicion(0,0);
	tablero.agregarUnidad(posicion, bumblebee);
	posicion =new Posicion(1,0);
	tablero.agregarUnidad(posicion, megatron);
	posicion =new Posicion(0,1);
	tablero.agregarUnidad(posicion,frenzy);*/
	@Test(expected=FriendlyFireException.class)
	public void test08AtaqueAmigoNoDeberiaPermitirseEntreAutobots() throws FriendlyFireException, NoSeEncuentraUnidadException{
		Tablero tablero=new Tablero();
		Transformer optimusPrime = new Optimusprime();
		Transformer bumblebee = new Bumblebee();

		Posicion posicion= new Posicion(1,1);
		tablero.agregarUnidad(posicion, optimusPrime);
		posicion =new Posicion(0,0);
		tablero.agregarUnidad(posicion, bumblebee);
		
		optimusPrime.atacarA(bumblebee);
		
	}
	
	@Test
	public void test09AtaqueAmigoNoDeberiaPermitirseEntreDecepticonsYNoDeberiaSacarVida() throws NoSeEncuentraUnidadException{
		Tablero tablero=new Tablero();
		
		Transformer megatron = new Megatron();
		Transformer  frenzy= new Frenzy();

		Posicion posicion= new Posicion(1,0);
		tablero.agregarUnidad(posicion, megatron);
		posicion =new Posicion(0,1);
		tablero.agregarUnidad(posicion,frenzy);
		int vidaOriginalMegatron=megatron.getVida();
		try{
		
			frenzy.atacarA(megatron);
			
				
			}catch(FriendlyFireException e){
				Assert.assertTrue(vidaOriginalMegatron==megatron.getVida());
			}
	}
	
	@Test
	public void test09OptimusAtacaMegatronDistanciaValidaYSacaVida() throws FriendlyFireException, NoSeEncuentraUnidadException{
		Tablero tablero=new Tablero();
		Transformer megatron = new Megatron();
		Transformer  optimusPrime= new Optimusprime();

		Posicion posicion= new Posicion(1,0);
		tablero.agregarUnidad(posicion, megatron);
		posicion =new Posicion(0,1);
		tablero.agregarUnidad(posicion,optimusPrime);
		int vidaOriginalMegatron=megatron.getVida();
		optimusPrime.atacarA(megatron);
		Assert.assertTrue(vidaOriginalMegatron-optimusPrime.getPuntosAtaque()==megatron.getVida());
		
	}
	@Test
	public void test10OptimusAtacaMegatronDistanciaInvalidaYNoSacaVida() throws FriendlyFireException, NoSeEncuentraUnidadException{
		Tablero tablero=new Tablero();
		
		Transformer megatron = new Megatron();
		Transformer  optimusPrime= new Optimusprime();
		optimusPrime.transformar();

		Posicion posicionMegatron= new Posicion(3,0);
		tablero.agregarUnidad(posicionMegatron, megatron);
		Posicion posicionOptimus =new Posicion(0,1);
		tablero.agregarUnidad(posicionOptimus,optimusPrime);
		int vidaOriginalMegatron=megatron.getVida();
		try{
		
			tablero.atacar(optimusPrime,megatron);
			fail("Deberia lanzar excepcion de distancia");
			
				
			}catch(AtaqueInvalidoException e){
				Assert.assertTrue(vidaOriginalMegatron==megatron.getVida());
			}
	}
	
	@Test
	public void test11OptimusAtacaMegatronDistanciaInvalidaCambiaDeFormaPuedeAtacar() throws FriendlyFireException, NoSeEncuentraUnidadException{
		Tablero tablero=new Tablero();
		
		Transformer megatron = new Megatron();
		Transformer  optimusPrime= new Optimusprime();
		optimusPrime.transformar();

		Posicion posicionMegatron= new Posicion(3,0);
		tablero.agregarUnidad(posicionMegatron, megatron);
		Posicion posicionOptimus =new Posicion(0,1);
		tablero.agregarUnidad(posicionOptimus,optimusPrime);
		int vidaOriginalMegatron=megatron.getVida();
		try{
		
			tablero.atacar(optimusPrime, megatron);
			fail("Deberia lanzar excepcion de distancia");
				
			}catch(AtaqueInvalidoException e){
				Assert.assertTrue(vidaOriginalMegatron==megatron.getVida());
			}
		optimusPrime.transformar();
		optimusPrime.atacarA(megatron);
		Assert.assertTrue(vidaOriginalMegatron-optimusPrime.getPuntosAtaque()==megatron.getVida());
	}
}
