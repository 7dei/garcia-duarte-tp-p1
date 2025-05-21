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
	private double anchoJuego = 600;
	private double altoJuego = 600;
	private double anchoPoderes = 200;
	private Image fondoJuego;
	private Image fondoPoderes;
	
//Constructor que me permita cargar la imagen	
	public Pantalla() {
		this.fondoJuego = Herramientas.cargarImagen("bloque.png");
		this.fondoPoderes = Herramientas.cargarImagen("bloquem.png");
	}
	
	
	public void dibujarPantalla(Entorno entorno) {
	    // Zona jugable (3/4 izq) → centro en 600 / 2 = 300
	    entorno.dibujarImagen(fondoJuego, anchoJuego / 2, altoJuego / 2, 0);
	}

	public void dibujarPoderes(Entorno entorno) {
	    // Zona de menú (1/4 der) → centro en 600 + (200 / 2) = 700
	    entorno.dibujarImagen(fondoPoderes, anchoJuego + (anchoPoderes*2), altoJuego / 2, 0);
	}

	
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getAnchoJuego() {
		return anchoJuego;
	}
	
	public double getAltoJuego() {
		return altoJuego;	
	}
	
	public double getAnchoPoderes() {
		return anchoPoderes;
	}
	
	
	
}
