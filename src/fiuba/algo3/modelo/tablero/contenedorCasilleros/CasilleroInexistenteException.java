package fiuba.algo3.modelo.tablero.contenedorCasilleros;

import fiuba.algo3.modelo.tablero.Posicion;

public class CasilleroInexistenteException extends RuntimeException {

	private Posicion cual;
	public CasilleroInexistenteException(){
		cual = null;
	}

	public CasilleroInexistenteException(Posicion posicion){
		cual = posicion;
	}
	
	public String toString(){
		if(cual!=null){
			return "No existe un casillero en la posicion "+cual.toString();
		}else{
			return "No existe un casillero en la posicion esa";
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -3693172006517777644L;

}
