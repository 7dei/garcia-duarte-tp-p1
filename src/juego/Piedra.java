package juego;

import java.awt.Color;

import entorno.Entorno;

public class Piedra {
	private double x;
	private double y;
//	private double ancho;
//	private double alto;
	private double diametro;
	private Color color;	


	public Piedra(double x, double y, double diametro) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
//		this.ancho = ancho;
//		this.alto = alto;
		this.color = Color.GRAY; 
	}
	
	public void dibujarPiedra(Entorno e) {
		e.dibujarCirculo(x, y, diametro, color);
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}

	public double getDiam() {
		// TODO Auto-generated method stub
		return diametro;
	}
}
