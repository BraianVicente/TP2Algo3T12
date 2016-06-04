package fiuba.algo3.modelo.tablero;

public abstract class PosicionException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Posicion problematica;
	public PosicionException(Posicion p){
		problematica = p;
	}
	public String toString(){
		return descripcion()+" "+problematica.toString();
	}
	
	public abstract String descripcion();
}
