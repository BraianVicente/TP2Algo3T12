package fiuba.algo3.modelo.equipos;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.unidades.muerte.DesarmadorCombinable;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Superion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.modelo.unidades.UnidadCombinable;

public class Autobots extends Equipo {

    @Override
    public boolean mismoEquipo(Equipo otro) {
        return otro.mismoEquipo(this);
    }

    @Override
    public boolean mismoEquipo(Autobots otro) {
        return true;
    }

    @Override
    public boolean mismoEquipo(Decepticons otro) {
        return false;
    }

    @Override
    public boolean mismoEquipo(Ninguno otro) {
        return false;
    }

	@Override
	public UnidadCombinable getCombination(Tablero tab,Unidad unita, Unidad unitb, Unidad unitc) {
		return new Superion(new DesarmadorCombinable(tab,this), unita,unitb,unitc);
	}
	
	@Override
	public String toString() {
		return "Autobots";
		
	}

	@Override
	public Equipo equipoContrario() {
		return new Decepticons();
	}

}
