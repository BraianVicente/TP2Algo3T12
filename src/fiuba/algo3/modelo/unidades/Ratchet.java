package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.formas.AvionF22;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideRatchet;

public class Ratchet extends Transformer {

    public Ratchet(DeathListener command) {
        super(new AvionF22(), new Autobots(), command);
    }
    
    public Ratchet() {
        this(new IgnorarMuerte());
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
