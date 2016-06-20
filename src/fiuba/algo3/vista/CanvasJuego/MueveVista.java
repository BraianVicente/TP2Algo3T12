package fiuba.algo3.vista.CanvasJuego;

import java.util.ArrayList;

import fiuba.algo3.modelo.tablero.Posicion;
import fiuba.algo3.modelo.tablero.PosicionEnElPlano;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

public class MueveVista {
	//recurso compartido
	private volatile double x;
	private volatile double y;
	private volatile double escala;
	
	private Object lock = new Object();
	
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
	public void presionado(MouseEvent e){
		x_m_inicial=e.getX();
		y_m_inicial=e.getY();
		presionando = true;
		x_inicial = x;
		y_inicial = y;
	}
	
	public void soltado(MouseEvent e){
		presionando = false;
		double difx = x_m_inicial-e.getX();
		double dify = y_m_inicial-e.getY();
		double dis = Math.sqrt(difx*difx+dify*dify);//esta distancia es sin escalar nada!
		if(dis<5){
			for(CallbackPosicion c: cllbacksClickeo){
				c.execute(obtenerPosicion(e));
			}
		}
	}
	public Posicion obtenerPosicion(MouseEvent e) {
		double escala = getEscala();
		double mxAbs = e.getX()/escala-getX();
		double myAbs = e.getY()/escala-getY();
		
		double posX = mxAbs/80;
		double posY = myAbs/80;
		
		return new Posicion((int)Math.floor(posX),(int)Math.floor(posY));
	}
	public void salio(MouseEvent e){
		presionando = false;
	}
	
	public void draggeado(MouseEvent e){
		if(presionando){
			synchronized(lock){
				x = (e.getX()-x_m_inicial)/escala+x_inicial;
				y = (e.getY()-y_m_inicial)/escala+y_inicial;
			}
		}
		movido(e);
	}
	
	
	public void scrolleado(ScrollEvent e){
		synchronized(lock){
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
		}
	}
	
	public double getX(){
		synchronized(lock){
			return x;
		}
	}
	
	public double getY(){
		synchronized(lock){
			return y;
		}
	}
	
	public double getEscala(){
		synchronized(lock){
			return escala;
		}
	}
	
	public double xPantalla(Posicion p){
		return (p.getX()*80+getX())*getEscala();
	}
	public double xPantalla(PosicionEnElPlano p){
		return (p.getX()*80+getX())*getEscala();
	}
	
	public double yPantalla(Posicion p){
		return (p.getY()*80+getY())*getEscala();
	}
	
	public double yPantalla(PosicionEnElPlano p){
		return (p.getY()*80+getY())*getEscala();
	}
	
	public double anchoCasillero(){
		return 80*getEscala();
	}
	
	public double altoCasillero(){
		return 80*getEscala();
	}
	public void agregarCallbackClickeo(CallbackPosicion call) {
		cllbacksClickeo.add(call);
		
	}
	public void agregarCallbackHover(CallbackPosicion call) {
		cllbacksHovereo.add(call);
		
	}
	public void clickeado(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	public void movido(MouseEvent e) {
		synchronized(lock){
			x_mouse = e.getX();
			y_mouse = e.getY();
			for(CallbackPosicion c: cllbacksHovereo){
				c.execute(obtenerPosicion(e));
			}
		}
	}
	public boolean draggeando(){
		return presionando;
	}
	
	public double getYMouse() {
		synchronized(lock){
			return y_mouse;
		}
	}
	public double getXMouse() {
		synchronized(lock){
			return x_mouse;
		}
	}

}
