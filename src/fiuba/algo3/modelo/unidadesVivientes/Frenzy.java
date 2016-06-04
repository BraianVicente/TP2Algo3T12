package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Duster;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideFrenzy;

public class Frenzy extends Transformer {

    public Frenzy(DeathListener command) {
        super(new Decepticons(), command);
    }

    public Frenzy() {
        super(new Decepticons(), new IgnorarMuerte());
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

    @Override
    public boolean esTerrestre() {
        return true;
    }

}
