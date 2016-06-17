package fiuba.algo3.vista.CanvasJuego;

public class ImagenInexistenteExcption extends Exception {

	private String nombre;
	public ImagenInexistenteExcption(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		return "No existe la imagen " + nombre;
	}

}
