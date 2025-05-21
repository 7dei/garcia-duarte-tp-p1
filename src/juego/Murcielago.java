package juego;

import java.awt.Color;
import entorno.Entorno;

public class Murcielago {
	private double x;
	private double y;
	private double diam;
	private double velocidad;
	private Color color;


	public Murcielago(double x, double y, int diam ) {
		this.y = y;
		this.x = x;
		this.diam = diam;
		this.color = Color.RED;
		this.velocidad = 2;
	}
	
	public void dibujarMurcielago(Entorno e) {
		e.dibujarCirculo(x, y, diam, color);
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

}

