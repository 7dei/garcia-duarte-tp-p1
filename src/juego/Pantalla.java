package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

//se va a encargar de dividir las partes de la pantalla, 1/4 para los poderes y los otros 3/4 para la zona de jugabilidad
public class Pantalla {
	//los anchos estan asignado al reves para que no generen error
	private double anchoJuego = 600;
	private double altoJuego = 600;
	private double anchoPoderes = 200;
	private Image fondoJuego;
	private Image fondoPoderes;
	
//Constructor que me permita cargar la imagen	
	public Pantalla() {
		this.fondoJuego = Herramientas.cargarImagen("Fondo.png");
		this.fondoPoderes = Herramientas.cargarImagen("PantallaPoder.png");
	}
	
	
	public void dibujarPantalla(Entorno entorno) {
	    // Zona jugable (3/4 izq) → centro en 600 / 2 = 300
	    entorno.dibujarImagen(fondoJuego, anchoJuego / 2, altoJuego / 2, 0);
	}

	public void dibujarPoderes(Entorno entorno) {
	    // Zona de menú (1/4 der) → centro en 600 + (200 / 2) = 700
	    entorno.dibujarImagen(fondoPoderes, anchoJuego + (anchoPoderes * 1.5), altoJuego / 2, 0);
	}
	
	
	public void dibujarDerrota(Entorno entorno) {
		entorno.dibujarRectangulo(350, 300, 900, 600, 0, Color.BLACK);
		entorno.cambiarFont("Arial bold", 60, Color.RED);
		entorno.escribirTexto("PERDISTE", 240, 250);
		entorno.cambiarFont("Arial", 30, Color.WHITE);
		entorno.escribirTexto("Gondolf fue eliminado por los murcielagos :(", 110, 340);
	}
	
	public void dibujarVictoria(Entorno entorno) {
		entorno.dibujarRectangulo(350, 300, 900, 600, 0, Color.BLACK);
		entorno.cambiarFont("Arial bold", 60, Color.GREEN);
		entorno.escribirTexto("GANASTE", 240, 250);
		entorno.cambiarFont("Arial", 30, Color.WHITE);
		entorno.escribirTexto("Gondolf elimino a todos los murcielagos!", 140, 340);
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
