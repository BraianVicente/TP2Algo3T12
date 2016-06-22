package fiuba.algo3.controlador;

import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.Casillero;
import fiuba.algo3.vista.CanvasJuego.ModoVista;

public class AtacarController implements CallbackCasillero {

	private Unidad atacante;
	private Juego juego;
	private CanvasJuego cj;
	
	public AtacarController(Juego juego, CanvasJuego cj) {
		this.juego = juego;
		atacante = null;
		this.cj=cj;
	}
	
	@Override
	public void execute(Casillero cas) {
		if(atacante!=null&&atacante.getVida()>0)  {
		Unidad objetivo=obtenerUnidadObjetivo(cas);
		if(objetivo!=null&&
				juego.puedeAtacar(atacante,juego.obtenerPosicion(objetivo))
				&&atacante.es(juego.jugadorEnTurno().getEquipo()))
			juego.atacarUnidad(juego.obtenerPosicion(atacante), juego.obtenerPosicion(objetivo));
		else atacante=cas.getUnidad();
		}else{
		atacante=cas.getUnidad();
		}
		cj.actualizar();
	}
	private Unidad obtenerUnidadObjetivo(Casillero cas) {
		if((cj.getModoVista()==ModoVista.AMBAS&&cas.getuTerrestre()!=null)
				||cj.getModoVista()==ModoVista.SOLOTIERRA )return cas.getuTerrestre();
		else return cas.getuAerea();
	}
}
