package juego;

import java.awt.Color;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Bombas {

double x;
double y;
boolean explotada = false;
double radioExplosion = 50;
int ticksDesdeExplosion = 0;
int duracionDeTicks = 120;
Image imagenExplosion;

//constructor 
public Bombas (double x, double y, double radioExplosion, int duracionDeTicks) {
this.x=x;
this.y=y;
this.explotada=true;
this.radioExplosion= radioExplosion;
this.duracionDeTicks = duracionDeTicks;
this.imagenExplosion=Herramientas.cargarImagen("explosion.png");
	}

//dibuja la explosión en el entorno
public void dibujar (Entorno entorno) {
	if (explotada && ticksDesdeExplosion < duracionDeTicks) {
		entorno.dibujarImagen(imagenExplosion, x, y, 0, 0.3);
	}
}

//verifica el estado en el que se encuentra la bomba
public boolean Exploto () {
	return explotada;
}

//compara la duracion desde que exploto hasta la duracion de la explosion
public boolean terminoDeExplotar() {
	return ticksDesdeExplosion >= duracionDeTicks;
}

//controla la duracion de la explosion
public void actualizar () {
	if (explotada) {
		ticksDesdeExplosion++;
	}
}

//verifica la poscion de la bomba
public boolean colisionaCon (Murcielago m) {
double ax = this.x - m.getX(); //si la bomba esta en la misma posicion que el murcielago en la posicion X.
double by = this.y - m.getY(); //si la bomba esta en la misma posicion que el murcielago en la posicion Y.
return Math.sqrt(ax * ax + by * by) < radioExplosion; //Aplicamos pitagoras para saber si el radio de la bomba es mernor a la poscion del murcielago
}
}
