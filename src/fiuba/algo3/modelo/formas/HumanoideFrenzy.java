package fiuba.algo3.modelo.formas;

public class HumanoideFrenzy extends FormaHumanoide {

    @Override
    public Forma getAlternativa() {
        return new Duster();
    }

    @Override
    public int getDistanciaAtaque() {
        return 5;
    }

    @Override
    public int getPuntosAtaque() {
        return 10;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 2;
    }
    
    @Override
	public String nombreImagen() {
		return "fiuba/algo3/vista/imagenes/transformers/FrenzyHumanoide.png";
	}

    @Override
    public String nombre() {
        return "Frenzy";
    }


}
