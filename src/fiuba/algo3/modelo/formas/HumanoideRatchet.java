package fiuba.algo3.modelo.formas;

public class HumanoideRatchet extends FormaAerea {

    @Override
    public Forma getAlternativa() {
        return new AvionF22();
    }

    @Override
    public int getDistanciaAtaque() {
        return 5;
    }

    @Override
    public int getPuntosAtaque() {
        return 5;
    }

    @Override
    public int getDistanciaMovimiento() {
        return 1;
    }
    
    @Override
	public String nombreImagen() {
		return "fiuba/algo3/vista/imagenes/transformers/RatchetHumanoide.png";
	}


}
