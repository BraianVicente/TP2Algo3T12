/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fiuba.algo3.modelo.tablero;

import fiuba.algo3.modelo.juego.condicionvictoria.SinVictoria;
import fiuba.algo3.modelo.juego.condicionvictoria.WinListener;
import java.util.LinkedList;

import fiuba.algo3.modelo.bonuses.Bonus;
import fiuba.algo3.modelo.equipos.Autobots;
import fiuba.algo3.modelo.equipos.Decepticons;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.posicion.PosicionEnElPlano;
import fiuba.algo3.modelo.posicion.PosicionOcupadaException;
import fiuba.algo3.modelo.tablero.contenedorBonuses.ContenedorBonuses;
import fiuba.algo3.modelo.tablero.contenedorSuperficies.ContenedorSuperficies;
import fiuba.algo3.modelo.tablero.contenedorUnidades.ContenedorUnidades;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.Nubes;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Rocosa;
import fiuba.algo3.modelo.unidades.CombinacionInvalidaException;
import fiuba.algo3.modelo.unidades.MovimientoInvalidoException;
import fiuba.algo3.modelo.unidades.Transformer;
import fiuba.algo3.modelo.unidades.Unidad;
import fiuba.algo3.modelo.unidades.UnidadCombinable;

import java.util.ArrayList;

/**
 *
 * @author brahvic
 */
public class Tablero {
    private ContenedorSuperficies contenedorSuperficies;
	private ContenedorUnidades contenedorUnidades;
	private ContenedorBonuses contenedorBonuses;
	private int ancho, alto;
    private WinListener strategiWin;
    private Posicion posicionMontePerdicion;
	private Posicion posicionChispa;

    private static final Integer MAX_DISTANCE = 2; //definir distancia maxima entre units para hacer la combinacion
	
	public int obtenerAncho(){
		return ancho;
	}
	
	public int obtenerAlto(){
		return alto;
	}
	
    public void configurarSuperficie(Posicion pos, Superficie cual){
    	contenedorSuperficies.agregarSuperficie(cual, pos);
    }
    
    public void agregarBonus(Bonus b, Posicion p){
    	contenedorBonuses.agregarBonus(b, p);
    }

    public Tablero(Integer x,Integer y) {
        this.contenedorUnidades = new ContenedorUnidades();
        this.contenedorSuperficies = new ContenedorSuperficies();
        this.contenedorBonuses = new ContenedorBonuses();
        ancho=x;
        alto=y;
        for (int i = 0; i < ancho; i++) {
        	for(int j=0;j<alto;j++){
            this.contenedorSuperficies.agregarSuperficie(new Nubes(), new Posicion(i,j,Plano.AEREO));
            this.contenedorSuperficies.agregarSuperficie(new Rocosa(),new Posicion(i, j,Plano.TERRESTRE));
        	}
        }
        this.colocarChispa();
        this.strategiWin = new SinVictoria();
        this.colocarMontePerdicion();
    }

        
    //Preparando cambios para agregar los escenarios distintos en el juego ;
    public Tablero(Escenario e){
        this(e.getAnchoEscenario(),e.getLargoEscenario());
        this.agregarSuperficiesAereas(e) ;
        this.agregarSuperficiesTerrestres(e) ;
        this.colocarChispaCentrada();
    }

    public Tablero(Escenario escenario,WinListener condicionVictoria){
        this(escenario);
        this.strategiWin = condicionVictoria;
    }
    
    public Tablero(){
        this(10,10);
    }
    
    public Tablero(int ancho, int alto, WinListener win) {
		this(ancho,alto);
        this.strategiWin = win;
	}

	public boolean isEmpty(Posicion posicion) {
        return !(this.contenedorUnidades.ocupada(posicion));
    }

    public void mover(Unidad unidad, Posicion posicionFin) {
    	if(!this.sePuedeMover(unidad, posicionFin)) throw new MovimientoInvalidoException();
    	Posicion posicionInicio= contenedorUnidades.obtenerPosicion(unidad);
    	Posicion posicionActual=posicionInicio;
    	while(!posicionActual.equals(posicionFin)){
    		Posicion posicionSiguiente=obtenerPosicionADondeMoverse(unidad,posicionFin);
    		if(unidad.getCoeficienteMovimientoActual()==0) throw new MovimientoInvalidoException();
    		unidad.descontarMovimiento(1/unidad.getCoeficienteMovimientoActual());
    		this.desplazarPosicionContigua(unidad, posicionSiguiente);

    		posicionActual=contenedorUnidades.obtenerPosicion(unidad);
    		if (unidad.tieneChispa()){
                this.posicionChispa = posicionActual;
    		}

        }

        this.strategiWin.gano(unidad.equipo());
    }

