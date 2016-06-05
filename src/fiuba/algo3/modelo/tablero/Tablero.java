/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.ContenedorCasilleros;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.Menasor;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Superion;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

/**
 *
 * @author brahvic
 */
public class Tablero {
    private static final Integer MAX_DISTANCE = null; //definir distancia maxima entre units para hacer la combinacion
	private ContenedorCasilleros tablero;

    public Tablero() {
        this.tablero = new ContenedorCasilleros();
        for (Integer i = 0; i < 100; i++) {
            this.tablero.agregarCasilleroVacio(new Posicion(i % 10, i / 10));
        }
        //Death.getInstance().asignarTablero(this);
    }

    public boolean isEmpty(Posicion posicion) {
        return this.tablero.isEmpty(posicion);
    }

    public void quitarUnidadActual(Posicion posicion) {
        this.tablero.quitarUnidadActual(posicion);
    }

    public void agregarUnidad(Posicion posicion, Unidad unidad) {
        this.tablero.agregarUnidad(posicion, unidad);
    }

    public void avanzarPorCasillero(Posicion ini, Posicion fin, Transformer unidad){
        this.tablero.avanzarPorCasillero(fin, fin, unidad, unidad.getDistanciaMovimiento());
    }
    public void mover(Posicion posicionInicio, Posicion posicionFin) {
// cambiar metodo para que verifique la posibilidad de abanzar entre cada casillero
// t lo haga de manera recursiva, disminuyendo la distancia desplazamiento.
// seria un public void mover(posI, posF,movRest) que verifique la penalizacion
// en cada una de las posiciones ocupadas.
        Unidad unidad = tablero.obtenerUnidad(posicionInicio);
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
        return tablero.obtenerUnidad(posicion);
    }

    public void agregarChispa(Posicion posicion) {
        tablero.agregarChispa(posicion);
    }

	public boolean tieneChispa(Posicion posicion) {
		return tablero.tieneChispa(posicion);
	}

	public void murio(Unidad u) throws NoSeEncuentraUnidadException {
		Posicion pos = tablero.obtenerPosicion(u);
		if (u.tieneChispa()){
			tablero.agregarChispa(pos);
		}
		tablero.quitarUnidadActual(pos);
	}
	
	public void combinar(Posicion a, Posicion b, Posicion c) {
		UnidadConVida unita, unitb, unitc;
		
		unita = (UnidadConVida) obtenerUnidad(a);
		unitb = (UnidadConVida) obtenerUnidad(b);
		unitc = (UnidadConVida) obtenerUnidad(c);
		
		// check the distance between units
		if ( (a.distanciaA(b) > MAX_DISTANCE) || (b.distanciaA(c) > MAX_DISTANCE) || (c.distanciaA(a) > MAX_DISTANCE) ) {
			// throw an exception or something
		}
		
		// check they're in the same team
		if (!unita.equipo().mismoEquipo(unitb.equipo(), unitc.equipo())) {
			// throw exception or something
		}
		
		UnidadConVida comb = (UnidadConVida) unita.equipo().getCombination();
		
		if ( (unita.tieneChispa()) || (unitb.tieneChispa()) || (unitc.tieneChispa())) {
			comb.darChispa();
		}
		
		quitarUnidadActual(a);
		quitarUnidadActual(b);
		quitarUnidadActual(c);
		
		agregarUnidad(a, comb); // or get avg distance? 
		
	}
}
