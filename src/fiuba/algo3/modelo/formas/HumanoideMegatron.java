package fiuba.algo3.modelo.formas;

public class HumanoideMegatron extends FormaHumanoide {

    @Override
    public Forma getAlternativa() {
        return new NaveCybertroniana();
    }

    @Override
    public int getDistanciaAtaque() {
        return 3;
    }

    @Override
    public int getPuntosAtaque() {
        return 10;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 1;
    }
    
    @Override
	public String nombreImagen() {
		return "fiuba/algo3/vista/imagenes/transformers/MegatronHumanoide.png";
	}


}
