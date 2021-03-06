package fiuba.algo3.modelo.unidades;

import fiuba.algo3.modelo.unidades.muerte.DeathListener;
import fiuba.algo3.modelo.chispa.ChispaHolder;
import fiuba.algo3.modelo.chispa.ChispaSuprema;
import fiuba.algo3.modelo.equipos.Equipo;
import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.modificadores.ModificadorNebulosa;
import fiuba.algo3.modelo.modificadores.ModificadorPsionica;
import fiuba.algo3.modelo.posicion.Posicion.Plano;
import fiuba.algo3.modelo.tablero.Tablero;
import fiuba.algo3.modelo.tablero.superficies.Superficie;
import fiuba.algo3.modelo.tablero.superficies.aerea.NebulosaAndromeda;
import fiuba.algo3.modelo.tablero.superficies.aerea.TormentaPsionica;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Espinas;
import fiuba.algo3.modelo.tablero.superficies.terrestre.Pantano;


public abstract class Transformer extends Unidad {
	private Forma forma;
	boolean seTransformoEnEsteTurno;
	
	Transformer(Forma f, Equipo equipo){
		super(equipo);
		movimientosUsados=0;
		seTransformoEnEsteTurno=false;
		this.forma = f;

    }
    
	public void transformar(){
			//if(seTransformoEnEsteTurno) throw new TransformacionInvalida();
			forma = forma.getAlternativa();
			seTransformoEnEsteTurno=true;
		//sin embargo, creo q esta bueno que queden los getVehiculo() getHumanoide() por las dudas
		//(y porque son los que acordamos, y porque 
		//se me hace que tiene mas sentido aunque esta solucion me guste mas porque ahorra un if (if esVehiculo()))
	}
	
    @Override
    public boolean sePuedeTransformar(){
    	return !this.seTransformoEnEsteTurno;
    	//return true;
    }
    
    @Override
    public boolean cambiaDePlanoAlTransformase() {
		return !(this.planoPertenecienteSiguienteForma()==this.getPlanoPerteneciente());
	}
    
    @Override
	public Plano planoPertenecienteSiguienteForma() {
		return forma.getAlternativa().getPlanoPerteneciente();
	}
	public Plano getPlanoPerteneciente() {
		return forma.getPlanoPerteneciente();
	}
	//------------------formas--------------------//
	protected abstract Forma getVehiculo();
	protected abstract Forma getHumanoide();
	
	//-----------------estadisticas---------------//
	@Override
	public int getDistanciaAtaque() {
		return forma.getDistanciaAtaque();
	}

	@Override
	public int getPuntosAtaque() {
		return (int) Math.ceil(forma.getPuntosAtaque()*forma.coeficienteAtaqueVehiculo(coeficienteAtaqueModoVehiculo()));
	}

	@Override
	public int getDistanciaMovimiento() {
		return forma.getDistanciaMovimiento();
	}
	///lo ideal es volar estos
	public boolean esVehiculo() {
		return forma.esVehiculo();
	}
	
	public boolean esHumanoide(){
		return forma.esHumanoide();
		
	}

    
    @Override
    public boolean esTerrestre(){
    	return forma.esTerrestre();
    }
    @Override
    public boolean esAerea(){
    	return forma.esAerea();
    }
    //esto tambien habria que volarlo
    
    public float getCoeficienteMovimientoActual() {
  		// TODO Auto-generated method stub
  		return modificadores.coeficienteVelocidad()*forma.obtenerCoeficientePorVelocidad(modificadores);
  	}
    //--------------------------superficies-----------------------------//

    public void serAfectadoPor(NebulosaAndromeda s){
    	if(esAerea()){//////!!!!!!!!!!
    		agregarModificador(new ModificadorNebulosa());
    	}
    }
	

    
    
	public void serAfectadoPor(TormentaPsionica s){
		if(esAerea()){//////!!!!!!!!!!
    		agregarModificador(new ModificadorPsionica());
    	}
	}
	public void serAfectadoPor(Espinas s){
		disminuirVida((int)forma.danioRealPorEspinas((getVidaMaxima()*5)/100));
	}


	public float coeficienteMovimientoEn(Pantano s){
		return forma.coeficienteMovimientoEnPantano();
	}
	
	@Override
	public void avanzarTurno(){
		super.avanzarTurno();
		this.seTransformoEnEsteTurno=false;
	}
	@Override
	public float coeficienteVelocidadParaSuperficie(Superficie superficie) {
		return		forma.obtenerCoeficienteVelocidad(superficie);
	}	
	//------------------------------dibujo---------------------------------//
    @Override
	public String nombreImagen(){
		return forma.nombreImagen();
	}
	
    @Override
    public String nombre(){
        String n = forma.nombre() ;
        return n;
    }
    
    @Override
    public void darChispa() {
        if (this.forma.esHumanoide()){
    		chispa = ChispaSuprema.getInstance();
        }
	}

	public void transformar(Tablero tablero) {
		if(!forma.getAlternativa().esHumanoide()){
			tablero.sacarChispaSiLaTiene(this);
			this.chispa=new ChispaHolder();
		}
		transformar();

		
	}

}
