package fiuba.algo3.modelo.formas;

public abstract class FormaHumanoide extends Forma {
	@Override
	public boolean esHumanoide() {
		return false;
	}

	@Override
	public boolean esVehiculo() {
		return true;
	}

	@Override
	public float coeficienteAtaqueVehiculo(float coeficienteAtaqueModoVehiculo) {
		return 1;
	}
	
	@Override
	public float coeficienteMovimientoEnPantano(){
		return 0.5f;
	}
	
	public float danioRealPorEspinas(float danioPosible){
		return danioPosible;
	}
	
	public boolean esAerea(){
		return false;
	}
	
	public boolean esTerrestre(){
		return true;
	}

}