	private void darChispa(Unidad unidad) {
		unidad.darChispa();
		
	}

	private Posicion obtenerPosicionADondeMoverse(Unidad unidad, Posicion posicionFin) {
		Posicion posicionActual=contenedorUnidades.obtenerPosicion(unidad);
		int distanciaEnX, distanciaEnY;
		distanciaEnX=posicionActual.distanciaEnXA(posicionFin);
		distanciaEnY=posicionActual.distanciaEnYA(posicionFin);
		return posicionActual.obtenerMismaPosicionDesplazada((int)Math.signum(distanciaEnX),(int)Math.signum(distanciaEnY));
	}

	private void darBonus(Unidad unidad,Posicion posicion){
		Bonus bonus=contenedorBonuses.obtenerBonus(posicion);
		unidad.recibirBonus(bonus);
		contenedorBonuses.removerBonus(bonus);
	}

	public void transformar(Unidad unidad){
		if(!unidad.sePuedeTransformar())throw new TransformacionInvalida();
        ((Transformer) unidad).transformar(this);
        this.cambiarPlano(unidad, unidad.getPlanoPerteneciente());
        
	}

	private void cambiarPlano(Unidad unidad, Plano nuevoPlano) {
		this.desplazarPosicionContigua(unidad,posicion(unidad).nuevaPosicionConDistintoPlano(nuevoPlano));
		
	}

	public void desplazarPosicionContigua(Unidad unidad, Posicion posicionSiguiente){
		Posicion actual=posicion(unidad);
		contenedorUnidades.removerUnidad(unidad);
		try {
            contenedorUnidades.agregarUnidad(unidad, posicionSiguiente);
            if (this.tieneChispa(posicionSiguiente))  this.darChispa(unidad);
    		contenedorSuperficies.obtenerSuperficie(posicionSiguiente).afectarA(unidad);
    		if(contenedorBonuses.ocupada(posicionSiguiente)) this.darBonus(unidad,posicionSiguiente);
        } catch (PosicionOcupadaException e ){
        	 contenedorUnidades.agregarUnidad(unidad, actual);
            throw new MovimientoInvalidoException();

        }

	}

    public void combinarUnidades(Equipo equipo){
        //equipo.crearCombinacion() ;
    	combinarUnidadesEquipo(equipo);
        
    }
    
	public void combinar(Posicion a, Posicion b, Posicion c) {
        Unidad unita,unitb, unitc;

		unita = obtenerUnidad(a);
		unitb = obtenerUnidad(b);
		unitc = obtenerUnidad(c);

		// check the distance between units
		if ( (a.distanciaA(b) > MAX_DISTANCE) || (b.distanciaA(c) > MAX_DISTANCE) || (c.distanciaA(a) > MAX_DISTANCE) ) {
			throw new CombinacionInvalidaException();
		}

		// check they're in the same team
		if (!unita.equipo().mismoEquipo(unitb.equipo(), unitc.equipo())) {
			throw new CombinacionInvalidaException();
		}

		Unidad comb = unita.equipo().crearCombinacion(this,unita,unitb,unitc);

		if ( (unita.tieneChispa()) || (unitb.tieneChispa()) || (unitc.tieneChispa())) {
			comb.darChispa();
		}
		
		quitarUnidadActual(a);
		quitarUnidadActual(b);
		quitarUnidadActual(c);

		agregarUnidad(a, comb); 

	}

	public Unidad obtenerUnidad(Posicion p) {
		return contenedorUnidades.obtenerUnidad(p);
	}

	public void agregarUnidad(Posicion p, Unidad u) {
		contenedorUnidades.agregarUnidad(u, p);
	}

	private void quitarUnidadActual(Posicion p) {
		Unidad u=contenedorUnidades.obtenerUnidad(p);
		contenedorUnidades.removerUnidad(u);
	}

	public void murio(Unidad u) {
		this.sacarChispaSiLaTiene(u);
		contenedorUnidades.removerUnidad(u);
        this.strategiWin.perdio(u.equipo());

	}

	public void sacarChispaSiLaTiene(Unidad u) {
		if(u.tieneChispa()){
			posicionChispa=contenedorUnidades.obtenerPosicion(u);
		}
	}

