/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero.contenedorCasilleros;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.UnidadVacia;
import fiuba.algo3.modelo.chispa.Chispa;
import fiuba.algo3.modelo.chispa.ChispaHolder;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;

/**
 *
 * @author brahvic
 */
public class Casillero {

    private Unidad unidad;
    private Chispa chispa;
    private Superficie aerea;
    private Superficie terrestre;
    
    public Casillero() {
        this.unidad = new UnidadVacia();
        this.aerea = new Nubes();
        this.terrestre = new Rocosa() ;
        this.chispa = new ChispaHolder();
    }

    public boolean isEmpty() {
        return (!(this.unidad.existe()));
    }

    public void agregarUnidad(Unidad unidad2) {
        this.unidad = unidad2;
        if( ( (Transformer)unidad).esTerrestre() ){
            this.terrestre.afectarA((Transformer) unidad2);
        } else {
            this.aerea.afectarA((Transformer) unidad2);
        }
    }

    public void quitarUnidadActual() {
        this.unidad = new UnidadVacia();
    }

    public Unidad obtenerUnidad() {
        return unidad;
    }

    public void agregarChispa() {
        this.chispa = ChispaSuprema.getInstance();
    }
    
    public boolean tieneChispa() {
    	return (this.chispa instanceof ChispaSuprema);
    }

}
