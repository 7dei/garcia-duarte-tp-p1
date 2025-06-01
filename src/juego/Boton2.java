package juego;

import java.awt.Color;
import entorno.Herramientas;
import entorno.Entorno;
import java.awt.Image;

public class Boton2 {

private double x;
private double y;
private double ancho;
private double alto;
private boolean activo;
private double tamaño3;
private double tamaño4;
private Image imagenNormal;
private Image imagenActiva;

//constructor
public Boton2 (double x,double y) {
	this.x=x;
	this.y=y;
	this.ancho=80;
	this.alto=80;
	this.activo=false;
	this.tamaño3 = 0.45;
	this.tamaño4 = 0.45;
	this.imagenActiva= Herramientas.cargarImagen("botonbomba2.png");
	this.imagenNormal= Herramientas.cargarImagen("botonbomba2.png");
	}
	
//Dibuja el boton en el entorno
	public void dibujar(Entorno entorno) {
		if (activo) {
			entorno.dibujarImagen(imagenActiva, x, y, 0, tamaño3);
		}
		else {
			entorno.dibujarImagen(imagenNormal, x, y, 0, tamaño4);
		}
	}

//verifica que las coordenadas x e y del mouse esten sobre las coordenadas del boton y si fue presionado el boton del click izquierdo del mouse. Si esto se verifica se activa y se desactiva boton.		
public void estaSobreMousee (Entorno entorno, Boton boton) {
    if (entorno.mousePresente() && entorno.sePresionoBoton(1)) {
    	int mouseX = entorno.mouseX();
    	int mouseY = entorno.mouseY();
    	if (mouseX >= x - ancho / 2 && mouseX <= x + ancho /2 && mouseY >= y - alto / 2 && mouseY <= y + alto /2) {
    			this.activo = true;
    			boton.desactivar();
    	}
    }
}

//verifica el estado en el cual se encuentra el boton
public boolean estaActivo() {
	return this.activo;
	}

public void desactivar() {
	this.activo=false;
}
}
