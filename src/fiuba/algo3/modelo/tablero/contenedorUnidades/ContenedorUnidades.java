package fiuba.algo3.modelo.tablero.contenedorUnidades;

import fiuba.algo3.modelo.equipos.Equipo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.unidades.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidades.Transformer;
import fiuba.algo3.modelo.unidades.Unidad;
import java.util.ArrayList;

public class ContenedorUnidades  {
	private Map<Posicion,Unidad> unidadesPorPosicion;
	private Map<Unidad,Posicion> posicionesPorUnidad;

	public ContenedorUnidades(){
		unidadesPorPosicion = new HashMap<Posicion,Unidad>();
		posicionesPorUnidad = new HashMap<Unidad,Posicion>();
	}
	public void agregarUnidad(Unidad u, Posicion p) throws UnidadYaContenidaException{
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
	public boolean contiene(Unidad u){
		return posicionesPorUnidad.containsKey(u);
	}
	public Posicion obtenerPosicion(Unidad u) throws UnidadNoContenidaException{
		if(!contiene(u)){
			throw new UnidadNoContenidaException(u);
		}
		return posicionesPorUnidad.get(u);
	}

	public Unidad obtenerUnidad(Posicion p) throws PosicionLibreException{
		if(!ocupada(p)){
			throw new PosicionLibreException(p);
		}
		return unidadesPorPosicion.get(p);
	}

	public void removerUnidad(Unidad u) throws UnidadNoContenidaException{
		Posicion p = obtenerPosicion(u);
		posicionesPorUnidad.remove(u);
		unidadesPorPosicion.remove(p);
	}
	public void cambiarPlano(Transformer transformer, Plano planoPerteneciente) {
		Posicion nuevaPosicion=posicionesPorUnidad.get(transformer).nuevaPosicionConDistintoPlano(planoPerteneciente);
		mover(transformer,nuevaPosicion);
	}
	private void mover(Transformer transformer, Posicion nuevaPosicion) {
		Posicion posicionVieja=obtenerPosicion(transformer);
		try{
			removerUnidad(transformer);
			agregarUnidad(transformer, nuevaPosicion);
		}catch(PosicionOcupadaException e){
			agregarUnidad(transformer,posicionVieja);
			throw new MovimientoInvalidoException();
		}

	}

    public ArrayList<Posicion> obtenerPosicionesUnidadesVivasEquipo(Equipo equipo) {
        ArrayList<Posicion> posicionesUnidades = new ArrayList<Posicion>();
        for (Unidad unidad : posicionesPorUnidad.keySet()) {
            if(unidad.es(equipo)){
                posicionesUnidades.add(posicionesPorUnidad.get(unidad));
            }
        }
        return posicionesUnidades ;

    }

	public ArrayList<Unidad> obtenerUnidades() {
		ArrayList<Unidad> ret = new ArrayList<Unidad>();
		for(Map.Entry<Unidad, Posicion> entry : posicionesPorUnidad.entrySet()){
			ret.add(entry.getKey());
		}
		return ret;
	}

}