	public void agregarChispa(Posicion posicion) {
		posicionChispa=posicion;
	}

	public void atacar(Unidad atacante, Unidad atacado) {
		if(!this.puedeAtacar(atacante,atacado)) throw new AtaqueInvalidoException();
		atacante.atacarA(atacado);
	}
	
	public boolean puedeAtacar(Unidad atacante, Posicion atacado){
		if(isEmpty(atacado))return false;
		Unidad objetivo=obtenerUnidad(atacado);
		return puedeAtacar(atacante, objetivo)&&!atacante.esDelMismoEquipo(objetivo);
	}
	
	public boolean sePuedeTransformar(Unidad u){
		return u.sePuedeTransformar()&&(!u.cambiaDePlanoAlTransformase()||isEmpty(posicion(u).nuevaPosicionConDistintoPlano(u.planoPertenecienteSiguienteForma())));
	}

	private boolean puedeAtacar(Unidad atacante, Unidad atacado) {

		LinkedList<Posicion> posicionesQueDeberianEstarVacias=this.contenedorUnidades.obtenerPosicion(atacante).posicionesQueTocaLaRectaQueVaA(this.contenedorUnidades.obtenerPosicion(atacado));
		posicionesQueDeberianEstarVacias.remove(contenedorUnidades.obtenerPosicion(atacante));
		posicionesQueDeberianEstarVacias.remove(contenedorUnidades.obtenerPosicion(atacado));
		return atacante.puedeAtacar(contenedorUnidades.obtenerPosicion(atacante), contenedorUnidades.obtenerPosicion(atacado))&&estanVacias(posicionesQueDeberianEstarVacias);
	}

	private boolean estanVacias(LinkedList<Posicion> posicionesQueDeberianEstarVacias) {
		for(Posicion p: posicionesQueDeberianEstarVacias){
			if(contenedorUnidades.ocupada(p))return false;
		}
		return true;
	}

	public boolean tieneChispa(Posicion pos) {
		return posicionChispa.equals(pos);
	}
	//VOLAR
	public void agarrado(Bonus b) {
		// TODO Auto-generated method stub

	} 

	public void agregarSuperficie(Superficie sup, Posicion pos) {
		contenedorSuperficies.agregarSuperficie(sup,pos);
	}


    private void combinarUnidadesEquipo(Equipo equipo) {
        ArrayList<Posicion> unidadesEquipo  = this.obtenerPosicionesUnidadesVivasEquipo(equipo) ;
        if (unidadesEquipo.size() == 3 ){
            combinar(unidadesEquipo.get(0), unidadesEquipo.get(1),unidadesEquipo.get(2));
        }

    }

    private ArrayList<Posicion> obtenerPosicionesUnidadesVivasEquipo(Equipo equipo) {
        return contenedorUnidades.obtenerPosicionesUnidadesVivasEquipo(equipo);
    }

    public void pasarTurnoEquipo(Equipo equipo) {
        ArrayList<Posicion> posU = this.obtenerPosicionesUnidadesVivasEquipo(equipo);
        for (Posicion pos : posU) {
            Unidad unidad = this.obtenerUnidad(pos);
            if (unidad.es(equipo)){
                unidad.avanzarTurno();
            }
        }
    }

    public boolean existenUnidadeDeEquipo(Equipo equipo) {
        return (this.obtenerPosicionesUnidadesVivasEquipo(equipo).size() > 0) ;
    }

	public ArrayList<Unidad> obtenerUnidades() {
		return contenedorUnidades.obtenerUnidades();
	}

	public Posicion posicion(Unidad u) {
		return contenedorUnidades.obtenerPosicion(u);
	}

	public Superficie obtenerSuperficie(Posicion p) {
		return contenedorSuperficies.obtenerSuperficie(p);
	}

    public boolean unidadesContieneChispa(Equipo equipo) {
        ArrayList<Unidad> unidades = this.obtenerUnidades();
        for (Unidad unidad:unidades){
            if(unidad.tieneChispa()){
                return true ;
            }
        }
        return false ;
    }

