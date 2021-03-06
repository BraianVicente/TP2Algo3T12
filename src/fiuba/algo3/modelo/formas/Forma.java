package fiuba.algo3.modelo.formas;

import fiuba.algo3.modelo.modificadores.ContenedorModificadores;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.tablero.superficies.Superficie;

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


	public abstract float coeficienteAtaqueVehiculo(float posible);
	public abstract float coeficienteMovimientoEnPantano();
	public abstract float danioRealPorEspinas(float danioPosible);
	
	public abstract boolean esAerea();

	public abstract boolean esTerrestre();

	public abstract float obtenerCoeficientePorVelocidad(ContenedorModificadores modificadores) ;

	public abstract String nombreImagen();

	public float obtenerCoeficienteVelocidad(Superficie superficie) {
		return superficie.obtenerVelocidadParaForma(this);
	}
	public Plano getPlanoPerteneciente() {
		if(esAerea())return Plano.AEREO;
		if(esTerrestre())return Plano.TERRESTRE;
		return null;
	}

    public abstract String nombre() ;
    
}
