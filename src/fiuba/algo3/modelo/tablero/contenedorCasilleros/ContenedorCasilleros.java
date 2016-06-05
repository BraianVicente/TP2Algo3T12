/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.contenedorCasilleros;

import java.util.HashMap;
import java.util.Map;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

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
        this.misCasilleros.put(posicion, new Casillero());
    }
    
    public boolean isEmpty(Posicion posicion) {
        return obtenerCasillero(posicion).isEmpty();
    }

    public void quitarUnidadActual(Posicion posicion) {
        obtenerCasillero(posicion).quitarUnidadActual();
        Casillero cas = obtenerCasillero(posicion);
    }

    public void agregarUnidad(Posicion posicion, Unidad unidad) {
        if (this.isEmpty(posicion)){
            obtenerCasillero(posicion).agregarUnidad(unidad);
        } else {
            throw new PosicionOcupadaException(posicion);
        }

    }
    

    private Casillero obtenerCasillero(Posicion posicion) {
        if ( ! this.misCasilleros.containsKey(posicion) ) {
            throw new CasilleroInexistenteException();
        }
        Casillero casillero = this.misCasilleros.get(posicion);
        return casillero;
    }

    public Unidad obtenerUnidad(Posicion posicion) {
        return obtenerCasillero(posicion).obtenerUnidad();
    }

    public void agregarChispa(Posicion posicion) {
        obtenerCasillero(posicion).agregarChispa();

    }

    public void avanzarPorCasillero(Posicion actual, Posicion fin, Transformer unidad, Integer movRest) {
        if (actual.distanciaA(fin) == 0){
            return ;
        }
        for (Map.Entry<Posicion, Casillero> entry : misCasilleros.entrySet()){
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
                    movRest = movRest - casilleroSiguiente.obtenerUnidad().getFormaActual().disminuirEnUnMovimiento();
                    if (movRest > 0){
                        avanzarPorCasillero(posSiguiente,fin,unidad,movRest);
                    }
                    return ;
                    
                }
            }
        }
    }

	public boolean tieneChispa(PosicionEnElPlano posicion) {
		return misCasilleros.get(posicion).tieneChispa();
	}
	
	public Posicion obtenerPosicion(Unidad u) throws NoSeEncuentraUnidadException{
		Posicion buscada = null;
		for(Map.Entry<Posicion, Casillero> entrada : misCasilleros.entrySet()){
			if(entrada.getValue().obtenerUnidad() == u){
				buscada = entrada.getKey();
			}
		}
		if(buscada==null){
			throw new NoSeEncuentraUnidadException(u);
		}
		return buscada;
	}

	public boolean puedeAtacar(UnidadConVida atacante, UnidadConVida atacado) {
		// TODO Auto-generated method stub
		return false;
	}
}
