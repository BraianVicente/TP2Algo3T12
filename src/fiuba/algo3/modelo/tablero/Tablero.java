/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.tablero.contenedorCasilleros.ContenedorCasilleros;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;


/**
 *
 * @author brahvic
 */
public class Tablero {

    private ContenedorCasilleros tablero ;

    public Tablero(){
    this.tablero = new ContenedorCasilleros();
        for (Integer i = 0; i < 25;i++){
            this.tablero.agregarCasilleroVacio(new Posicion(i%5,i/5));
        }
    }
    
    public boolean isEmpty(Posicion posicion) {
        return this.tablero.isEmpty(posicion);
    }
    
    public void quitarUnidadActual(Posicion posicion) {
        this.tablero.quitarUnidadActual(posicion);
         
     }
     public void agregarUnidadConVida(Posicion posicion, UnidadConVida unidad) {
    	 this.tablero.agregarUnidadConVida(posicion, unidad);    		 
     }
}
