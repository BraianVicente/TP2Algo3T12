package fiuba.algo3.modelo.modificadores;

public abstract class ModificadorTransitorio extends Modificador {
	
	protected abstract int turnosDuracion();
	
	private int turnos;
	
	public ModificadorTransitorio(){
		turnos = 0;
	}
	
	@Override
	public void pasaTurno(){
		turnos +=1;
	}
	
	//visibilidad de paquete
	@Override
	boolean haceEfecto(){
		return turnos<turnosDuracion();
	}

}