    public void colocarChispaCentrada(){
        Integer enX,rangoMayorX,rangoMenorX,enY,rangoMayorY,rangoMenorY;
        rangoMayorY = (alto/5) + (alto/2);
        rangoMenorY = (alto/2) - (alto/5) ;
        rangoMayorX = (ancho/2) + (ancho/5) ;
        rangoMenorX = (ancho/2) - (ancho/5) ;
        enX = (int)(Math.random()*(rangoMayorX-rangoMenorX+1)+rangoMenorX);
        enY = (int)(Math.random()*(rangoMayorY-rangoMenorY+1)+rangoMenorY);
        this.agregarChispa(new Posicion(enX,enY));
    }
    
    public void colocarChispa() {
        this.agregarChispa(new Posicion(this.alto/2,this.ancho/2));
    }

	public boolean sePuedeMover(Unidad unidad, Posicion posicionFinal) {
		LinkedList<Posicion> posicionesQueDeberianEstarVacias=this.posicion(unidad).posicionesUsadasParaMoverseEnOrden(posicion(unidad),posicionFinal);
		posicionesQueDeberianEstarVacias.remove(posicion(unidad));
		
		return (unidad.getCoeficienteMovimientoActual()!=0)&&
				this.estanVacias(posicionesQueDeberianEstarVacias)&&
				alcanzanCantidadDeMovimientos(unidad,posicionFinal)&&
				posicion(unidad).getPlano()==posicionFinal.getPlano()
				;
	}

	private boolean alcanzanCantidadDeMovimientos(Unidad unidad, Posicion posicionFinal) {
		float cantidadDeMovimientosNecesarios=0;
		Posicion posicionActual;
		float coeficienteEnSuperficieActual;
		float coeficienteInicial=unidad.getCoeficienteMovimientoActual();
		float coeficienteTotal;
		LinkedList<Posicion> posicionesPorLasQueSeMueve=posicion(unidad).posicionesUsadasParaMoverseEnOrden(posicion(unidad), posicionFinal);
		for(int i=0;i<posicionesPorLasQueSeMueve.size()-1;i++){
		posicionActual=posicionesPorLasQueSeMueve.get(i);
		coeficienteEnSuperficieActual=unidad.coeficienteVelocidadParaSuperficie(this.contenedorSuperficies.obtenerSuperficie(posicionActual));
		coeficienteTotal=coeficienteInicial*coeficienteEnSuperficieActual;
		if(coeficienteTotal==0) return false;
		cantidadDeMovimientosNecesarios+=1/(coeficienteInicial*coeficienteEnSuperficieActual);
		}
		
		//si la unidad estaba afectada por pantano en un principio		
		//la cantidad de movimientos dara el doble de lo real
		if(unidad.estaAfectadaPorPantano()) cantidadDeMovimientosNecesarios=cantidadDeMovimientosNecesarios/2;
		return cantidadDeMovimientosNecesarios<=unidad.getMovimientosRestantes();
		
		
	}

    public Posicion getPosicionChispa() {
        return this.posicionChispa ;
    }

    public Posicion getPosicionMontePerdicion() {
        return this.posicionMontePerdicion ;
    }
    
    private void agregarSuperficiesAereas(Escenario e) {
        Integer limite = (e.getAnchoEscenario()*e.getLargoEscenario())/5 ;
        Integer posX,posY ;
        for(Integer i = 0; i < limite;i ++ ){
            posX = (int)(Math.random()*(e.getAnchoEscenario()+1));//random
            posY = (int)(Math.random()*(e.getLargoEscenario()+1));//random
            this.agregarSuperficie(e.agregarSuperficieAereaAleatoria(posX, posY), new Posicion(posX,posY,Plano.AEREO));
        }
    }

    private void agregarSuperficiesTerrestres(Escenario e) {
        Integer limite = (e.getAnchoEscenario()*e.getLargoEscenario())/5 ;
        Integer posX,posY ;
        for(Integer i = 0; i < limite;i ++ ){
            posX = (int)(Math.random()*(e.getAnchoEscenario()+1));//random
            posY = (int)(Math.random()*(e.getLargoEscenario()+1));//random
            this.agregarSuperficie(e.agregarSuperficieTerrestreAleatoria(posX, posY), new Posicion(posX,posY,Plano.TERRESTRE));
        }
    }

    public void colocarMontePerdicion() {
        int posX = (int)(Math.random()*(ancho+1)); //random
        int posY = (int)(Math.random()*(alto+1)); //random
        this.posicionMontePerdicion = new Posicion(posX,posY);
        
    }

    public void colocarMontePerdicion(Posicion posicion) {
        this.posicionMontePerdicion = posicion ;
    }

