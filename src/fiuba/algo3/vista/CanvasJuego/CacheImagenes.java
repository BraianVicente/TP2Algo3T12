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
				"/fiuba/algo3/vista/imagenes/tierra/piedras.png",
				
				"fiuba/algo3/vista/imagenes/transformers/Superion.png",
				
				"fiuba/algo3/vista/imagenes/transformers/Menasor.png",
				
				"fiuba/algo3/vista/imagenes/transformers/BoneCrusherVehiculo.png",
				"fiuba/algo3/vista/imagenes/transformers/BonecrusherHumanoide.png",
				
				"fiuba/algo3/vista/imagenes/transformers/BumblebeeVehiculo.png",
				"fiuba/algo3/vista/imagenes/transformers/BumblebeeHumanoide.png",
				
				"fiuba/algo3/vista/imagenes/transformers/FrenzyVehiculo.png",
				"fiuba/algo3/vista/imagenes/transformers/FrenzyHumanoide.png",
				
				"fiuba/algo3/vista/imagenes/transformers/MegatronVehiculo.png",
				"fiuba/algo3/vista/imagenes/transformers/MegatronHumanoide.png",
				
				"fiuba/algo3/vista/imagenes/transformers/OptimusVehiculo.png",
				"fiuba/algo3/vista/imagenes/transformers/OptimusHumanoide.png",
				
				"fiuba/algo3/vista/imagenes/transformers/RatchetVehiculo.png",
				"fiuba/algo3/vista/imagenes/transformers/RatchetHumanoide.png",
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
