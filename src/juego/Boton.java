package juego;
import java.awt.Color;
import entorno.Herramientas;
import entorno.Entorno;
import java.awt.Image;

public class Boton {
private double x;
private double y;
private double ancho;
private double alto;
private boolean activo;
private double tamaño1;
private double tamaño2;
private Image imagenActiva;
private Image imagenNormal;


public Boton (double x,double y) {
	this.x=x;
	this.y=y;
	this.ancho=80;
	this.alto=80;
	this.activo=false;
	this.tamaño1 = 0.255;
	this.tamaño2=0.1;
	this.imagenActiva= Herramientas.cargarImagen("hechizo1.png");
	this.imagenNormal= Herramientas.cargarImagen("hechizo2.png");
}

public void dibujar(Entorno entorno) {
	if (activo) {
		entorno.dibujarImagen(imagenActiva, x, y, 0, tamaño1);
	}
	else {
		entorno.dibujarImagen(imagenNormal,x,y,0, tamaño2);
	}
}

public void estaSobreMouse(Entorno entorno) { //esta funcion verifica si el mouse esta dentro del area del boton
    if (entorno.mousePresente() && entorno.sePresionoBoton(1)) {
    	int mouseX = entorno.mouseX();
    	int mouseY = entorno.mouseY();
    	if (mouseX >= x - ancho / 2 && mouseX <= x + ancho /2 && mouseY >= y - alto / 2 && mouseY <= y + alto /2) {
    			this.activo = true;
    	}
    }
}


public boolean estaActivo() {
	return this.activo;
	}

public void desactivar() {
	this.activo=false;
}
}