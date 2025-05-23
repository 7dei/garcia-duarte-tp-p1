package juego;

import java.awt.Color;

import entorno.Entorno;

public class Hechizos {
	double x;
	double y;
	double velocidad = 5;
	double angulo; //Pasamos los parametros de la clase Hechizos


public Hechizos (double x, double y, double angulo) {
	this.x=x;
	this.y=y;
	this.angulo = angulo;
}
public void mover() {
	x+= Math.cos(angulo) * velocidad; //velocidad en la que se mueve el mouse desde la coordenada x
	y+= Math.sin(angulo) * velocidad; //velocidad en la que se mueve el mouse desde la coordenada y
}
public void dibujar (Entorno entorno) {
	entorno.dibujarCirculo(x, y, 10, Color.GREEN); //dibujo del hechizo
}
public boolean colisionaCon (Murcielago m) { //devuelve true o false
	double dx = this.x - m.getX(); //si el hechizo esta en la misma posicion que el murcielago en la posicion X.
	double dy = this.y - m.getY(); //si el hechizo esta en la misma posicion que el murcielago en la posicion Y.
	return Math.sqrt(dx * dx + dy * dy) < 10; //Aplicamos pitagoras para saber si el hechizo colisiono con el murcielago. 10 es la distancia maxima que puede recorrer el hechizo
}
}
