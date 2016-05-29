package fiuba.algo3.modelo.chispa;

public class ChispaSuprema extends Chispa {
	private static Chispa INSTANCE = new ChispaSuprema();
	
	private ChispaSuprema() {}
	
	public Chispa getInstance() {
		return INSTANCE;
	}
}
