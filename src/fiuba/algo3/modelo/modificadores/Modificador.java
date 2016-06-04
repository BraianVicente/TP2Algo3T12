package fiuba.algo3.modelo.modificadores;

/**
 * Modela:
 * -Nebulosa de andr�meda
 * -tormenta psi�nica
 * -doble ca��n
 * -burbuja inmaculada
 * -flash
 * 
 * @author Jos� Sb
 *
 */
public abstract class Modificador {
	public abstract void pasaTurno();
	
	abstract boolean haceEfecto();//visibilidad de paquete
	
	//-----------m�todos de efectos--------------------//
	//-----------se definen los valores "default" aqu� pero la idea es
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
