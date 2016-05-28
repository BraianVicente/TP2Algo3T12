package fiuba.algo3.modelo.equipos;

public class Autobots extends Equipo {

	@Override
	public boolean mismoEquipo(Equipo otro) {
		return otro.mismoEquipo(this);
	}

	@Override
	public boolean mismoEquipo(Autobots otro) {
		return true;
	}

	@Override
	public boolean mismoEquipo(Decepticons otro) {
		return false;
	}

	@Override
	public boolean mismoEquipo(Ninguno otro) {
		return false;
	}

}