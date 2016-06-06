package fiuba.algo3.modelo.tablero;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import fiuba.algo3.modelo.tablero.Posicion.Plano;

public class Posicion {
	public enum Plano{
		AEREO, TERRESTRE
	}
	private Plano plano;
	private PosicionEnElPlano posicionEnElPlano;
	
	public Posicion(Integer x, Integer y, Plano plano) {
        posicionEnElPlano=new PosicionEnElPlano(x,y);
        this.plano=plano;
        
    }public Posicion(Integer x, Integer y) {
        posicionEnElPlano=new PosicionEnElPlano(x,y);
        plano=Plano.TERRESTRE;
    }
	

    public Posicion() {
    	posicionEnElPlano=new PosicionEnElPlano();
        plano=Plano.TERRESTRE;
    }

    public Posicion(PosicionEnElPlano posicionEnElPlano, Plano plano) {
		this.posicionEnElPlano=posicionEnElPlano.clone();
    	this.plano=plano;
	}
	@Override
    public boolean equals(Object otra) {
        if (otra == null) {
            return false;
        }
        if (otra == this) {
            return true;
        }
        if (!(otra instanceof Posicion)) {
            return false;
        }
        Posicion p = (Posicion) otra;
        return ((plano==p.plano) && (posicionEnElPlano.equals(p.posicionEnElPlano)));
    }

    @Override
    public int hashCode() {
        int hash;
        hash =  Objects.hashCode(posicionEnElPlano);
        hash =  hash+Objects.hashCode(plano);
        return hash;
    }

    @Override
    public Posicion clone() {
        return new Posicion(posicionEnElPlano, plano);
    }

    public Integer distanciaA(Posicion otra) {
        if (otra == null) {
            throw new IllegalArgumentException();
        }
        return (Integer) posicionEnElPlano.distanciaA(otra.posicionEnElPlano);
    }

    @Override
    public String toString() {
        return posicionEnElPlano.toString()+"Plano: "+plano;
    }
    
    public Integer getX(){
    	return posicionEnElPlano.getX();
    }
    public Integer getY(){
    	return this.posicionEnElPlano.getY();
    }

    public boolean contiguoAPosicion(Posicion otra) {
        return this.distanciaA(otra) == 1 && otra.plano==this.plano ;
        
    }
	public Posicion nuevaPosicionConDistintoPlano(Plano plano) {
		return new Posicion(this.posicionEnElPlano,plano);
	}
	public Plano getPlano() {
		return plano;
	}
	public Posicion obtenerMismaPosicionDesplazada(int desplazamientoX, int desplazamientoY) {
		return new Posicion(getX()+desplazamientoX,getY()+desplazamientoY,plano);
	}
	public int distanciaEnYA(Posicion posicionFin) {
		return posicionFin.getY()-this.getY();
	}
	public int distanciaEnXA(Posicion posicionFin) {
		return posicionFin.getX()-this.getX();
	}
	
	public LinkedList<Posicion> posicionesQueTocaLaRectaQueVaA(Posicion p2) {
		LinkedList<Posicion> posiciones=new LinkedList<Posicion>();
		Plano plano=p2.getPlano();
		float diferenciaEnX=this.distanciaEnXA(p2);
		float diferenciaEnY=this.distanciaEnYA(p2);
		posiciones.add(this);
		posiciones.add(p2);
		float pendiente=diferenciaEnY/diferenciaEnX;
		for(float i=this.getX()+Math.signum(diferenciaEnX)*0.5f;Math.signum(diferenciaEnX)*i<Math.signum(diferenciaEnX)*p2.getX();i=i+1*Math.signum(diferenciaEnX)) {
			float yDeInterseccion=pendiente*(i-this.getX())+this.getY();
			posiciones.addAll(posicionesQueSonTocadasPorUnSegmentoDeRecta(i,yDeInterseccion,plano, pendiente));
		}
		pendiente=1/pendiente;
		for(float i=this.getY()+Math.signum(diferenciaEnY)*0.5f;Math.signum(diferenciaEnY)*i<Math.signum(diferenciaEnY)*p2.getY();i=i+1*Math.signum(diferenciaEnY)) {
			float xDeInterseccion=pendiente*(i-this.getY())+this.getX();
			posiciones.addAll(posicionesQueSonTocadasPorUnSegmentoDeRecta(xDeInterseccion,i,plano, pendiente));
			
		}
		removerRepetidos(posiciones);
		return posiciones;
	}
	private void removerRepetidos(LinkedList<Posicion> posiciones) {
		HashSet<Posicion> posicionesAuxiliar=new HashSet<Posicion>(posiciones);
		posiciones.clear();
		posiciones.addAll(posicionesAuxiliar);
		
	}
	private List<Posicion> posicionesQueSonTocadasPorUnSegmentoDeRecta(float x, float y, Plano plano, float pendiente) {
		List<Posicion> posiciones =new ArrayList<Posicion>();
		if(Math.abs(y-Math.floor(y)-0.5f)<0.001f&&Math.abs(x-Math.floor(x)-0.5f)<0.001f) {
			//caso interseccion de 4 casilleros
			if(pendiente>0){
				posiciones.add(new Posicion((int)Math.floor(x), (int)Math.floor(y),plano));
				posiciones.add(new Posicion((int)Math.ceil(x), (int)Math.ceil(y),plano));
			}else{
				posiciones.add(new Posicion((int)Math.ceil(x), (int)Math.floor(y),plano));
				posiciones.add(new Posicion((int)Math.floor(x), (int)Math.ceil(y),plano));
			}
		
		}
		
		else{
			//caso intrseccion de 2 casilleros, verifico si fue en x o en y
			if(Math.abs(x-Math.floor(x)-0.5f)<0.001f){
				posiciones.add(new Posicion((int)Math.floor(x), (int)Math.round(y),plano));
				posiciones.add(new Posicion((int)Math.ceil(x), (int)Math.round(y),plano));
		
			}else{
				posiciones.add(new Posicion((int)Math.round(x), (int)Math.floor(y),plano));
				posiciones.add(new Posicion((int)Math.round(x), (int)Math.ceil(y),plano));
			}
		}
		return posiciones;	
	}
}
