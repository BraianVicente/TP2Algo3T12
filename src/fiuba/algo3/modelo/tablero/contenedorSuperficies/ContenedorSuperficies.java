package fiuba.algo3.modelo.tablero.contenedorSuperficies;

import java.util.HashMap;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionLibreException;
import fiuba.algo3.modelo.tablero.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.superficies.Superficie;

public class ContenedorSuperficies {
		private HashMap<Posicion, Superficie> superficies;
		
		
		public ContenedorSuperficies(){
			superficies = new HashMap<Posicion,Superficie>();

		}
		
		public void agregarSuperficie(Superficie s, Posicion p) {
			if(superficies.containsKey(p)){
				throw new PosicionOcupadaException(p);
			}
			superficies.put(p, s);
		}
		
		public boolean ocupada(Posicion p){
			return superficies.containsKey(p);
		}
		
		public Superficie obtenerSuperficie(Posicion p){
			if(!superficies.containsKey(p)){
				throw new PosicionLibreException(p);
			}
			return superficies.get(p);
		}
		
		
}
