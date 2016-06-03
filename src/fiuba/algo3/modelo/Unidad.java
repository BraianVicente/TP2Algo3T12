package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.FriendlyFireException;

public abstract class Unidad {

    public abstract boolean existe();

    //-------------------equipo-------------
    protected final Equipo equipo;//equipo no tiene estado y es Final! Fontela, hago getter?

    protected Unidad(Equipo equipo) {
        this.equipo = equipo;
    }

    public boolean es(Equipo e) {
        return equipo.equals(e);
    }

    public abstract boolean puedeAtacar(Posicion a, Posicion desde);

    public abstract boolean puedeMoverse(Posicion a, Posicion desde);

    public abstract void recibirDanio(Unidad atacante, int danio) throws FriendlyFireException;

    public abstract boolean tieneChispa();

    public abstract Forma getFormaActual() ;
    
    public abstract int getVida();

}
