package fiuba.algo3.modelo.formas;

public class HumanoideBumblebee extends FormaHumanoide {

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
    public Integer disminuirEnUnMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
