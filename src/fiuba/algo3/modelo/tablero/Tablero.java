/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.ContenedorCasilleros;
import fiuba.algo3.modelo.tablero.contenedorUnidades.ContenedorUnidades;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;

/**
 *
 * @author brahvic
 */
public class Tablero {
    private ContenedorCasilleros contenedorCasilleros;
	private ContenedorUnidades contenedorUnidades;

    public Tablero() {
        this.contenedorCasilleros = new ContenedorCasilleros();
        for (Integer i = 0; i < 100; i++) {
            this.contenedorCasilleros.agregarCasilleroVacio(new Posicion(i % 10, i / 10,Plano.AEREO));
            this.contenedorCasilleros.agregarCasilleroVacio(new Posicion(i % 10, i / 10,Plano.TERRESTRE));
        }
        //Death.getInstance().asignarTablero(this);
    }

    public boolean isEmpty(Posicion posicion) {
        return this.contenedorCasilleros.isEmpty(posicion);
    }

    public void quitarUnidadActual(Posicion posicion) {
        this.contenedorCasilleros.quitarUnidadActual(posicion);
    }

    public void agregarUnidad(Posicion posicion, Unidad unidad) {
        this.contenedorCasilleros.agregarUnidad(posicion, unidad);
    }

    public void mover(Posicion posicionInicio, PosicionEnElPlano posicionFin) {
        Unidad unidad = contenedorCasilleros.obtenerUnidad(posicionInicio);
    	try {
           if (!unidad.puedeMoverse(posicionInicio, posicionFin)) {
                throw new MovimientoInvalidoException();
            }
           this.avanzarPorCasillero(posicionFin, posicionFin, (Transformer) unidad);
            quitarUnidadActual(posicionInicio);
            agregarUnidad(posicionFin, unidad);
    } catch (PosicionOcupadaException e) {
            agregarUnidad(posicionInicio, unidad);
            throw new MovimientoInvalidoException();
        }
    }

    public Unidad obtenerUnidad(Posicion posicion) {
        return contenedorCasilleros.obtenerUnidad(posicion);
    }

    public void agregarChispa(Posicion posicion) {
        contenedorCasilleros.agregarChispa(posicion);
    }

	public boolean tieneChispa(PosicionEnElPlano posicion) {
		return contenedorCasilleros.tieneChispa(posicion);
	}

	public void murio(Unidad u) throws NoSeEncuentraUnidadException {
		Posicion pos = contenedorCasilleros.obtenerPosicion(u);
		if (u.tieneChispa()){
			contenedorCasilleros.agregarChispa(pos);
		}
		contenedorCasilleros.quitarUnidadActual(pos);
	}
	
	public void transformar(Transformer transformer){
		try{
			transformer.transformar();
			contenedorUnidades.cambiarPlano(transformer,transformer.getPlanoPerteneciente());
		}catch(MovimientoInvalidoException e){
			throw new TransformacionInvalida();
		}
	}
}
