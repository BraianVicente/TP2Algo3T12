package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.Camaro;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBumblebee;

public class Bumblebee extends Transformer {

    public Bumblebee() {
        super(new Camaro(), new Autobots());
    }

    @Override
    protected Forma getVehiculo() {
        return new Camaro();
    }

    @Override
    protected Forma getHumanoide() {
        return new HumanoideBumblebee();
    }

    @Override
    public int getVidaMaxima() {
        return 350;
    }
}
