package fiuba.algo3.vista.CanvasJuego;

import java.util.TimerTask;

public class Actualizador extends TimerTask {
	private Actualizable aActualizar;
	public Actualizador(Actualizable aActualizar){
		this.aActualizar = aActualizar;
	}
	@Override
	public void run() {
		aActualizar.actualizar();
	}

}
