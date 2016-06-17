package fiuba.algo3.modelo.equipos;

import fiuba.algo3.modelo.unidades.Menasor;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.modelo.unidades.UnidadCombinable;

public class Decepticons extends Equipo {

    @Override
    public boolean mismoEquipo(Equipo otro) {
        return otro.mismoEquipo(this);///preguntar al profesor si se puede poner esto en la super
    }

    @Override
    public boolean mismoEquipo(Autobots otro) {
        return false;
    }

    @Override
    public boolean mismoEquipo(Decepticons otro) {
        return true;
    }

    @Override
    public boolean mismoEquipo(Ninguno otro) {
        return false;
    }

	@Override
	public UnidadCombinable getCombination() {
		return new Menasor();
	}
	
	@Override
	public String toString() {
		return "Decepticons";
	}

}
