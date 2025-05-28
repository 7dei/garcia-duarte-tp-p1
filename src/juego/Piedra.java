package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Piedra {
	private double x;
	private double y;
	private Image imagenPiedra;
	private double diametro;


	public Piedra(double x, double y, double diametro) {
		this.x = x;
		this.y = y;
		this.diametro = diametro;
		this.imagenPiedra = Herramientas.cargarImagen("Piedra.png").getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	}
	
	public void dibujarPiedra(Entorno e) {
		e.dibujarImagen(imagenPiedra, x, y, 0);
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
