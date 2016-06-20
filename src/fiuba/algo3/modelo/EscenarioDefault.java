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

    ArrayList<Superficie> superficiesAereas ;
    ArrayList<Superficie> superficiesTerrestres;
    
    public EscenarioDefault(){
        superficiesAereas = new ArrayList<Superficie>() ;
        superficiesAereas.add(new NebulosaAndromeda());
        superficiesAereas.add(new TormentaPsionica());
        superficiesTerrestres = new ArrayList<Superficie>();
        superficiesTerrestres.add(new Espinas());
        superficiesTerrestres.add(new Pantano());
       
        
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
    public Superficie agregarSuperficieAereaAleatoria(Integer x, Integer y) {
        Random random = new Random();
        Integer index = random.nextInt(superficiesAereas.size());
	    return superficiesAereas.get(index);  


    }

    @Override
    public Superficie agregarSuperficieTerrestreAleatoria(Integer posX, Integer posY) {
        Random random = new Random();
        Integer index = random.nextInt(superficiesTerrestres.size());
	    return superficiesTerrestres.get(index);  
    }

    
    
    
}
