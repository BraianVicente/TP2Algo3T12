package fiuba.algo3.test.obligatorias;


import org.junit.Assert;
import org.junit.Test;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.bonuses.BonusBurbuja;
import fiuba.algo3.modelo.bonuses.BonusDobleCanion;
import fiuba.algo3.modelo.bonuses.BonusFlash;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Bonecrusher;
import fiuba.algo3.modelo.unidades.Ratchet;

public class TerceraEntregaTest {

	private static final Posicion posb=new Posicion(1,1);
	private static final Posicion posb2=new Posicion(1,2);
	private static final Posicion posRat=new Posicion(0,0);//vida inicial: 150, atkv: 35, atkh:5
	private static final Posicion posBone=new Posicion(0,1);//vida inicial: 200, atkv: 30, atkh:30
	@Test
	public void test01LuchaDobleCanionVehiculo() {
		Tablero tablero = new Tablero();
		Ratchet rat = new Ratchet();
		Bonecrusher bone = new Bonecrusher();
		tablero.agregarUnidad(posRat, rat);
		tablero.agregarUnidad(posBone, bone);
		
		Bonus b = new BonusDobleCanion(null);
		tablero.agregarBonus(b, posb);
		
		tablero.mover(rat, posb);//ratchet agarra el bonus
		//(faltan 3 turnos para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200-35*2, bone.getVida());//danio doble!
		
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(falta 2 turno para que pase el efecto)
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(faltan 1 turnos para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200-35*2-35*2, bone.getVida());//danio doble!
		
		
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		
		//(pasa el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200-35*2-35*2-35, bone.getVida());//danio simple!
	}
	
