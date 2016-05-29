/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.contenedorCasilleros;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

import java.util.HashMap;

/**
 *
 * @author brahvic
 */
public class ContenedorCasilleros  {
    private HashMap<Posicion,Casillero> misCasilleros ;

    public ContenedorCasilleros(){
        this.misCasilleros = new HashMap<Posicion,Casillero>();
    }

    public void agregarCasilleroVacio(Posicion posicion) {
        this.misCasilleros.put(posicion,new Casillero(posicion));
    }

    public boolean isEmpty(Posicion posicion) {
        return obtenerCasillero(posicion).isEmpty();
        
    }
    
    public void quitarUnidadActual(Posicion posicion) {
       obtenerCasillero(posicion).quitarUnidadActual();
        
    }
    public void agregarUnidad(Posicion posicion, Unidad unidad) {
    	obtenerCasillero(posicion).agregarUnidad(unidad);
        
    }
    
    private Casillero obtenerCasillero(Posicion posicion){
    	Casillero casillero=this.misCasilleros.get(posicion);
    	if(casillero==null) throw new CasilleroInexistenteException();
    	return casillero;
    }
    public Unidad obtenerUnidad(Posicion posicion){
    	return obtenerCasillero(posicion).obtenerUnidad();
    }

	public void agregarChispa(Posicion posicion,Chispa chispa) {
		obtenerCasillero(posicion).agregarChispa(chispa);
		
	}
}
