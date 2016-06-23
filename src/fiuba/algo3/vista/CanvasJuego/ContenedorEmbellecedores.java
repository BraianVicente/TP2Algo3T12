package fiuba.algo3.vista.CanvasJuego;

import java.util.ArrayList;
import java.util.HashMap;

import fiuba.algo3.modelo.juego.Juego;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.unidades.Unidad;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ContenedorEmbellecedores {
	private HashMap<Unidad,EmbellecedorUnidad> embellecedores;
	private ArrayList<Explosion> explosiones;
	
	public ContenedorEmbellecedores (){
		embellecedores = new HashMap<Unidad,EmbellecedorUnidad>();
		explosiones = new ArrayList<Explosion>();
	}
	/**
	 * Agrega los embellecedores que no esten presentes ya
	 * @param juego
	 */
	public void revisarEmbellecedores(Juego juego){
		ArrayList<Unidad> unidades = juego.obtenerUnidades();
		for(Unidad u : unidades){
			if(!embellecedores.containsKey(u)){
				embellecedores.put(u, new EmbellecedorUnidad(u,juego));
				u.agregarDeathListener(x->removerEmbellecedorDe(x));
			}else{
				if(!juego.enTablero(u)){
					embellecedores.remove(u);
				}
			}
		}
	}
	
	private void removerEmbellecedorDe(Unidad u) {
		System.out.println("Removiendo el embellecedor!");
		Explosion nueva = new Explosion(embellecedores.get(u).getUltimaPosicion(),e->removerExplosion(e));
		explosiones.add(nueva);
		embellecedores.remove(u);
		
	}
	
	private void removerExplosion(Explosion e) {
		explosiones.remove(e);
	}
	
	public void dibujarUnidad(GraphicsContext gc, Unidad u, Juego juego, MueveVista mueveVista,CacheImagenes cacheImagenes) {
		embellecedores.get(u).dibujar(gc,mueveVista,cacheImagenes);
	}
	
	public void dibujarExplosiones(GraphicsContext gc, MueveVista mueveVista,CacheImagenes cacheImagenes) {
		for(Explosion exp : explosiones){
			exp.dibujarEn(gc,mueveVista,cacheImagenes);
			/*
			Image imgU = cacheImagenes.obtenerImagen(unidad.nombreImagen());
			double extraHovereando = 0;
			if(hovereando) extraHovereando =  (mueveVista.anchoCasillero()*0.3f); 
			gc.drawImage(imgU,
					mueveVista.xPantalla(x+Math.random()*shake*2-shake)-extraHovereando/2,
					mueveVista.yPantalla(y+Math.random()*shake*2-shake)-extraHovereando/2,
					mueveVista.anchoCasillero()+extraHovereando,
					mueveVista.altoCasillero()+extraHovereando
				);
			*/
		}
	}
	
	
	public void detectarCambioPosicion() {
		for(EmbellecedorUnidad e : embellecedores.values()){
			e.detectarCambio();
		}
	}
	
	public void settearHovereado(Unidad u){
		for(EmbellecedorUnidad e : embellecedores.values()){
			e.setHovereando(false);
		}
		if(u!=null && embellecedores.containsKey(u)){
			embellecedores.get(u).setHovereando(true);
		}
	}
}
