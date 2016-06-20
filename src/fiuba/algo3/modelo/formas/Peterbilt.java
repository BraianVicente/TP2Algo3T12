package fiuba.algo3.modelo.formas;

public class Peterbilt extends FormaTerrestre {

    @Override
    public Forma getAlternativa() {
        return new HumanoideOptimusprime();
    }

    @Override
    public int getDistanciaAtaque() {
        return 4;
    }

    @Override
    public int getPuntosAtaque() {
        return 15;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 5;
    }

    @Override
	public String nombreImagen() {
		return "/fiuba/algo3/vista/imagenes/transformers/OptimusVehiculo.png";
	}

    @Override
    public String nombre() {
        return "Peterbilt";
    }
}
