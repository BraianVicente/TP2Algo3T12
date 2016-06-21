package fiuba.algo3.vista.juego;

import fiuba.algo3.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class FinTurnoController implements EventHandler<ActionEvent> {
	
	private Juego juego;
	private GameboardController controller;
	
	public FinTurnoController(Juego j, GameboardController c) {
		juego = j;
		controller = c;
	}
	
	@Override
	public void handle(ActionEvent ae) {
		if(juego.termino()) controller.terminoJuego();
		juego.avanzarTurno();
		controller.setJugandoImage(juego.jugadorEnTurno().getEquipo());
	}

}
