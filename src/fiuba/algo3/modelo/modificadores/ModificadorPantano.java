package fiuba.algo3.modelo.modificadores;

import fiuba.algo3.modelo.formas.FormaHumanoide;

public class ModificadorPantano extends ModificadorPermanente{
	@Override
	public float coeficienteVelocidad(){
		return 0.5f;
	}
	
	@Override
	public float coeficienteVelocidadPorForma(FormaHumanoide humanoide){
		return 0;
	}
	

	@Override
	public Object clone() {
		return new ModificadorPantano();
	}
	@Override
	public boolean esPantano() {
		return true;
	}
	
	}

