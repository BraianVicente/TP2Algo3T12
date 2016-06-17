package fiuba.algo3.modelo.formas;

public class NaveCybertroniana extends FormaAerea {

    @Override
    public Forma getAlternativa() {
        return new HumanoideMegatron();
    }

    @Override
    public int getDistanciaAtaque() {
        return 2;
    }

    @Override
    public int getPuntosAtaque() {
        return 55;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 8;
    }
    
    @Override
	public String nombreImagen() {
		return "fiuba/algo3/vista/imagenes/transformers/MegatronVehiculo.png";
	}


}
