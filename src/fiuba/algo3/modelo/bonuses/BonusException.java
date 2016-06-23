package fiuba.algo3.modelo.bonuses;

public abstract class BonusException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7737166497516290632L;
	Bonus problematica;
	public BonusException(Bonus b){
		problematica = b;
	}
	public String toString(){
		return descripcion()+" "+problematica.getClass().getName();
	}
	public abstract String descripcion();
}
