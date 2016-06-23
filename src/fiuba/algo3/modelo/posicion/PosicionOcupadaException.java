/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.posicion;

import fiuba.algo3.modelo.tablero.*;

public class PosicionOcupadaException extends PosicionException{
	public PosicionOcupadaException(Posicion p) {
		super(p);
	}

	private static final long serialVersionUID = 3611756005877538235L;

	@Override
	public String descripcion() {
		return "La posicion se encuentra ocupada:";
	}
    
}
