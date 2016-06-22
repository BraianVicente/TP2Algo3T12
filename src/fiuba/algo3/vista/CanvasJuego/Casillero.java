package fiuba.algo3.vista.CanvasJuego;

import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;

public class Casillero {
	private Superficie sAerea;
	private Superficie sTerrestre;
	private Unidad uAerea;
	private Unidad uTerrestre;
	private PosicionEnElPlano pos;
	private boolean esChispa;
	private boolean esMontePerdicion;
	
	
	public Casillero(Superficie supAerea, 
			Superficie supTerrestre, 
			Posicion pos2, 
			Unidad uAerea, 
			Unidad uTerrestre,
			boolean esChispa,
			boolean esMontePerdicion) {
		this.sAerea=supAerea;
		this.sTerrestre=supTerrestre;
		this.pos =new PosicionEnElPlano(pos2.getX(),pos2.getY());
		this.uAerea=uAerea;
		this.uTerrestre=uTerrestre;
		this.esChispa = esChispa;
		this.esMontePerdicion = esMontePerdicion;
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
	
	public Posicion getPosicion() {
		return (new Posicion(pos.getX(), pos.getY()));
	}
	
	public boolean isEmpty() {
		return (getuTerrestre() == null && getuAerea() == null);
	}
	
	public Unidad getUnidad() {
		if (isEmpty()); // Do something here;
		if (getuTerrestre() != null)
			return getuTerrestre();
		else
			return getuAerea();
	}


	public Unidad getUnidad(ModoVista modoVista) {
		if(modoVista==ModoVista.SOLOTIERRA||(modoVista==ModoVista.AMBAS&&getuTerrestre()!=null)) return this.getuTerrestre();
		else return this.getuAerea();

	}


	public boolean getEsChispa() {
		return esChispa;
	}


	public boolean getMontePerdicion() {
		return esMontePerdicion;
	}
	
}
