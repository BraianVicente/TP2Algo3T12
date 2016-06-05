package fiuba.algo3.modelo.equipos;

import fiuba.algo3.modelo.Unidad;

public abstract class Equipo {

    public boolean equals(Object otro) {
        if (otro == null) {
            return false;
        }
        if (otro == this) {
            return true;
        }
        if (!(otro instanceof Equipo)) {
            return false;
        }
        return mismoEquipo((Equipo) otro);
    }

    public abstract boolean mismoEquipo(Equipo otro);
    
    public boolean mismoEquipo(Equipo a, Equipo b) {
    	return (mismoEquipo(a) && mismoEquipo(b));
    }

    public abstract boolean mismoEquipo(Autobots otro);

    public abstract boolean mismoEquipo(Decepticons otro);

    public abstract boolean mismoEquipo(Ninguno otro);
    
    public abstract Unidad getCombination();
}
