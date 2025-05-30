package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Mago {
	
	private double x;
	private double y;
	private double diam;
	private int velocidad;
	private Image imagenGondolf;
	
	
// constructor	
	public Mago(double x, double y, int diam) {
		this.y = y;
		this.x = x;
		this.diam = diam;
		this.velocidad = 3;
		// agregue el getScaledInstance ya que me permite modificar la escala para que no se vea grande.
		this.imagenGondolf = Herramientas.cargarImagen("Gondolf.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
	}
	
// se encarga de aumentar o restar un valor de velocidad a la direccion, es decir el movimiento 
// cuando sea presionada la tecla.
	public void moverIzquierda(Piedra [] piedras) {
		double nuevoX = x - velocidad;
	    if (nuevoX - diam / 2 >= 15 && !colisionPiedra(nuevoX, y, piedras)) { //el !colisionpiedra lo usamos ya que si no es true el jugador puede avanzar.
	        x = nuevoX;
	    }
	}
	
	public void moverDerecha(Piedra [] piedras) {
		double nuevoX = x + velocidad;
	    if (nuevoX + diam / 2 <= 585 && !colisionPiedra(nuevoX, y, piedras)) {
	        x = nuevoX;
	    }
	}
	
	public void moverArriba(Piedra [] piedras) {
		double nuevoY = y - velocidad;
	    if (nuevoY - diam / 2 >= 20 && !colisionPiedra(x, nuevoY, piedras)) {
	        y = nuevoY;
	    }
	}
	
	public void moverAbajo(Piedra [] piedras) {
		double nuevoY = y + velocidad;
	    if (nuevoY + diam / 2 <= 585 && !colisionPiedra(x, nuevoY, piedras)) {
	        y = nuevoY;
	    }
	}
	
// se encarga de dibujar el mago ubicado en el centro de la zona jugable.
	public void dibujarMago(Entorno e) {
		e.dibujarImagen(imagenGondolf, x, y, 0);
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

