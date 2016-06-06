package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

public class Death implements DeathListener {
	private Tablero aInformar;
	public Death(Tablero aInformar){
		this.aInformar = aInformar;
	}
	public void murio(UnidadConVida u) throws NoSeEncuentraUnidadException{
		aInformar.murio(u);
	}
}
