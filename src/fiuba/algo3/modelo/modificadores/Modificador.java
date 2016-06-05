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
	@Override
	public abstract Object clone();
	
	//-----------métodos de efectos--------------------//
	//-----------se definen los valores "default" aquí pero la idea es
	//que los hijos los sobreescriban
	public boolean puedeMoverse(){
		return true;
	}
	
	public float coeficienteAtaqueModoVehiculo(){
		return 1;
	}
	
	public float coeficienteAtaque(){
		return 1;
	}
	
	public boolean recibeDanio(){
		return true;
	}
	
	public float coeficienteVelocidad(){
		return 1;
	}
	
	public boolean afectadoPorPsionica(){
		return false;
	}
}