	@Test
	public void test01LuchaDobleCanionHumanoide() {
		Tablero tablero = new Tablero();
		
		Ratchet rat = new Ratchet();
		rat.transformar();
		tablero.agregarUnidad(posRat, rat);
		
		Bonecrusher bone = new Bonecrusher();
		bone.transformar();

		tablero.agregarUnidad(posBone, bone);
		
		Bonus b = new BonusDobleCanion(null);
		tablero.agregarBonus(b, posb);
		
		tablero.mover(rat, posb);//ratchet agarra el bonus
		//(faltan 3 turnos para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkh = 5
		
		Assert.assertEquals(200-5*2, bone.getVida());//danio doble!
		
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(falta 2 turno para que pase el efecto)
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(faltan 1 turnos para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkh = 5
		
		Assert.assertEquals(200-5*2-5*2, bone.getVida());//danio doble!
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(pasa el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200-5*2-5*2-5, bone.getVida());//danio simple!
	}
	
	
	@Test
	public void test02LuchaBurbujaHumanoide() {
		Tablero tablero = new Tablero();
		
		Ratchet rat = new Ratchet();
		rat.transformar();
		tablero.agregarUnidad(posRat, rat);
		
		Bonecrusher bone = new Bonecrusher();
		bone.transformar();

		tablero.agregarUnidad(posBone, bone);
		
		Bonus b = new BonusBurbuja(null);
		tablero.agregarBonus(b, posb);
		
		tablero.mover(bone, posb);//bonecrusher agarra el bonus
		//(faltan 2 turnos para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkh = 5
		
		Assert.assertEquals(200, bone.getVida());//danio doble!
		
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(falta 1 turno para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkh = 5
		
		Assert.assertEquals(200, bone.getVida());//danio doble!
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(pasa el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200-5, bone.getVida());//danio simple!
	}
	
	@Test
	public void test02LuchaBurbujaVehiculo() {
		Tablero tablero = new Tablero();
		
		Ratchet rat = new Ratchet();
		tablero.agregarUnidad(posRat, rat);
		
		Bonecrusher bone = new Bonecrusher();
		tablero.agregarUnidad(posBone, bone);
		
		Bonus b = new BonusBurbuja(null);
		tablero.agregarBonus(b, posb);
		
		tablero.mover(bone, posb);//bonecrusher agarra el bonus
		//(faltan 2 turnos para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200, bone.getVida());//danio doble!
		
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(falta 1 turno para que pase el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200, bone.getVida());//danio doble!
		
		tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
		tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
		//(pasa el efecto)
		
		tablero.atacar(rat, bone);//atkv = 35
		
		Assert.assertEquals(200-35, bone.getVida());//danio simple!
	}
	
	//movto ratchet: en vehiculo 8 en humanoide 1
		@Test
		public void test03MovtoVehiculo() {
			Tablero tablero = new Tablero();
			
			Ratchet rat = new Ratchet();
			tablero.agregarUnidad(posRat, rat);
			
			Bonus b = new BonusFlash(null);
			tablero.agregarBonus(b, posb);
			
			tablero.mover(rat, posb);//ratchet agarra el bonus
			//(faltan 3 turnos para que pase el efecto)
			
			Assert.assertEquals(8*3, rat.getVelocidad());
			
			tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
			tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
			//(falta 2 turno para que pase el efecto)
			
			Assert.assertEquals(8*3, rat.getVelocidad());
			
			tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
			tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
			//(falta 1 turno para que pase el efecto)
			
			Assert.assertEquals(8*3, rat.getVelocidad());
			
			tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
			tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
			//(pasa el efecto)
			
			Assert.assertEquals(8, rat.getVelocidad());
		}
		
		//movto ratchet: en vehiculo 8 en humanoide 1
				@Test
				public void test03MovtoHumanoide() {
					Tablero tablero = new Tablero();
					
					Ratchet rat = new Ratchet();
					tablero.agregarUnidad(posRat, rat);
					rat.transformar();
					
					Bonus b = new BonusFlash(null);
					tablero.agregarBonus(b, posb);
					
					tablero.mover(rat, posb);//ratchet agarra el bonus
					//(faltan 3 turnos para que pase el efecto)
					
					Assert.assertEquals(3, rat.getVelocidad());
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//(falta 2 turno para que pase el efecto)
					
					Assert.assertEquals(3, rat.getVelocidad());
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//(falta 1 turno para que pase el efecto)
					
					Assert.assertEquals(3, rat.getVelocidad());
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//(pasa el efecto)
					
					Assert.assertEquals(1, rat.getVelocidad());
				}
				
				//movto ratchet: en vehiculo 8 en humanoide 1
				@Test
				public void test03MovtoVHV() {
					Tablero tablero = new Tablero();
					
					Ratchet rat = new Ratchet();
					tablero.agregarUnidad(posRat, rat);
					
					
					Bonus b = new BonusFlash(null);
					tablero.agregarBonus(b, posb);
					
					tablero.mover(rat, posb);//ratchet agarra el bonus
					//(faltan 3 turnos para que pase el efecto)
					
					Assert.assertEquals(8*3, rat.getVelocidad());
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//(falta 2 turno para que pase el efecto)
					rat.transformar();
					Assert.assertEquals(3, rat.getVelocidad());
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//(falta 1 turno para que pase el efecto)
					rat.transformar();
					Assert.assertEquals(8*3, rat.getVelocidad());
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//(pasa el efecto)
					
					Assert.assertEquals(8, rat.getVelocidad());
				}
				
				
				//movto ratchet: en vehiculo 8 en humanoide 1
				@Test
				public void test04MovtoHumanoideFlashDoble() {
					Tablero tablero = new Tablero();
					
					Ratchet rat = new Ratchet();
					tablero.agregarUnidad(posRat, rat);
					rat.transformar();
					
					//-----------
					Bonus b = new BonusFlash(null);
					tablero.agregarBonus(b, posb);
					tablero.mover(rat, posb);//ratchet agarra el bonus
					Assert.assertEquals(3, rat.getVelocidad()); //flash 1 pasa en 3 turnos)
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//----------
					
					Bonus b2 = new BonusFlash(null);
					tablero.agregarBonus(b2, posb);
					tablero.mover(rat, posb2);//ratchet agarra el bonus
					Assert.assertEquals(3, rat.getVelocidad()); //(flash 1 pasa en 2 turnos)(flash 2 pasa en 3 turnos)
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					//--------------
					Assert.assertEquals(3, rat.getVelocidad()); //(flash 1 pasa en 1 turnos)(flash 2 pasa en 2 turnos)
					
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
					
					//--------------
					Assert.assertEquals(1, rat.getVelocidad()); //(flash 1 pasa en 0 turnos)(flash 2 pasa en 1 turnos)
					//el flash 2 fue ignorado!!
					tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
					tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno					
				}
				
		@Test
		public void test04RatchetAgarraDisparoYFlash() {
			Tablero tablero = new Tablero();
			
			Ratchet rat = new Ratchet();
			tablero.agregarUnidad(posRat, rat);
			
			Bonecrusher bone = new Bonecrusher();
			tablero.agregarUnidad(posBone, bone);
			
			
			
			//-----------
			Bonus b = new BonusFlash(null);
			tablero.agregarBonus(b, posb);
			tablero.mover(rat, posb);//ratchet agarra el bonus
			
			tablero.pasarTurnoEquipo(new Autobots());//los autobots terminan su turno
			tablero.pasarTurnoEquipo(new Decepticons());//los decepticons terminan su turno
			
			Bonus b2 = new BonusDobleCanion(null);
			tablero.agregarBonus(b2, posb2);
			
			tablero.mover(rat, posb2);//ratchet agarra el bonus
			
			Assert.assertEquals(8*3, rat.getVelocidad()); //flash 1 pasa en 3 turnos)
			tablero.atacar(rat, bone);
			Assert.assertEquals(200-35*2, bone.getVida());//danio doble!
			
		}

}
