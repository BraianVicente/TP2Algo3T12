package fiuba.algo3.vista.CanvasJuego;

import fiuba.algo3.modelo.posicion.Posicion;
import fiuba.algo3.modelo.posicion.PosicionEnElPlano;
import java.util.ArrayList;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MueveVista {
	//recurso compartido
	private volatile double x;
	private volatile double y;
	private volatile double escala;
	
	//private Object lock = new Object();
	
	private double x_m_inicial;
	private double y_m_inicial;
	
	private double x_mouse;
	private double y_mouse;
	
	private double x_inicial;
	private double y_inicial;
	
	private boolean presionando;
	
	private double ancho;
	private double alto;
	private ArrayList<CallbackPosicion> cllbacksClickeo;
	private ArrayList<CallbackPosicion> cllbacksHovereo;
	
	public MueveVista(double ancho, double alto){
		x=0;
		y=0;
		presionando=false;
		escala = 0.75; //0.30
		this.ancho = ancho;
		this.alto = alto;
		cllbacksClickeo = new ArrayList<CallbackPosicion>();
		cllbacksHovereo = new ArrayList<CallbackPosicion>();
	}
	public void presionado(MouseEvent e){//EVENTO
		//synchronized(lock){
		x_m_inicial=e.getX();
		y_m_inicial=e.getY();
		presionando = true;
		x_inicial = x;
		y_inicial = y;
		//}
	}
	
	public void soltado(MouseEvent e){//EVENTO
		double dis;
		//synchronized(lock){
		presionando = false;
		double difx = x_m_inicial-e.getX();
		double dify = y_m_inicial-e.getY();
		dis= Math.sqrt(difx*difx+dify*dify);//esta distancia es sin escalar nada!
		//}
		if(dis<5){
			for(CallbackPosicion c: cllbacksClickeo){
				c.execute(obtenerPosicion(e));
			}
		}
	}
	public Posicion obtenerPosicion(MouseEvent e) {
		double mxAbs = e.getX()/escala-x;
		double myAbs = e.getY()/escala-y;
		
		double posX = mxAbs/80;
		double posY = myAbs/80;
		
		return new Posicion((int)Math.floor(posX),(int)Math.floor(posY));
	}
	public void salio(MouseEvent e){//EVENTO
		//synchronized(lock){
		presionando = false;
		//}
	}
	
	public void draggeado(MouseEvent e){//EVENTO
		//synchronized(lock){
		if(presionando){
			x = (e.getX()-x_m_inicial)/escala+x_inicial;
			y = (e.getY()-y_m_inicial)/escala+y_inicial;
		}
		//}
		movido(e);
	}
	
	
	public void scrolleado(ScrollEvent e){//EVENTO
		//synchronized(lock){
			double escala_inicial = escala; 
			escala-=e.getDeltaY()*0.001;
			if(escala<0.1){
				escala=0.1;
			}
			double ancho_inicial = ancho/escala_inicial;
			double ancho_final = ancho/escala;
			
			double alto_inicial = alto/escala_inicial;
			double alto_final = alto/escala;
			
			double centro_x = x+ancho_inicial/2;
			double centro_y = y+alto_inicial/2;
			/*
			x = centro_x-ancho_final/2;
			y = centro_y-alto_final/2;
			*/
		//}
	}
	/*
	public double xPantalla(Posicion p){
		synchronized(lock){
		return (p.getX()*80+x)*escala;
		}
	}
	public double xPantalla(PosicionEnElPlano p){
		synchronized(lock){
		return (p.getX()*80+x)*escala;
		}
	}
	
	public double yPantalla(Posicion p){
		synchronized(lock){
		return (p.getY()*80+y)*escala;
		}
	}
	
	public double yPantalla(PosicionEnElPlano p){
		synchronized(lock){
		return (p.getY()*80+y)*escala;
		}
	}
	*/
	public double anchoCasillero(){
		//synchronized(lock){
		return 80*escala;
		//}
	}
	
	
	public double xPantalla(Posicion p){
		return xPantalla(p.getX());
	}
	public double xPantalla(PosicionEnElPlano p){
		return xPantalla(p.getX());
	}
	
	public double xPantalla(double xTablero){
		//synchronized(lock){
		return (xTablero*80+x)*escala;
		//}
	}
	
	public double yPantalla(double yTablero){
		//synchronized(lock){
		return (yTablero*80+y)*escala;
		//}
	}
	
	public double yPantalla(Posicion p){
		return yPantalla(p.getY());
	}
	
	public double yPantalla(PosicionEnElPlano p){
		return yPantalla(p.getY());
	}
	
	
	public double altoCasillero(){
		//synchronized(lock){
		return 80*escala;
		//}
	}
	public void agregarCallbackClickeo(CallbackPosicion call) {
		cllbacksClickeo.add(call);
	}
	
	public void agregarCallbackHover(CallbackPosicion call) {
		cllbacksHovereo.add(call);
		
	}
	public void clickeado(MouseEvent e) {//EVENTO
		// TODO Auto-generated method stub
	}
	
	public void movido(MouseEvent e) {//EVENTO
		//synchronized(lock){
			x_mouse = e.getX();
			y_mouse = e.getY();
		//}
		for(CallbackPosicion c: cllbacksHovereo){
			c.execute(obtenerPosicion(e));
		}
	}
	public boolean draggeando(){
		return presionando;
	}
	
	public double getYMouse() {
		//synchronized(lock){
			return y_mouse;
		//}
	}
	public double getXMouse() {
		//synchronized(lock){
			return x_mouse;
		//}
	}

}
