package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.AvionF22;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideRatchet;

public class Ratchet extends Transformer {

    public Ratchet() {
        super(new AvionF22(), new Autobots());
    }
    

    @Override
    protected Forma getVehiculo() {
        return new AvionF22();
    }

    @Override
    protected Forma getHumanoide() {
        return new HumanoideRatchet();
    }

    @Override
    public int getVidaMaxima() {
        return 150;
    }



}
