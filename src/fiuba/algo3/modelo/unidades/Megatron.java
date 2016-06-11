package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideMegatron;
import fiuba.algo3.modelo.formas.NaveCybertroniana;

public class Megatron extends Transformer {

    public Megatron(DeathListener command) {
        super(new Decepticons(), command);
        // TODO Auto-generated constructor stub
    }
    public Megatron() {
        super(new Decepticons(), new IgnorarMuerte());
    }
    @Override
    protected Forma getVehiculo() {
        return new NaveCybertroniana();
    }

    @Override
    protected Forma getHumanoide() {
        return new HumanoideMegatron();
    }

    @Override
    public int getVidaMaxima() {
        return 550;
    }

    @Override
    public boolean esTerrestre() {
		return false;
    }

}
