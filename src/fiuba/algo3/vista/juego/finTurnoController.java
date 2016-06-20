package fiuba.algo3.vista.juego;

import fiuba.algo3.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class finTurnoController implements EventHandler<ActionEvent> {
	
	private Juego juego;
	private GameboardController controller;
	
	public finTurnoController(Juego j, GameboardController c) {
		juego = j;
		controller = c;
	}
	
	@Override
	public void handle(ActionEvent ae) {
		juego.avanzarTurno();
		controller.setJugandoImage(juego.jugadorEnTurno().getEquipo());
	}

}
