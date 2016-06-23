package fiuba.algo3.modelo.modificadores;

public class ModificadorDoble extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 3;
	}
	
	public float coeficienteAtaque(){
		return 2;
	}

	@Override
	public Object clone() {
		return new ModificadorDoble();
	}
	
	@Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/efectos/bonusDoble.png";
	}

}
