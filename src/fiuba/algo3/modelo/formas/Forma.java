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

    public  void disminuriPuntosAtaque() {
        //Only air units override paramether
        throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
    }

}
