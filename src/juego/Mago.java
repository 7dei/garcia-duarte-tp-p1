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
	private int vida;
//	private Image imagenMago;
	
	
	
	public Mago(double x, double y, int diam) {
		this.y = y;
		this.x = x;
		this.diam = diam;
		this.color = Color.CYAN;
		this.velocidad = 3;
		this.vida = 100;
//		this.imagenMago = Herramientas.cargarImagen("2.jpg");
	}
	
		// TODO Auto-generated constructor stub
// se encarga de aumentar o restas un velocidad a la direccion, es decir el movimiento cuando sea presionada una tecla.
	public void moverIzquierda(Piedra [] piedras) {
		double nuevoX = x - velocidad;
	    if (nuevoX - diam / 2 >= 0 && !colisionPiedra(nuevoX, y, piedras)) { //el !colisionpiedra lo usamos ya que si no es true el jugador puede avanzar.
	        x = nuevoX;
	    }
	}
	
	public void moverDerecha(Piedra [] piedras) {
		double nuevoX = x + velocidad;
	    if (nuevoX + diam / 2 <= 600 && !colisionPiedra(nuevoX, y, piedras)) {
	        x = nuevoX;
	    }
	}
	
	public void moverArriba(Piedra [] piedras) {
		double nuevoY = y - velocidad;
	    if (nuevoY - diam / 2 >= 0 && !colisionPiedra(x, nuevoY, piedras)) {
	        y = nuevoY;
	    }
	}
	
	public void moverAbajo(Piedra [] piedras) {
		double nuevoY = y + velocidad;
	    if (nuevoY + diam / 2 <= 600 && !colisionPiedra(x, nuevoY, piedras)) {
	        y = nuevoY;
	    }
	}
	
// se encarga de dibujar el mago ubicado en el centro de la zona jugable.
	public void dibujarMago(Entorno e) {
		e.dibujarCirculo(x, y, diam, color);
		}
	
// se encarga de verificar si la distancia entre el radio del mago y el radio de la piedra es minima, si esto
// sucede devuelve true, creando que el usuario no pueda avanzar hacia esa direccion.
	public boolean colisionPiedra(double nuevoX, double nuevoY, Piedra[] piedras) {
	    for (Piedra p : piedras) {
	        double distancia = Math.sqrt(Math.pow(nuevoX - p.getX(), 2) + Math.pow(nuevoY - p.getY(), 2));
	        double distanciaMinima = (this.diam / 2) + (p.getDiam() / 2);
	        if (distancia < distanciaMinima) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getDiam() {
		return this.diam; 
	}
	
}

