/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.contenedorCasilleros;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;

import java.util.HashMap;

/**
 *
 * @author brahvic
 */
public class ContenedorCasilleros {

    public HashMap<Posicion, Casillero> misCasilleros;

    public ContenedorCasilleros() {
        this.misCasilleros = new HashMap<Posicion, Casillero>();
    }

    public void agregarCasilleroVacio(Posicion posicion) {
        this.misCasilleros.put(posicion, new Casillero(posicion));
    }

    public boolean isEmpty(Posicion posicion) {
        return obtenerCasillero(posicion).isEmpty();

    }

    public void quitarUnidadActual(Posicion posicion) {
        obtenerCasillero(posicion).quitarUnidadActual();

    }

    public void agregarUnidad(Posicion posicion, Unidad unidad) {
        if (this.isEmpty(posicion)){
            obtenerCasillero(posicion).agregarUnidad(unidad);
        } else {
            throw new PosicionOcupadaException();
        }

    }

    private Casillero obtenerCasillero(Posicion posicion) {
        if ( ! this.misCasilleros.containsKey(posicion) ) {
            throw new CasilleroInexistenteException();
        }
        return this.misCasilleros.get(posicion);
    }

    public Unidad obtenerUnidad(Posicion posicion) {
        return obtenerCasillero(posicion).obtenerUnidad();
    }

    public void agregarChispa(Posicion posicion, Chispa chispa) {
        obtenerCasillero(posicion).agregarChispa(chispa);

    }

    public void avanzarPorCasillero(Posicion actual, Posicion fin, Transformer unidad, Integer movRest) {
        if (actual.distanciaA(fin) == 0){
            return ;
        }
        for (HashMap.Entry<Posicion, Casillero> entry : misCasilleros.entrySet()){
            Posicion posSiguiente = entry.getKey();
            
            if  (posSiguiente.contiguoAPosicion(actual)) {
                if ( (fin.distanciaA(posSiguiente) ) == (movRest-1)){
                    Casillero casilleroSiguiente = obtenerCasillero(posSiguiente);
                    this.quitarUnidadActual(actual);
                    try {
                        this.agregarUnidad(posSiguiente, unidad);
                    } catch (PosicionOcupadaException e) {
                        this.agregarUnidad(actual,unidad);
                        return ;
                    }                  
                    movRest = movRest - casilleroSiguiente.unidad.getFormaActual().disminuirEnUnMovimiento();
                    if (movRest > 0){
                        avanzarPorCasillero(posSiguiente,fin,unidad,movRest);
                    }
                    return ;
                    
                }
            }
        }
    }
}
