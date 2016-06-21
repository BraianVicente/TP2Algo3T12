package fiuba.algo3.modelo;

import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.modelo.unidades.UnidadCombinable;

public class DesarmadorCombinable implements DeathListener{
	 private Tablero aInformar;
	 private Equipo equipoPerteneciente;
		
	    public DesarmadorCombinable(Tablero aInformar, Equipo equipo){
	  		this.aInformar = aInformar;
	  		equipoPerteneciente=equipo;
	  	}
	    
	    @Override
		public void murio(Unidad u){
			aInformar.desarmar((UnidadCombinable)u);
			equipoPerteneciente.combinacionDesarmada();
			
	    }
}
