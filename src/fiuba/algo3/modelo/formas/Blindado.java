package fiuba.algo3.modelo.formas;

public class Blindado extends FormaTerrestre {

    @Override
    public Forma getAlternativa() {
        return new HumanoideBonecrusher();
    }

    @Override
    public int getDistanciaAtaque() {
        return 3;
    }

    @Override
    public int getPuntosAtaque() {
        return 30;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 8;
    }



    @Override
    public Integer disminuirEnUnMovimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
