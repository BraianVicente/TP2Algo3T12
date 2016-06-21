package fiuba.algo3.modelo.equipos;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.modelo.unidades.UnidadCombinable;

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
	public UnidadCombinable getCombination(Tablero tab, Unidad unita, Unidad unitb, Unidad unitc) {
		return null; // throw exception
	}

}
