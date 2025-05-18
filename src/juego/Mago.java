package juego;

import java.awt.Color;
//import java.awt.Image;
import entorno.Entorno;
//import entorno.Herramientas;

public class Mago {
	
	private double x;
	private double y;
	private double diam;
	private int velocidad;
	private Color color;
//	private Image imagenMago;
	
	
	
	public Mago(double x, double y, int diam) {
		this.y = y;
		this.x = x;
		this.diam = diam;
		this.color = Color.BLUE;
		this.velocidad = 5;
//		this.imagenMago = Herramientas.cargarImagen("2.jpg");
	}
	
		// TODO Auto-generated constructor stub
// se encarga de aumentar o restas un velocidad a la direccion, es decir el movimiento cuando sea presionada una tecla.
	public void moverIzquierda() {
		if (x - diam/2 - velocidad >= 0) {
			x -= velocidad ;
		}
	}
	
	public void moverDerecha() {
		if (x + diam / 2 + velocidad <= 600) {
			x += velocidad;
		}
	}
	
	public void moverArriba() {
		if (y - diam/2 - velocidad >= 0) {
			y-= velocidad;
		}
	}
	
	public void moverAbajo() {
		if (y + diam/2 + velocidad <=600) {
		y+= velocidad;
		}
	}
	
// se encarga de dibujar el mago ubicado en el centro de la zona jugable.
	public void dibujarMago(Entorno e) {
		e.dibujarCirculo(x, y, diam, color);
		}
		
	}


