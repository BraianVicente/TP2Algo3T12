package fiuba.algo3.vista.CanvasJuego;

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
	
	public MueveVista(double ancho, double alto){
		x=0;
		y=0;
		presionando=false;
		escala = 1;
		this.ancho = ancho;
		this.alto = alto;
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
	}
	
	public void salio(MouseEvent e){
		soltado(e);
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
			
			double alto_inicial = ancho*escala_inicial;
			double alto_final = ancho*escala;
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

}
