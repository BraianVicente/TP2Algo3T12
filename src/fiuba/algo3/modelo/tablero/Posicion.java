package fiuba.algo3.modelo.tablero;

import java.util.Objects;

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
        if (!(otra instanceof PosicionEnElPlano)) {
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
}
