package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

//se va a encargar de dividir las partes de la pantalla, 1/4 para los poderes y los otros 3/4 para la zona de jugabilidad
public class Pantalla {
	
	private double x;
	private double y;
	//los anchos estan asignado al reves para que no generen error
	private int anchoJuego = 200;
	private int altoJuego = 600;
	private int anchoPoderes = 600;
	private Image fondoJuego;
	private Image fondoPoderes;
	
//Constructor que me permita cargar la imagen	
	public Pantalla() {
		this.fondoJuego = Herramientas.cargarImagen("bloque.png");
		this.fondoPoderes = Herramientas.cargarImagen("bloquem.png");
		this.x = x;
		this.y = y;
	}
	
	
	public void dibujarZonas (Entorno entorno) {
		entorno.dibujarImagen(fondoPoderes, anchoPoderes, altoJuego/2, 0);
		entorno.dibujarImagen(fondoJuego, anchoJuego, altoJuego/2, 0 );
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getAnchoJuego() {
		return anchoJuego;
	}
	
	public int getAltoJuego() {
		return altoJuego;	
	}
	
	public int getAnchoPoderes() {
		return anchoPoderes;
	}
	
	
	
}
