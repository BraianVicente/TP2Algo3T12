package fiuba.algo3.vista.CanvasJuego;

public class ImagenInexistenteException extends RuntimeException {

	private String nombre;
	public ImagenInexistenteException(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		return "No existe la imagen " + nombre;
	}

}
