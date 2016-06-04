package fiuba.algo3.modelo;

public abstract class UnidadException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Unidad problematica;
	public UnidadException(Unidad problematica){
		this.problematica = problematica;
	}
	public String toString(){
		return descripcion()+problematica.getClass().getName();
	}
	public abstract String descripcion();
}
