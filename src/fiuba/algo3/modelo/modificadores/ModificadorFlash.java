package fiuba.algo3.modelo.modificadores;

public class ModificadorFlash extends ModificadorTransitorio {

	@Override
	protected int turnosDuracion() {
		return 3;
	}
	@Override
	public float coeficienteVelocidad(){
		return 3;
	}
	@Override
	public Object clone() {
		return new ModificadorFlash();
	}
	
	@Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/efectos/bonusFlash.png";
	}

}
