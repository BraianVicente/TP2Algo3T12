package fiuba.algo3.modelo.unidadesVivientes;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideOptimusprime;
import fiuba.algo3.modelo.formas.Peterbilt;

public class Optimusprime extends Transformer {

    public Optimusprime(DeathListener command) {
        super(new Autobots(), command);
    }
    public Optimusprime() {
        super(new Autobots(), new IgnorarMuerte());
    }

    @Override
    protected Forma getVehiculo() {
        return new Peterbilt();
    }

    @Override
    protected Forma getHumanoide() {
        return new HumanoideOptimusprime();
    }

    @Override
    public int getVidaMaxima() {
        return 500;
    }

    @Override
    public boolean esTerrestre() {
        return true;
    }

}
