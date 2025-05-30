package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Piedra {
	private double x;
	private double y;
	private Image imagenPiedra;
	private double diametro;

// constructor del objeto Piedra
	public Piedra(double x, double y, double diametro) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		this.imagenPiedra = Herramientas.cargarImagen("Piedra.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	}

// se encarga de dibujar con forma de imagen al objeto piedra
	public void dibujarPiedra(Entorno e) {
		e.dibujarImagen(imagenPiedra, x, y, 0);
	}

// este metodo se encarga de crear un arreglo de piedras y tras recorrer el arreglo
// establece a cada una de las piedras en su respectiva posicion.
	public static Piedra[] crearPiedras() {
		double[] posX = {150, 150, 200, 400, 450, 450};
		double[] posY = {100, 500, 300, 300, 500, 100};
		Piedra[] piedras = new Piedra[posX.length];
		for (int i = 0; i < piedras.length; i++) {
			piedras[i] = new Piedra(posX[i], posY[i], 80);
		}
		return piedras;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}

	public double getDiam() {
		return diametro;
	}
}
