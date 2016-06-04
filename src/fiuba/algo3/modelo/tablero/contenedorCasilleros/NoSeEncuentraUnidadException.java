package fiuba.algo3.modelo.tablero.contenedorCasilleros;

import fiuba.algo3.modelo.Unidad;

public class NoSeEncuentraUnidadException extends Exception {
	private Unidad escurridiza;
	public NoSeEncuentraUnidadException(Unidad escurridiza){
		this.escurridiza = escurridiza;
	}
	public String toString(){
		return "No encontré esa Unidad que se llama "+escurridiza.getClass().getName();
	}
}
