package fiuba.algo3.modelo.modificadores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fiuba.algo3.modelo.formas.Forma;
import fiuba.algo3.modelo.formas.FormaHumanoide;
import fiuba.algo3.modelo.formas.FormaVehiculo;

public class ContenedorModificadores {
	List<Modificador> modificadores;
	public ContenedorModificadores(){
		modificadores = new ArrayList<Modificador>(); 
	}
	
	public void agregar(Modificador m){
		modificadores.add((Modificador) m.clone());
	}
	
	public void pasaTurno(){
		modificadores.forEach(m->m.pasaTurno());
		modificadores=modificadores.parallelStream()
				.filter(a->a.haceEfecto())
				.collect(Collectors.toList());
	}
	public void sacarModificadorPantano(){
		modificadores=modificadores.parallelStream()
				.filter(a->!a.esPantano())
				.collect(Collectors.toList());
	}
	public float coeficienteAtaqueModoVehiculo(){
		return modificadores.parallelStream()
				.map(a->a.coeficienteAtaqueModoVehiculo())
				.reduce(1f,(a,b)->{if(a<b)return a; else return b;});
	}
	
	public float coeficienteAtaque(){
		return modificadores.parallelStream()
				.map(a->a.coeficienteAtaque())
				.reduce(1f,(a,b)->a*b);
	}
	
	public boolean recibeDanio(){
		return modificadores.parallelStream()
				.map(a->a.recibeDanio())
				.reduce(true,(a,b)->a&&b);//false pisa a true
	}
	public boolean puedeMoverse(){
		return modificadores.parallelStream()
				.map(a->a.puedeMoverse())
				.reduce(true,(a,b)->a&&b);//false pisa a true
	}
	
	
	public float coeficienteVelocidad(){
		return modificadores.parallelStream()
				.map(a->a.coeficienteVelocidad())
				.reduce(1f,(a,b)->a*b);
	}
	
	public boolean afectadoPorPsionica(){
		return modificadores.parallelStream()
				.map(a->a.afectadoPorPsionica())
				.reduce(false,(a,b)->a||b);//true pisa a false
	}
	
	public float coeficienteVelocidadPorForma(FormaHumanoide humanoide){
		return modificadores.parallelStream()
				.map(a->a.coeficienteVelocidadPorForma(humanoide))
				.reduce(1f,(a,b)->a*b);	
	}
	
	public float coeficienteVelocidadPorForma(FormaVehiculo vehiculo ){
		return modificadores.parallelStream()
				.map(a->a.coeficienteVelocidadPorForma(vehiculo))
				.reduce(1f,(a,b)->a*b);	
	}
	public float coeficienteVelocidadPorForma(Forma forma ){
		return modificadores.parallelStream()
				.map(a->a.coeficienteVelocidadPorForma(forma))
				.reduce(1f,(a,b)->a*b);	
	}
}
