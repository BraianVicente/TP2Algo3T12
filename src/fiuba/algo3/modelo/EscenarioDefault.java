/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo;

import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author brahvic
 */
public class EscenarioDefault implements Escenario {

    ArrayList<Superficie> superficies ;
    
    public EscenarioDefault(){
        superficies = new ArrayList<Superficie>() ;
        superficies.add(new Espinas());
        superficies.add(new Pantano());
        superficies.add(new NebulosaAndromeda());
        superficies.add(new TormentaPsionica());
        
    }
   
    @Override
    public Integer getLargoEscenario() {
        return 10 ;
    }

    @Override
    public Integer getAnchoEscenario() {
        return 10 ;
    }

    @Override
    public Superficie agregarSuperficieTerrestre() {
        return new Rocosa();
    }

    @Override
    public Superficie agregarSuperficieAerea() {
        return new Nubes();
    }

    @Override
    public Superficie agregarSuperficiesAleatoria(Integer x, Integer y) {
        Random random = new Random();
        Integer index = random.nextInt(superficies.size());
	    return superficies.get(index);  


    }

    
    
    
}
