package fiuba.algo3.modelo.modificadores;

import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.FormaHumanoide;
import fiuba.algo3.modelo.formas.FormaVehiculo;

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
	@Override
	public abstract Object clone();
	
	//-----------m�todos de efectos--------------------//
	//-----------se definen los valores "default" aqu� pero la idea es
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

	public boolean esPantano() {
		
		return false;
	}
	public float coeficienteVelocidadPorForma(Forma forma ){
		return 1;
	}
	public float coeficienteVelocidadPorForma(FormaVehiculo vehiculo) {
		return 1;
	}
	public float coeficienteVelocidadPorForma(FormaHumanoide humanoide) {
		return 1;
	}

	
}
