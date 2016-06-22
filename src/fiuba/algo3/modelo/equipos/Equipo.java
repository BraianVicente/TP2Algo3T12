package fiuba.algo3.modelo.equipos;

import fiuba.algo3.modelo.DeathListener;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.modelo.unidades.UnidadCombinable;


public abstract class Equipo {

    private UnidadCombinable combinacion;

    @Override
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
    
    public abstract UnidadCombinable getCombination(Tablero tab,Unidad unita, Unidad unitb, Unidad unitc);

    public Unidad crearCombinacion(Tablero tab,Unidad unita, Unidad unitb, Unidad unitc) {
      this.combinacion = this.getCombination( tab, unita, unitb, unitc) ;
      return combinacion;
    }

    public UnidadCombinable obtenerUnidadCombinada(){
        return this.combinacion ;
    }

    public boolean tieneCombinacion() {
        return (this.combinacion != null) ; 
    }

	public void combinacionDesarmada() {
		this.combinacion=null;
		
	}

	abstract public Equipo equipoContrario() ;

}
