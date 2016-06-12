package fiuba.algo3.vista.CanvasJuego;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;

public class Casillero {
	private Superficie aerea;
	private Superficie terrestre;
	private Posicion pos;
	private Unidad u;
	
	public Casillero(Superficie aerea, Superficie terrestre, Posicion pos, Unidad u){
		this.aerea = aerea;
		this.terrestre = terrestre;
		this.pos = pos;
		this.u=u;
	}
	
	public Superficie getAerea(){
		return aerea;
	}
	
	public Superficie getTerrestre(){
		return terrestre;
	}
	
	public Posicion getPosicion(){
		return pos;
	}
	
	public Unidad getUnidad(){
		return u;
	}
	
}
