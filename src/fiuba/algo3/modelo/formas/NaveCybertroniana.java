package fiuba.algo3.modelo.formas;

public class NaveCybertroniana extends FormaAerea {

    @Override
    public Forma getAlternativa() {
        return new HumanoideMegatron();
    }

    @Override
    public int getDistanciaAtaque() {
        return 2;
    }

    @Override
    public int getPuntosAtaque() {
        return 55;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 8;
    }

    @Override
    public boolean esHumanoide() {
        return false;
    }

    @Override
    public boolean esVehiculo() {
        return true;
    }

    @Override
    public void disminuirPuntosAtaque() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer disminuirEnUnMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
