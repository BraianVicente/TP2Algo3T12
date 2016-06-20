package fiuba.algo3.vista.CanvasJuego;

import fiuba.algo3.modelo.tablero.Posicion;

public class CallbackMiraGonzalo implements SeleccionCallback {

	ControladorMiraGonzalo llamar;
	public CallbackMiraGonzalo(ControladorMiraGonzalo con){
		llamar = con;
	}
	@Override
	public void seleccion(Casillero c) {
		con.selecciona(c);

	}

}
