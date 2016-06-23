package fiuba.algo3.modelo.formas;

public class HumanoideMenasor extends FormaHumanoide {

	@Override
	public Forma getAlternativa() {
		return this;
	}

	@Override
	public int getDistanciaAtaque() {
		return 2;
	}

	@Override
	public int getPuntosAtaque() {
		return 115;
	}

	@Override
	public int getDistanciaMovimiento() {
		return 2;
	}
	
	@Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/transformers/Menasor.png";
	}

    @Override
    public String nombre() {
        return "Menasor";
    }


}
