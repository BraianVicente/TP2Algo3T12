package fiuba.algo3.vista.CanvasJuego;

import java.util.ArrayList;

import fiuba.algo3.modelo.tablero.Posicion;
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
	
	private double x_inicial;
	private double y_inicial;
	
	private boolean presionando;
	
	private double ancho;
	private double alto;
	private ArrayList<SeleccionCallback> seleccionCallbacks;
	
	public MueveVista(double ancho, double alto){
		x=0;
		y=0;
		presionando=false;
		escala = 0.75;
		this.ancho = ancho;
		this.alto = alto;
		seleccionCallbacks = new ArrayList<SeleccionCallback>();
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
			for(SeleccionCallback c: seleccionCallbacks){
				c.seleccion(obtenerPosicion(e));
			}
		}
	}
	private Posicion obtenerPosicion(MouseEvent e) {
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

	public void movido(MouseEvent e){
		if(presionando){
			synchronized(lock){
				x = (e.getX()-x_m_inicial)/escala+x_inicial;
				y = (e.getY()-y_m_inicial)/escala+y_inicial;
			}
		}
	}
	
	public void scrolleado(ScrollEvent e){
		synchronized(lock){
			double escala_inicial = escala; 
			escala-=e.getDeltaY()*0.001;
			if(escala<0.1){
				escala=0.1;
			}
			double ancho_inicial = ancho*escala_inicial;
			double ancho_final = ancho*escala;
			
			double alto_inicial = alto*escala_inicial;
			double alto_final = alto*escala;
			//System.out.println("delta ancho: "+(ancho_final-ancho_inicial)+" el inicial: "+ancho_inicial+" el final: "+ancho_final);
			
			//x+=(ancho_final-ancho_inicial)/2;//O NO ME SALEN LAS CUENTAS O EL CENTRO NO ES 
			//y+=(alto_final-alto_inicial)/2;//EL CENTRO... EN ESPERA HASTA EL LAYOUT FINAL
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
	public void seleccionaPosicion(SeleccionCallback seleccionCallback) {
		this.seleccionCallbacks.add(seleccionCallback);
	}

}
