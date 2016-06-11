package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Blindado;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideBonecrusher;

public class Bonecrusher extends Transformer {

	public Bonecrusher(DeathListener command) {
        super(new Decepticons(), command);
    }
	public Bonecrusher() {
        super(new Decepticons(), new IgnorarMuerte());
    }

    @Override
    protected Forma getVehiculo() {
        return new Blindado();
    }

    @Override
    protected Forma getHumanoide() {
        return new HumanoideBonecrusher();
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
