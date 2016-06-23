package fiuba.algo3.vista.juego;

import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CallbackCasillero;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;
import fiuba.algo3.vista.CanvasJuego.Casillero;

public class SeleccionController implements CallbackCasillero {
	Juego juego;
	CanvasJuego cj;

	public SeleccionController(Juego juego, CanvasJuego cj) {
		this.juego=juego;
		this.cj=cj;
	}

	@Override
	public void execute(Casillero cas) {
		cj.seleccionadorEn(cas.getPos());
		
		Unidad seleccionada = cas.getUnidad(cj.getModoVista());
		if(cas.isEmpty() || !seleccionada.es(juego.jugadorEnTurno().getEquipo())){
			cj.esconderSeleccionador();
			cj.setHalos(null);
		}else{
			cj.seleccionadorEn(cas.getPos());
			cj.setHalos(seleccionada);
		}
		
		
	}

}
