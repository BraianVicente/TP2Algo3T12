package fiuba.algo3.vista.CanvasJuego;
import java.util.HashMap;

import javafx.scene.image.Image;
public class CacheImagenes {
	private HashMap<String,Image> imagenes;
	
	public CacheImagenes(){
		String[] nombres = {
				"/fiuba/algo3/vista/imagenes/cielo/nebulosa.png",
				"/fiuba/algo3/vista/imagenes/cielo/nube.png",
				"/fiuba/algo3/vista/imagenes/cielo/tormenta.png",
				"/fiuba/algo3/vista/imagenes/tierra/espinas.png",
				"/fiuba/algo3/vista/imagenes/tierra/pantano.png",
				"/fiuba/algo3/vista/imagenes/tierra/piedras.png"
		};
		
		imagenes = new HashMap<String,Image>();
		for (String n: nombres){
			imagenes.put(n, new Image(n));
		}
	}
	
	public Image obtenerImagen(String nombre) throws ImagenInexistenteExcption{
		if(!imagenes.containsKey(nombre)){
			throw new ImagenInexistenteExcption(nombre);
		}
		return imagenes.get(nombre);
	}
}
