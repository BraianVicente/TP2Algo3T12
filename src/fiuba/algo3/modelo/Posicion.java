package fiuba.algo3.modelo;
import java.lang.IllegalArgumentException;
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
	private int x;
	private int y;
	public Posicion(int x, int y){
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
		return (x==p.x) && (y==p.y);
	}
	@Override
	public Posicion clone(){
		return new Posicion(x,y);
	}
	
	public float distanciaA(Posicion otra){
		if(otra==null) throw new IllegalArgumentException();
		return Math.max(Math.abs(otra.x-x),Math.abs(otra.y-y));
	}
	
	@Override
	public String toString(){
		return "(x="+x+", y=" + y +")";
	}
}
