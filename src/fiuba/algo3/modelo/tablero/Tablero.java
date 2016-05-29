/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.ContenedorCasilleros;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;


/**
 *
 * @author brahvic
 */
public class Tablero {

    private ContenedorCasilleros tablero ;

    public Tablero(){
    this.tablero = new ContenedorCasilleros();
        for (Integer i = 0; i < 100;i++){
            this.tablero.agregarCasilleroVacio(new Posicion(i%10,i/10));
        }
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
     
     public void mover(Posicion posicionInicio, Posicion posicionFin) {
 		Unidad unidad=tablero.obtenerUnidad(posicionInicio);
    	 if(!unidad.puedeMoverse(posicionInicio,posicionFin)) throw new MovimientoInvalidoException();   
    	 quitarUnidadActual(posicionInicio);
    	 agregarUnidad(posicionFin, unidad);
 		
     }

	public Unidad obtenerUnidad(Posicion posicion) {
		return tablero.obtenerUnidad(posicion);
	}

	public void agregarChispa(Posicion posicion,Chispa chispa) {
		tablero.agregarChispa(posicion,chispa);
		
	}
}
