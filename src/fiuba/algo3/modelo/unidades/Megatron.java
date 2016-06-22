package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.unidades.muerte.IgnorarMuerte;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.HumanoideMegatron;
import fiuba.algo3.modelo.formas.NaveCybertroniana;

public class Megatron extends Transformer {

    public Megatron(DeathListener command) {
        super(new NaveCybertroniana(), new Decepticons(), command);
        // TODO Auto-generated constructor stub
    }
    public Megatron() {
        this(new IgnorarMuerte());
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

   

}
