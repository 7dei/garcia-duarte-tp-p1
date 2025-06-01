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

//constructor
public Hechizos (double x, double y, double angulo, int diam) {
	this.x=x;
	this.y=y;
	this.angulo = angulo;
	this.diam = diam;
	this.imagenPoder = Herramientas.cargarImagen("Poder.png").getScaledInstance(20, 20, 0);
	
}

//mueve el hechizo en la cordenada x e y.
public void mover() {
	x+= Math.cos(angulo) * velocidad;
	y+= Math.sin(angulo) * velocidad;
}

//dibujo del hechizo
public void dibujar (Entorno entorno) {
	entorno.dibujarImagen(imagenPoder, x, y, angulo, 2);
}

//verifica la posicion x e y entre el hechizo y el murcielago
public boolean colisionaCon (Murcielago m) {
	double dx = this.x - m.getX();
	double dy = this.y - m.getY();
	return Math.sqrt(dx * dx + dy * dy) < 15; //Aplicamos pitagoras para saber si el hechizo colisiono con el murcielago
}
}
