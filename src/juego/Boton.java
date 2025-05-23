package juego;
import java.awt.Color;
import entorno.Entorno;

public class Boton {
private double x;
private double y;
private double ancho;
private double alto;
private Color colorFondo;
private Color colorTexto;
private String texto;


public Boton (double x,double y,double ancho,double alto, Color colorFondo, Color colorTexto, String texto) {
	this.x=x;
	this.y=y;
	this.ancho=ancho;
	this.alto=alto;
	this.texto=texto;
	this.colorFondo=Color.BLUE;
	this.colorTexto=Color.WHITE;
}

public void dibujar(Entorno entorno) {
	entorno.dibujarRectangulo(x + ancho / 2, y + alto / 2, ancho, alto, 0, colorFondo); //dibuja un rectangulo en la posicion x e y con el tamaño del ancho y del alto
	entorno.cambiarFont("Arial", 20, colorTexto);
	entorno.escribirTexto(texto, x+10, y + 25);
}

private boolean estaSobreMouse(int mouseX, int mouseY) { //esta funcion verifica si el mouse esta dentro del area del boton
    return mouseX >= x - ancho / 2 && mouseX <= x + ancho / 2 && mouseY >= y - alto / 2 && mouseY <= y + alto / 2; //compara la posicion del mouse con los limites del boton
}

public boolean fueClikeado(Entorno entorno) { //esta funcion detecta si fue apretado
	return entorno.mousePresente() && estaSobreMouse(entorno.mouseX() , entorno.mouseY()) && entorno.sePresionoBoton(1); //verifica si el puntero del mouse esta sobre el boton,
	//y detecta si el boton izquierdo del mouse fue presionado
	}
}