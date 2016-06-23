package fiuba.algo3.test.juego;

import java.util.ArrayList;

import fiuba.algo3.modelo.tablero.Escenario;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;

public class EscenarioBasico implements Escenario {

	
	ArrayList<Superficie> superficiesAereas ;
    ArrayList<Superficie> superficiesTerrestres;
    
    public EscenarioBasico(){
        superficiesAereas = new ArrayList<Superficie>() ;
        superficiesAereas.add(new NebulosaAndromeda());
        superficiesAereas.add(new TormentaPsionica());
        superficiesTerrestres = new ArrayList<Superficie>();
        superficiesTerrestres.add(new Espinas());
        superficiesTerrestres.add(new Pantano());
       
        
    }
    
	@Override
	public Integer getLargoEscenario() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Integer getAnchoEscenario() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Superficie agregarSuperficieAerea() {
		// TODO Auto-generated method stub
        return new Rocosa();
	}

	@Override
	public Superficie agregarSuperficieTerrestre() {
		// TODO Auto-generated method stub
        return new Nubes();
	}

	@Override
	public Superficie agregarSuperficieAereaAleatoria(Integer posX, Integer posY) {
		// TODO Auto-generated method stub
		return new Rocosa();
	}

	@Override
	public Superficie agregarSuperficieTerrestreAleatoria(Integer posX, Integer posY) {
		// TODO Auto-generated method stub
		return new Nubes();
	}

}
