package fiuba.algo3.vista.CanvasJuego;

import javafx.animation.AnimationTimer;

public class Actualizador extends AnimationTimer {
	private Actualizable aActualizar;
	public Actualizador(Actualizable aActualizar){
		this.aActualizar = aActualizar;
	}
	@Override
	public void handle(long arg0) {
		aActualizar.actualizar();
		
	}

}
