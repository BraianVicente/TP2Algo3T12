package fiuba.algo3.modelo.formas;

public class HumanoideOptimusprime extends FormaTerrestre {

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


    @Override
    public Integer disminuirEnUnMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
