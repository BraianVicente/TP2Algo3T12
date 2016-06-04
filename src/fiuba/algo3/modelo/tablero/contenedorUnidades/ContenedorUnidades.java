package fiuba.algo3.modelo.tablero.contenedorUnidades;

import java.util.HashMap;
import java.util.Map;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public class ContenedorUnidades {
	private Map<Posicion,UnidadConVida> unidadesPorPosicion;
	private Map<UnidadConVida,Posicion> posicionesPorUnidad;
	
	public ContenedorUnidades(){
		unidadesPorPosicion = new HashMap<Posicion,UnidadConVida>();
		posicionesPorUnidad = new HashMap<UnidadConVida,Posicion>();
	}
	public void agregarUnidad(UnidadConVida u, Posicion p) throws UnidadYaContenidaException{
		if(contiene(u)){
			throw new UnidadYaContenidaException(u);
		}
		if(ocupada(p)){
			throw new PosicionOcupadaException(p);
		}
		unidadesPorPosicion.put(p,u);
		posicionesPorUnidad.put(u, p);
	}
	
	public boolean ocupada(Posicion p){
		return unidadesPorPosicion.containsKey(p);
	}
	public boolean contiene(UnidadConVida u){
		return posicionesPorUnidad.containsKey(u);
	}
	public Posicion obtenerPosicion(UnidadConVida u) throws UnidadNoContenidaException{
		if(!contiene(u)){
			throw new UnidadNoContenidaException(u);
		}
		return posicionesPorUnidad.get(u);
	}
	
	public UnidadConVida obtenerUnidad(Posicion p) throws PosicionLibreException{
		if(!ocupada(p)){
			throw new PosicionLibreException(p);
		}
		return unidadesPorPosicion.get(p);
	}
	
	public void removerUnidad(UnidadConVida u) throws UnidadNoContenidaException{
		Posicion p = obtenerPosicion(u);
		posicionesPorUnidad.remove(u);
		unidadesPorPosicion.remove(p);
	}
}
