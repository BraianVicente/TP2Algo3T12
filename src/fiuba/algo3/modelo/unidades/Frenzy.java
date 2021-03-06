package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Duster;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideFrenzy;

public class Frenzy extends Transformer {

    public Frenzy() {
        super(new Duster(), new Decepticons());
    }
    @Override
    protected Forma getVehiculo() {
        return new Duster();
    }

    @Override
    protected Forma getHumanoide() {
        return new HumanoideFrenzy();
    }

    @Override
    public int getVidaMaxima() {
        return 200;
    }

}
