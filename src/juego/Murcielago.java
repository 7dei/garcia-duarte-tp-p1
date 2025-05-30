package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Image;
import java.awt.Point;

public class Murcielago {
	private double x;
	private double y;
	private double diam;
	private double velocidad;;
	private Image imagenMurcielago;
	
	
//constructor 	
	public Murcielago(double x, double y, int diam ) {
		this.y = y;
		this.x = x;
		this.diam = diam;
		this.velocidad = 1.5;
		this.imagenMurcielago = Herramientas.cargarImagen("Murcielago.png").getScaledInstance(60, 60, Image.SCALE_SMOOTH);
	}


// Se responsabiliza de dibujar al murcielago en pantalla a traves de una imagen.
	public void dibujarMurcielago(Entorno e) {
		e.dibujarImagen(imagenMurcielago, x, y, 0);
	}
	

// Este metodo se encarga de indicarle al murcielago que siga al mago. Se recibe el mago y con su 
// posicion se compara a la del murcielago, si esta es diferencia es mayor a 1
// se aumenta su valor en base a la velocidad hasta que iguale a la pos x e y del mago.
	public void seguir(Mago mago) {
		double posX = mago.getX() - this.x;
		double posY = mago.getY() - this.y;

		double distancia = Math.sqrt(posX * posX + posY * posY); //teorema de pitagoras para calcular la distancia

		if (distancia > 1) { // para que no tiemble si ya está muy cerca
		    this.x += (posX / distancia) * velocidad;
		    this.y += (posY / distancia) * velocidad;
		    }
		}	
	
	
// Esta funcion se ocupa de chequear si el murcielago colisiona con el mago. Lo hace a traves de comparar
// analizar la distancia obtenida por la posicion del mago y del murcielago. Si la distancia es menor al 
// radio de ambos elementos es que existe una colision.
	public boolean colisionaConMago(Mago mago) {
		double distX = x - mago.getX();
		double distY = y - mago.getY();
		double distancia = Math.sqrt(distX * distX + distY * distY)  ; 
		//utilizo sqrt para hacer la raiz, es decir teorema de pitagoras, asegurandome la distancia entre ambos.
		
		double radioMurcielago = diam / 2;
	    double radioMago = mago.getDiam() / 2;
	    //consigo el diametro de ambos, es decir el circulo completo, y luego lo divido por dos para conseguir el radio
	    //(distancia entre el centro y el borde)
	    
	    return distancia < (radioMurcielago + radioMago);
	    }
	    	
		
// este metodo se encarga de crear de a 10 murcielagos en las posiciones indicadas (donde los puntos indiquen)
// le asigna una coordenada a cada uno y se ejecuta hasta que se llegue al parametro de murcielagos creados lle
// gue al tope, en este caso, 50. 
	public static void crearMurcielagos(Murcielago[] arreglo, int murcielagosCreados) {
		Point[] zonasSpawn = new Point[] {
			new Point(-50, 0),
			new Point(-50, 400),
			new Point(150, -50),
			new Point(450, -50),
			new Point(700, -50),
			new Point(150, 650),
			new Point(450, 650),
			new Point(700, 650),
			new Point(750, 200),
			new Point(750, 550)
		};

		for (int i = 0; i < 10 && murcielagosCreados + i < arreglo.length; i++) {
			Point spawn = zonasSpawn[(murcielagosCreados + i) % zonasSpawn.length];
			arreglo[murcielagosCreados + i] = new Murcielago(spawn.x, spawn.y, 20);
		}
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

}

