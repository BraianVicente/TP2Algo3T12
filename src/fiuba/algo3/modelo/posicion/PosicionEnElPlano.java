package fiuba.algo3.modelo.posicion;

import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.tablero.*;
import java.util.Objects;

/**
 * La clase Posicion representa el lugar donde est� un casillero, en el modelo
 * final una posicion se corresponde univocamente con un casillero.
 *
 * x aumenta de izquierda a derecha. y aumenta de arriba hacia abajo. (al reves
 * que el eje cartesiano!)
 *
 * As�, se parece m�s a la posici�n en una matriz.
 *
 * Es mejor tener el eje y invertido para que sea m�s facil programar los
 * gr�ficos (en gr�ficos el eje y est� cambiado)
 *
 * @author Jos� Sb
 *
 */
public class PosicionEnElPlano implements Cloneable {
	
	
    private Integer x;
    private Integer y;

    public PosicionEnElPlano(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public Integer getX(){
    	return x;
    }
    public Integer getY(){
    	return y;
    }
    public PosicionEnElPlano() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public boolean equals(Object otra) {
        if (otra == null) {
            return false;
        }
        if (otra == this) {
            return true;
        }
        if (!(otra instanceof PosicionEnElPlano)) {
            return false;
        }
        PosicionEnElPlano p = (PosicionEnElPlano) otra;
        return ((x.equals(p.x)) && (y.equals(p.y)));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.x);
        hash = 83 * hash + Objects.hashCode(this.y);
        return hash;
    }

    @Override
    public PosicionEnElPlano clone() {
        return new PosicionEnElPlano(x, y);
    }

    public Integer distanciaA(PosicionEnElPlano otra) {
        if (otra == null) {
            throw new IllegalArgumentException();
        }
        return (Integer) Math.max(Math.abs(otra.x - x), Math.abs(otra.y - y));
    }

    @Override
    public String toString() {
        return "(x=" + x + ", y=" + y + ")";
    }

    public boolean contiguoAPosicion(PosicionEnElPlano actual) {
        return this.distanciaA(actual) == 1 ;
    }
	public Posicion crearPosicion(Plano plano) {
		return  new Posicion(x,y,plano);
	}
}
