package fiuba.algo3.controlador;


import fiuba.algo3.modelo.Juego;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.vista.CanvasJuego.CanvasJuego;

public class GameController {
	
	private Juego juego;
	private ClickedUnitManager manager;
	private CanvasJuego cj;
	
	public GameController(Juego juego, ClickedUnitManager manager, CanvasJuego cj) {
		this.juego = juego;
		this.manager = manager;
		this.cj = cj;
	}
	
	public void transformarUnidad() {
		try{
			Unidad unit = manager.getUnidad(cj.getModoVista()); // Fallback: returns UnidadAere
			Posicion pos = juego.obtenerPosicion(unit);
			if (unit.sePuedeTransformar())	juego.transformarUnidad(pos);
			this.actualizarInformacion();
		}catch(RuntimeException e){
			
		}
	}
	
	private void actualizarInformacion() {
		cj.actualizar();
		manager.actualizarCasillero(juego.construirCasillero(manager.getPosicion()));
		
	}

	public void combinarUnidades() {
		juego.combinarUnidades();
		this.actualizarInformacion();
	}
	
}
