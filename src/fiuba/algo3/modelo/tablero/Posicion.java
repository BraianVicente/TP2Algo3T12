package fiuba.algo3.modelo.tablero;
import java.lang.IllegalArgumentException;
import java.util.Objects;
/**
 * La clase Posicion representa el lugar donde est� un casillero, 
 * en el modelo final una posicion se corresponde univocamente con un casillero.
 * 
 *  x aumenta de izquierda a derecha.
 *  y aumenta de arriba hacia abajo. (al reves que el eje cartesiano!)
 *  
 *  As�, se parece m�s a la posici�n en una matriz.
 *  
 *  Es mejor tener el eje y invertido para que sea m�s facil programar 
 *  los gr�ficos (en gr�ficos el eje y est� cambiado)
 *  
 * @author Jos� Sb
 *
 */
public class Posicion implements Cloneable{
    private Integer x;
    private Integer y;
	
    public Posicion(Integer x, Integer y){
        this.x=x;
        this.y=y;
    }
    public Posicion(){
        this.x = 0;
        this.y = 0;
    }
	
    @Override
    public boolean equals(Object otra){
        if(otra == null) return false;
        if(otra == this) return true;
        if(!(otra instanceof Posicion)) return false;
        Posicion p = (Posicion) otra;
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
    public Posicion clone(){
        return new Posicion(x,y);
    }
	
    public Integer distanciaA(Posicion otra){
        if(otra==null) throw new IllegalArgumentException();
        return (Integer)Math.max(Math.abs(otra.x-x),Math.abs(otra.y-y));
    }
	
    @Override
    public String toString(){
        return "(x="+x+", y=" + y +")";
    }
}
