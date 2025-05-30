package juego;


import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Hechizos {
	double x;
	double y;
	double velocidad = 5;
	private int diam;
	double angulo; //Pasamos los parametros de la clase Hechizos
	private Image imagenPoder;


public Hechizos (double x, double y, double angulo, int diam) {
	this.x=x;
	this.y=y;
	this.angulo = angulo;
	this.diam = diam;
	this.imagenPoder = Herramientas.cargarImagen("Poder.png").getScaledInstance(20, 20, 0);
	
}
public void mover() {
	x+= Math.cos(angulo) * velocidad; //velocidad en la que se mueve el mouse desde la coordenada x
	y+= Math.sin(angulo) * velocidad; //velocidad en la que se mueve el mouse desde la coordenada y
}
public void dibujar (Entorno entorno) {
	entorno.dibujarImagen(imagenPoder, x, y, angulo, 2); //dibujo del hechizo
}
public boolean colisionaCon (Murcielago m) { //devuelve true o false
	double dx = this.x - m.getX(); //si el hechizo esta en la misma posicion que el murcielago en la posicion X.
	double dy = this.y - m.getY(); //si el hechizo esta en la misma posicion que el murcielago en la posicion Y.
	return Math.sqrt(dx * dx + dy * dy) < 15; //Aplicamos pitagoras para saber si el hechizo colisiono con el murcielago
}
}
