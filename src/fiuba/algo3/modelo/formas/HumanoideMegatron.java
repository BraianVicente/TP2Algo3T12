package fiuba.algo3.modelo.formas;

public class HumanoideMegatron extends FormaHumanoide {

    @Override
    public Forma getAlternativa() {
        return new NaveCybertroniana();
    }

    @Override
    public int getDistanciaAtaque() {
        return 3;
    }

    @Override
    public int getPuntosAtaque() {
        return 10;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 1;
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
