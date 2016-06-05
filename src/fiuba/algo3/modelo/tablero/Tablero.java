/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.Unidad;
import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.tablero.Posicion.Plano;
import fiuba.algo3.modelo.tablero.contenedorBonuses.ContenedorBonuses;
import fiuba.algo3.modelo.tablero.contenedorCasilleros.ContenedorCasilleros;
import fiuba.algo3.modelo.tablero.contenedorSuperficies.ContenedorSuperficies;
import fiuba.algo3.modelo.tablero.contenedorUnidades.ContenedorUnidades;
import fiuba.algo3.modelo.tablero.contenedorUnidades.NoSeEncuentraUnidadException;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidadesVivientes.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidadesVivientes.Transformer;
import fiuba.algo3.modelo.unidadesVivientes.UnidadConVida;

/**
 *
 * @author brahvic
 */
public class Tablero {
    private ContenedorSuperficies contenedorSuperficies;
	private ContenedorUnidades contenedorUnidades;
	private ContenedorBonuses contenedorBonuses;
	private int ancho, alto;

    public Tablero() {
        this.contenedorUnidades = new ContenedorUnidades();
        this.contenedorSuperficies = new ContenedorSuperficies();
        this.contenedorBonuses = new ContenedorBonuses();
        for (int i = 0; i < alto; i++) {
        	for(int j=0;i<ancho;j++){
            this.contenedorSuperficies.agregarSuperficie(new Nubes(), new Posicion(i,j,Plano.AEREO));
            this.contenedorSuperficies.agregarSuperficie(new Rocosa(),new Posicion(i, j,Plano.TERRESTRE));
        	}
        }
        //Death.getInstance().asignarTablero(this);
    }

    public boolean isEmpty(Posicion posicion) {
        return !this.contenedorUnidades.ocupada(posicion);
    }

   
    public void mover(UnidadConVida unidad, Posicion posicionFin) {
    	Posicion posicionInicio= contenedorUnidades.obtenerPosicion(unidad);
    	if(posicionInicio.getPlano()!=posicionFin.getPlano()) throw new MovimientoInvalidoException();
    	try{
    		Posicion posicionSiguiente;
    		posicionSiguiente=obtenerPosicionADondeMoverse(unidad,posicionFin);
    		if(unidad.getCoeficienteMovimientoActual()==0) throw new MovimientoInvalidoException();
    		unidad.descontarMovimiento((int)Math.floor(1/unidad.getCoeficienteMovimientoActual()));
    		desplazarPosicionContigua(unidad, posicionSiguiente);
    		contenedorSuperficies.obtenerSuperficie(posicionSiguiente).afectarA(unidad);
    		if(contenedorBonuses.ocupada(posicionSiguiente)) this.darBonus(unidad,posicionSiguiente);
    		
    	}catch(MovimientoInvalidoException e){
    		//si quedo en alguna posicion, lo saco y lo vuelvo a donde estaba en un principio
    		try{
    			contenedorUnidades.removerUnidad(unidad);
    		}catch(RuntimeException e2){} 
    		contenedorUnidades.agregarUnidad(unidad, posicionInicio);
    		unidad.restaurarMovimientosRestantes();
    		throw new MovimientoInvalidoException();
    	}
    }

	private Posicion obtenerPosicionADondeMoverse(UnidadConVida unidad, Posicion posicionFin) {
		Posicion posicionActual=contenedorUnidades.obtenerPosicion(unidad);
		int distanciaEnX, distanciaEnY;
		distanciaEnX=posicionActual.distanciaEnXA(posicionFin);
		distanciaEnY=posicionActual.distanciaEnYA(posicionFin);
		return posicionActual.obtenerMismaPosicionDesplazada((int)Math.signum(distanciaEnX),(int)Math.signum(distanciaEnY));
	}

	private void darBonus(UnidadConVida unidad,Posicion posicion){
		Bonus bonus=contenedorBonuses.obtenerBonus(posicion);
		unidad.recibirBonus(bonus);
		contenedorBonuses.removerBonus(bonus);
	}
	public void transformar(Transformer transformer){
		try{
			transformer.transformar();
			contenedorUnidades.cambiarPlano(transformer,transformer.getPlanoPerteneciente());
		}catch(MovimientoInvalidoException e){
			throw new TransformacionInvalida();
		}
	}
	
	public void desplazarPosicionContigua(UnidadConVida unidad, Posicion posicionSiguiente){
		contenedorUnidades.removerUnidad(unidad);
		contenedorUnidades.agregarUnidad(unidad, posicionSiguiente);
	}
}
