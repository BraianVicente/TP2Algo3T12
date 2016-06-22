/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.unidades;

import java.util.ArrayList;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.FormaHumanoide;
import fiuba.algo3.modelo.formas.HumanoideOptimusprime;
import fiuba.algo3.modelo.formas.Peterbilt;
import fiuba.algo3.modelo.tablero.superficies.Superficie;

/**
 *
 * @author brahvic
 */
public abstract class UnidadCombinable extends Unidad {

    private Integer turnosCreacion ;
	private int vidaMax;
	private Unidad componenteUno, componenteDos, componenteTres;
    public UnidadCombinable(Equipo equipo,DeathListener command,Unidad unita, Unidad unitb, Unidad unitc) {
        super(equipo, command);
        this.turnosCreacion = 2 ;
		vidaMax = unita.getVida() + unitb.getVida() + unitc.getVida();
		vida=vidaMax;
		this.componenteDos=unitb;
		this.componenteUno=unita;
		this.componenteTres=unitc;
    }

    
    @Override
    public void avanzarTurno(){
    	super.avanzarTurno();
    	turnosCreacion--;
    	if(turnosCreacion<=0) command.murio(this);
    }
    
    @Override
    public int getVidaMaxima(){
    	return vidaMax;
    }
    
    @Override
    public boolean existe(){
        return (this.turnosCreacion > 0)&&!algunComponenteMuerto() ;
    }
    
    @Override 
    public boolean sePuedeTransformar(){
    	return false;
    }
    
    public float coeficienteVelocidadParaSuperficie(Superficie superficie){
    	FormaHumanoide formaGenerica=new HumanoideOptimusprime();
    	return superficie.obtenerVelocidadParaForma(formaGenerica);
    	
    }
    
    @Override
    protected void disminuirVida(int danio) {
    	if(modificadores.recibeDanio()){
    		vida -= danio;
    		
    		componenteUno.disminuirVida(danio/3);
    		componenteDos.disminuirVida(danio/3);
    		componenteTres.disminuirVida(danio/3);
    	}
    	 if (algunComponenteMuerto()||getVida()<=0)	command.murio(this);
    	 
    }
    
   private boolean algunComponenteMuerto(){
	   return componenteUno.getVida()<=0||componenteDos.getVida()<=0||componenteTres.getVida()<=0;
   }
   
   public ArrayList<Unidad> componentesVivos(){
	   ArrayList<Unidad> lista=new ArrayList<Unidad>();
	if(componenteUno.getVida()>0) lista.add(componenteUno);
	if(componenteDos.getVida()>0) lista.add(componenteDos);
	if(componenteTres.getVida()>0) lista.add(componenteTres);
	return lista;
	   
	   
   }
   @Override
   public boolean esCombinacion(){
	   return true;
   }
}
