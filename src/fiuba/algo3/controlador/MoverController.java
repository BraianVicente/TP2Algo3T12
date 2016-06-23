package fiuba.algo3.controlador;

import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.Casillero;

public class MoverController implements CallbackCasillero {

	private Casillero actual, anterior;
	private Juego juego;
	private CanvasJuego cj;
	
	public MoverController(Juego juego, CanvasJuego cj) {
		this.juego = juego;
		actual = null;
		anterior = null;
		this.cj=cj;
	}
	
	@Override
	public void execute(Casillero cas) {
		try{
			if (actual != null && cas.isEmpty()) {
			anterior = actual;
			actual = cas;

			Unidad unit = anterior.getUnidad(cj.getModoVista());
			Posicion posActual = juego.obtenerPosicion(unit);
			Posicion posNueva = new Posicion(actual.getPos().getX(), actual.getPos().getY(), unit.getPlanoPerteneciente());
			
			juego.moverUnidad(posActual, posNueva);
			
			//actualizo casilleros
			this.actualizarInformacion();
		} else {
			anterior = actual;
			actual = cas;
		}
		
		}catch(RuntimeException e){
			
		}
		if(actual!=null&&anterior!=null)this.actualizarInformacion();
	}

	private void actualizarInformacion() {
		anterior=juego.construirCasillero(new Posicion(anterior.getPos(),Plano.TERRESTRE));
		actual=juego.construirCasillero(new Posicion(actual.getPos(),Plano.TERRESTRE));
	}

	

}
