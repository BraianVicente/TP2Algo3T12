package fiuba.algo3.modelo.equipos;

import fiuba.algo3.modelo.Unidad;

public class Ninguno extends Equipo {

    @Override
    public boolean mismoEquipo(Equipo otro) {
        return otro.mismoEquipo(this);
    }

    @Override
    public boolean mismoEquipo(Autobots otro) {
        return false;
    }

    @Override
    public boolean mismoEquipo(Decepticons otro) {
        return false;
    }

    @Override
    public boolean mismoEquipo(Ninguno otro) {
        return true;
    }

	@Override
	public Unidad getCombination() {
		return null; // throw exception
	}

}
