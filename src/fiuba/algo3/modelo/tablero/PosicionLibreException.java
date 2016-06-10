package fiuba.algo3.modelo.tablero;

public class PosicionLibreException extends PosicionException {

	public PosicionLibreException(Posicion p) {
		super(p);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -5561558541375530331L;

	@Override
	public String descripcion() {
		return "La posicion esta libre";
	}

}
