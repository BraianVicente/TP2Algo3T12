package fiuba.algo3.modelo.formas;

public abstract class Forma {
	
	/**
	 * La forma sabe cuál es la alternativa, osea, el camaro solo se trnasforma en Bumblebee,
	 * el camión sólo se transforma en Optimus, y así...
	 * Sin embargo, dejo los métodos getVehiculo y getHumanoide en Transformer por las dudas
	 * Capaz más adelante son útiles
	 * @return
	 */
	public abstract Forma getAlternativa();

	public abstract int getDistanciaAtaque();

	public abstract int getPuntosAtaque();

	public abstract int getDistanciaMovimiento();

	public abstract boolean esHumanoide();
	
	public abstract boolean esVehiculo();

}