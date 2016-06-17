package fiuba.algo3.vista.CanvasJuego;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;

public class Casillero {
	private Superficie sAerea;
	private Superficie sTerrestre;
	private Unidad uAerea;
	private Unidad uTerrestre;
	private PosicionEnElPlano pos;
	
	
	public Casillero(Superficie supAerea, Superficie supTerrestre, Posicion pos2, Unidad uAerea, Unidad uTerrestre) {
		this.sAerea=supAerea;
		this.sTerrestre=supTerrestre;
		this.pos =new PosicionEnElPlano(pos2.getX(),pos2.getY());
		this.uAerea=uAerea;
		this.uTerrestre=uTerrestre;
	}


	public Superficie getsAerea() {
		return sAerea;
	}


	public Superficie getsTerrestre() {
		return sTerrestre;
	}


	public Unidad getuAerea() {
		return uAerea;
	}


	public Unidad getuTerrestre() {
		return uTerrestre;
	}


	public PosicionEnElPlano getPos() {
		return pos;
	}
	
}
