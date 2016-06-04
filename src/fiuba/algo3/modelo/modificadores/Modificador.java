package fiuba.algo3.modelo.modificadores;

/**
 * Modela:
 * -Nebulosa de andrómeda
 * -tormenta psiónica
 * -doble cañón
 * -burbuja inmaculada
 * -flash
 * 
 * @author José Sb
 *
 */
public abstract class Modificador {
	public abstract void pasaTurno();
	
	abstract boolean haceEfecto();//visibilidad de paquete
	
	//-----------métodos de efectos--------------------//
	//-----------se definen los valores "default" aquí pero la idea es
	//que los hijos los sobreescriban
	protected boolean puedeMoverse(){
		return true;
	}
	
	protected float coeficienteAtaqueModoVehiculo(){
		return 1;
	}
	
	protected float coeficienteAtaque(){
		return 1;
	}
	
	protected boolean recibeDanio(){
		return true;
	}
	
	protected float coeficienteVelocidad(){
		return 1;
	}
	
	protected boolean afectadoPorPsionica(){
		return false;
	}
}
