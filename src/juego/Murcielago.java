package juego;

import java.awt.Color;
import entorno.Entorno;

public class Murcielago {
	private double x;
	private double y;
	private double diam;
	private double velocidad;
	private Color color;
	private double radioMago;
	
	
	
	public Murcielago(double x, double y, int diam ) {
		this.y = y;
		this.x = x;
		this.diam = diam;
		this.color = Color.RED;
		this.velocidad = 1.5;
	}
	
	public void dibujarMurcielago(Entorno e) {
		e.dibujarCirculo(x, y, diam, color);
	}
	
	public boolean colisionaConMago(Mago mago) {
		double distX = x - mago.getX();
		double distY = y - mago.getY();
		double distancia = Math.sqrt(distX * distX + distY * distY)  ; 
		//utilizo sqrt para hacer la raiz, es decir teorema de pitagoras, asegurandome la distancia entre ambos.
		
		double radioMurcielago = diam / 2;
	    double radioMago = mago.getDiam() / 2;
	    //consigo el diametro de ambos, es decir el circulo completo, y luego lo divido por dos para conseguir el radio
	    //(distancia entre el centro y el borde)
	    
	    return distancia < (radioMurcielago + radioMago);
	    }
	    	
	
	public void seguir(Mago mago) {
		//se recibe el mago y con su posicion se compara a la del murcielago, si esta es diferencia se aumenta
		//su valor en base a la velocidad
	    double posX = mago.getX() - this.x;
	    double posY = mago.getY() - this.y;

	    double distancia = Math.sqrt(posX * posX + posY * posY); //teorema de pitagoras para calcular la distancia

	    if (distancia > 1) { // para que no tiemble si ya está muy cerca
	        this.x += (posX / distancia) * velocidad;
	        this.y += (posY / distancia) * velocidad;
	    }
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

}

