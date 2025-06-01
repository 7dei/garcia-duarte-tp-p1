package juego;
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

//constructor
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

//Dibuja el boton en el entorno
	public void dibujar(Entorno entorno) {
		if (activo) {
			entorno.dibujarImagen(imagenActiva, x, y, 0, tamaño1);
		}
		else {
			entorno.dibujarImagen(imagenNormal,x,y,0, tamaño2);
		}
	}

//verifica que las coordenadas x e y del mouse esten sobre las coordenadas del boton y si fue presionado el boton del click izquierdo del mouse. Si esto se verifica se activa y se desactiva botonBomba.
public void estaSobreMouse(Entorno entorno, Boton2 botonBomba) {
    if (entorno.mousePresente() && entorno.sePresionoBoton(1)) {
    	int mouseX = entorno.mouseX();
    	int mouseY = entorno.mouseY();
    	if (mouseX >= x - ancho / 2 && mouseX <= x + ancho /2 && mouseY >= y - alto / 2 && mouseY <= y + alto /2) {
    			this.activo = true;
    			botonBomba.desactivar();
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