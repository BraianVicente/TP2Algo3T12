package fiuba.algo3.modelo.modificadores;

public abstract class ModificadorPermanente extends Modificador {
	@Override
	boolean haceEfecto(){
		return true;
	}
	@Override
	public void pasaTurno(){
		//nada
	}

}
