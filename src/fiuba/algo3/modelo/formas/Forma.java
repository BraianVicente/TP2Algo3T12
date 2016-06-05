package fiuba.algo3.modelo.formas;

public abstract class Forma {

    /**
     * La forma sabe cu�l es la alternativa, osea, el camaro solo se trnasforma
     * en Bumblebee, el cami�n s�lo se transforma en Optimus, y as�... Sin
     * embargo, dejo los m�todos getVehiculo y getHumanoide en Transformer por
     * las dudas Capaz m�s adelante son �tiles
     *
     * @return
     */
    public abstract Forma getAlternativa();

    public abstract int getDistanciaAtaque();

    public abstract int getPuntosAtaque();

    public abstract int getDistanciaMovimiento();

    public abstract boolean esHumanoide();

    public abstract boolean esVehiculo();

    public abstract Integer disminuirEnUnMovimiento() ;

	public abstract float coeficienteAtaqueVehiculo(float posible);
	public abstract float coeficienteMovimientoEnPantano();
	public abstract float danioRealPorEspinas(float danioPosible);

}
