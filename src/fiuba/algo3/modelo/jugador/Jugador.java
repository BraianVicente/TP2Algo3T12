/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.jugador;

import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.unidades.Bumblebee;
import fiuba.algo3.modelo.unidades.Unidad;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author brahvic
 */
public class Jugador {
    
    private String nombre ;
    private Equipo equipo ;
    private Tablero tablero;
    

    public Jugador(String nombre, Equipo equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public Jugador(String nombre, Equipo equipo, Tablero tab) {
        this(nombre,equipo);
        this.tablero = tab ;
    }
    
    public void setTablero(Tablero tab){
        this.tablero = tab;
    }
    
    public Equipo getEquipo(){
        return this.equipo;
    }
    
    public String getNombre(){
        return this.nombre ;
    }
    
    public boolean perteneceEquipo(Unidad unidad){
        if (unidad.es(this.equipo)){
            return true;
        }
        throw new EquipoInvalidoException();
        
    }
    
    public void combinarUnidades(){
        this.tablero.combinarUnidadesEquipo(this.equipo);
    }
    
    public void pasarTurno(){
        this.tablero.pasarTurnoEquipo(this.equipo);
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.equipo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jugador other = (Jugador) obj;
        if (!this.nombre.equals(other.nombre)) {
            return false;
        }
        if (!this.equipo.equals(other.equipo)) {
            return false;
        }
        return true;
        
    }

    public boolean derrotado() {
        return !tablero.existenUnidadeDeEquipo(this.equipo) ;
    }

    public void agregarUnidad(Posicion posicion, Unidad unidad) {
        if (this.perteneceEquipo(unidad)){
            this.tablero.agregarUnidad(posicion, unidad);
        }
        
    }
    
}