	public ArrayList<Bonus> obtenerBonuses() {
		return contenedorBonuses.obtenerBonuses();
	}

	public Posicion posicion(Bonus b) {
		return contenedorBonuses.obtenerPosicion(b);
	}

	public void desarmar(UnidadCombinable u) {
		ArrayList<Unidad> lista=u.componentesVivos();
		Posicion posInicial=contenedorUnidades.obtenerPosicion(u);
		contenedorUnidades.removerUnidad(u);
		for(Unidad unidadNueva:lista){
			reubicar(unidadNueva,posInicial);
		}
        this.strategiWin.perdio(u.equipo());
	}

	private void reubicar(Unidad unidadNueva, Posicion posInicial) {
		Posicion posProbable=posInicial;
		posProbable=posProbable.nuevaPosicionConDistintoPlano(unidadNueva.getPlanoPerteneciente());
		//me muevo a izquierda
		while(contenedorUnidades.ocupada(posProbable)&&this.enLimites(posProbable)){
			posProbable=posProbable.obtenerMismaPosicionDesplazada(-1, 0);
		}
		//despues a derecha si no puedo
		while(contenedorUnidades.ocupada(posProbable)&&this.enLimites(posProbable)){
			posProbable=posProbable.obtenerMismaPosicionDesplazada(1, 0);
		}
		
		this.agregarUnidad(posProbable, unidadNueva);
        
        this.strategiWin.gano(unidadNueva.equipo());

	}

	public boolean enLimites(PosicionEnElPlano p) {
		return enLimites(new Posicion(p,Plano.TERRESTRE));
	}
	public boolean enLimites(Posicion p) {
		return (0<=p.getX() && p.getX()<this.obtenerAncho()) &&
				(0<=p.getY() && p.getY()<this.obtenerAlto());
	}

	public boolean tieneCombinacion(Equipo equipo) {
		ArrayList<Posicion> list=this.obtenerPosicionesUnidadesVivasEquipo(equipo);
		return list.size()==1&&contenedorUnidades.obtenerUnidad(list.get(0)).esCombinacion();
	}

	public boolean contiene(Unidad u) {
		return contenedorUnidades.contiene(u);
	}

	public void cambiarCondicionVictoria(WinListener wl) {
		strategiWin=wl;
		
	}
	
	public boolean laChispaLaTieneUnaUnidad(){
		return this.unidadesContieneChispa(new Autobots())
				||this.unidadesContieneChispa(new Decepticons());
	}

	public ArrayList<PosicionEnElPlano> obtenerHaloMovimiento(Unidad u) {
		ArrayList<PosicionEnElPlano> ret = new ArrayList<PosicionEnElPlano>(); 
		int xInicio=posicion(u).getX()-u.getDistanciaMovimiento();
		int yInicio=posicion(u).getY()-u.getDistanciaMovimiento();
		
		int xFin=posicion(u).getX()+u.getDistanciaMovimiento();
		int yFin=posicion(u).getY()+u.getDistanciaMovimiento();
		
		for(int x = xInicio; x<=xFin; x++){
			for(int y = yInicio; y<=yFin; y++){
				
				Posicion chequeo = new Posicion(x,y,u.getPlanoPerteneciente());
				if(enLimites(chequeo) && sePuedeMover(u, chequeo)){
					ret.add(chequeo.obtenerPosicionEnElPlano());
				}
				
			}
		}
		
		return ret;
	}

	public ArrayList<PosicionEnElPlano> obtenerHaloAtaque(Unidad u) {
		ArrayList<PosicionEnElPlano> ret = new ArrayList<PosicionEnElPlano>(); 
		int xInicio=posicion(u).getX()-u.getDistanciaAtaque();
		int yInicio=posicion(u).getY()-u.getDistanciaAtaque();
		
		int xFin=posicion(u).getX()+u.getDistanciaMovimiento();
		int yFin=posicion(u).getY()+u.getDistanciaMovimiento();
		
		for(int x = xInicio; x<=xFin; x++){
			for(int y = yInicio; y<=yFin; y++){
				Posicion chequeo = new Posicion(x,y);
				Posicion aereo = chequeo.nuevaPosicionConDistintoPlano(Plano.AEREO);
				if(enLimites(chequeo) && (puedeAtacar(u, chequeo) || puedeAtacar(u, aereo)) ){
					ret.add(chequeo.obtenerPosicionEnElPlano());
				}
			}
		}
		
		return ret;
	}

}
