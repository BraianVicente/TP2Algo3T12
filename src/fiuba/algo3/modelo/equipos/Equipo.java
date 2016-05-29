package fiuba.algo3.modelo.equipos;

public abstract class Equipo {
	public boolean equals(Object otro){
		if(otro == null) return false;
		if(otro==this)return true;
		if(!(otro instanceof Equipo))return false;
		return mismoEquipo((Equipo)otro);
	}
	
	public abstract boolean mismoEquipo(Equipo otro);
	public abstract boolean mismoEquipo(Autobots otro);
	public abstract boolean mismoEquipo(Decepticons otro);
	public abstract boolean mismoEquipo(Ninguno otro);
}
