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
				"/fiuba/algo3/vista/imagenes/tierra/montePerdicion.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/Superion.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/Menasor.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/BoneCrusherVehiculo.png",
				"/fiuba/algo3/vista/imagenes/transformers/BonecrusherHumanoide.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/BumblebeeVehiculo.png",
				"/fiuba/algo3/vista/imagenes/transformers/BumblebeeHumanoide.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/FrenzyVehiculo.png",
				"/fiuba/algo3/vista/imagenes/transformers/FrenzyHumanoide.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/MegatronVehiculo.png",
				"/fiuba/algo3/vista/imagenes/transformers/MegatronHumanoide.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/OptimusVehiculo.png",
				"/fiuba/algo3/vista/imagenes/transformers/OptimusHumanoide.png",
				
				"/fiuba/algo3/vista/imagenes/transformers/RatchetVehiculo.png",
				"/fiuba/algo3/vista/imagenes/transformers/RatchetHumanoide.png",
				
				"/fiuba/algo3/vista/imagenes/bonus/bonusInvulnerabilidad.png",
				"/fiuba/algo3/vista/imagenes/bonus/bonusDoble.png",
				"/fiuba/algo3/vista/imagenes/bonus/bonusFlash.png",
				
				"/fiuba/algo3/vista/imagenes/efectos/bonusInvulnerabilidad.png",
				"/fiuba/algo3/vista/imagenes/efectos/bonusFlash.png",
				"/fiuba/algo3/vista/imagenes/efectos/bonusDoble.png",
				
				"/fiuba/algo3/vista/imagenes/bonus/chispa.png",
				
				cuadroExplosion(1),
				cuadroExplosion(2),
				cuadroExplosion(3),
				cuadroExplosion(4),
				cuadroExplosion(5),
				cuadroExplosion(6),
				cuadroExplosion(7),
				cuadroExplosion(8),
				cuadroExplosion(9)
		};
		
		imagenes = new HashMap<String,Image>();
		for (String n: nombres){
			try{
				imagenes.put(n, new Image(n));
			}catch(Exception e){
				System.out.println(n);
			}
		}
	}
	
	public Image obtenerImagen(String nombre) throws ImagenInexistenteException{
		if(!imagenes.containsKey(nombre)){
			throw new ImagenInexistenteException(nombre);
		}
		return imagenes.get(nombre);
	}
	
	public static String cuadroExplosion(int i){
		if(i<1 || i>9)return "";
		return "/fiuba/algo3/vista/imagenes/explosion/"+i+".png";
	}
}
