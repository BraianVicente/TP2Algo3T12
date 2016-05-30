package fiuba.algo3.modelo.formas;

public class HumanoideOptimusprime extends Forma {

    @Override
    public Forma getAlternativa() {
        return new Peterbilt();
    }

    @Override
    public int getDistanciaAtaque() {
        return 2;
    }

    @Override
    public int getPuntosAtaque() {
        return 50;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 2;
    }

    @Override
    public boolean esHumanoide() {
        return true;
    }

    @Override
    public boolean esVehiculo() {
        return false;
    }
}
