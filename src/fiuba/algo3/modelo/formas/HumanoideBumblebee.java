package fiuba.algo3.modelo.formas;

public class HumanoideBumblebee extends Forma {

    @Override
    public Forma getAlternativa() {
        return new Camaro();
    }

    @Override
    public int getDistanciaAtaque() {
        return 1;
    }

    @Override
    public int getPuntosAtaque() {
        return 40;
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
