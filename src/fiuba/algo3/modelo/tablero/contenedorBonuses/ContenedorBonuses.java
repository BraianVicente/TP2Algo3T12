package fiuba.algo3.modelo.tablero.contenedorBonuses;

import java.util.HashMap;
import java.util.Map;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;

public class ContenedorBonuses {
	private Map<Posicion, Bonus> bonusesPorPosicion;
	private Map<Bonus, Posicion> posicionesPorBonus;
	
	public ContenedorBonuses(){
		bonusesPorPosicion = new HashMap<Posicion,Bonus>();
		posicionesPorBonus = new HashMap<Bonus,Posicion>();
	}
	public void agregarBonus(Bonus u, Posicion p) throws  BonusYaContenidoException{
		if(contiene(u)){
			throw new BonusYaContenidoException(u);
		}
		if(ocupada(p)){
			throw new PosicionOcupadaException(p);
		}
		bonusesPorPosicion.put(p,u);
		posicionesPorBonus.put(u, p);
	}
	
	public boolean ocupada(Posicion p){
		return bonusesPorPosicion.containsKey(p);
	}
	private boolean contiene(Bonus u){
		return posicionesPorBonus.containsKey(u);
	}
	private Posicion obtenerPosicion(Bonus u) throws BonusNoContenidoException{
		if(!contiene(u)){
			throw new BonusNoContenidoException(u);
		}
		return posicionesPorBonus.get(u);
	}
	
	public Bonus obtenerBonus(Posicion p) throws PosicionLibreException{
		if(!ocupada(p)){
			throw new PosicionLibreException(p);
		}
		return bonusesPorPosicion.get(p);
	}
	
	public void removerBonus(Bonus u) throws BonusNoContenidoException{
		Posicion p = obtenerPosicion(u);
		posicionesPorBonus.remove(u);
		bonusesPorPosicion.remove(p);
	}
}
