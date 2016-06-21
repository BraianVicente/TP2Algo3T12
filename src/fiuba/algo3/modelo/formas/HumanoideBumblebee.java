package fiuba.algo3.modelo.formas;

public class HumanoideBumblebee extends FormaHumanoide {

    @Override
    public Forma getAlternativa() {
        return new Camaro();
    }

    @Override
    public int getDistanciaAtaque() {
        return 1;
    }

    @Override
    public int getPuntosAtaque() {
        return 40;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 2;
    }

    @Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/transformers/BumblebeeHumanoide.png";
	}

    @Override
    public String nombre() {
        return "Bumblebee" ;
    }

}
