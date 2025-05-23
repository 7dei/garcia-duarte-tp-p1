package juego;

import java.awt.Color;
import java.awt.Point;

import java.util.ArrayList;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	//meto en private las clases que voy a utilizar
	
	private Pantalla pantalla;
	private Mago mago;
	private Piedra[] piedras;
	private Murcielago[] murcielagos;
	private int murcielagosVivos = 0;
	private int murcielagosCreados = 0;
	
	private ArrayList <Hechizos> hechizos= new ArrayList<>(); //creamos una lista dinamica la cual es usada para contar todos los hechizos que fueron utilizados
	private Boton botonDisparo;
	
	
	private String[] opciones = {"Jugar", "Salir"};
    private int opcionSeleccionada = 0;
    private enum estadoJuego {Menu_Principal, En_Juego};
    private estadoJuego estado = estadoJuego.Menu_Principal;


	public Juego() {
		this.entorno = new Entorno(this, "Gondolf un nuevo Mago - Grupo N10 - Garcia - Duarte - Apellido3", 800, 600);
		
		this.mago = new Mago(300, 300, 30);
		this.murcielagos = new Murcielago[50];

		// arreglo de piedras
		this.piedras = new Piedra[6];
		for (int i = 0; i < piedras.length; i++) {
			double[] posX = {150, 150, 200, 400, 450, 450};
			double[] posY = {100, 500, 300, 300, 500, 100};
			piedras[i] = new Piedra(posX[i], posY[i], 60);
		}
		this.pantalla = new Pantalla();	

		this.entorno.iniciar();
	}
//este metodo se encarga de crear de a 10 murcielagos nuevos y agregarlos al arreglo murcielagos,
//como su limite es 50, si el contador de murcielagos creados llega a 50 termina (Ya que el largo de murcielagos es 50)
	public void crearMurcielagos() {
		Point[] zonasSpawn = new Point[] {
			new Point(-50, 0), // izquierda arriba
			new Point(-50, 400), //izquierda abajo
			new Point(150, -50), //arriba izquierda
			new Point(450, -50), //arriba izquierda
			new Point(700, -50), // arriba derecha
			new Point(150, 650), // abajo izquierda
			new Point(450, 650), // abajo medio
			new Point(700, 650), // abajo derecha
			new Point(750, 200), //derecha arriba
			new Point(750, 550) //derecha abajo
		};

		// crear hasta 10 murcielagos mas sin superar 50
		for (int i = 0; i < 10 && murcielagosCreados < murcielagos.length; i++) {
			// utilizo modulo en el indice para que recorra cada punto segun la cantidad de murcielagos creados.
			// por ejemplo [2 % 10 = 2], lo que me generaria el segundo punto, hasta llegar a 10.
			Point spawn = zonasSpawn[murcielagosCreados % zonasSpawn.length];
			murcielagos[murcielagosCreados] = new Murcielago(spawn.x, spawn.y, 10);
			murcielagosCreados++;
			murcielagosVivos++;
		}
	}

	public void tick() {
		if (estado == estadoJuego.Menu_Principal) {
			entorno.colorFondo(Color.BLACK);
			entorno.cambiarFont("Arial bold", 60, Color.WHITE);
			entorno.escribirTexto("Menu principal", 195, 140);
			
			for (int i = 0; i < opciones.length; i++) {
				Color color = (i == opcionSeleccionada) ? Color.YELLOW : Color.WHITE;
				entorno.cambiarFont("Arial", 35, color);
				entorno.escribirTexto(opciones[i], 360, 300 + i * 80);
			}

			if (entorno.sePresiono(entorno.TECLA_ABAJO)) {
				opcionSeleccionada = (opcionSeleccionada + 1) % opciones.length;
			} else if (entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				opcionSeleccionada = (opcionSeleccionada - 1 + opciones.length) % opciones.length;
			}

			if (entorno.sePresiono(entorno.TECLA_ENTER)) {
				if (opcionSeleccionada == 0) {
					System.out.println("Elegiste Jugar");
					estado = estadoJuego.En_Juego; 
					crearMurcielagos();
				} else if (opcionSeleccionada == 1) {
					System.out.println("Elegiste Salir");
					System.exit(0);
				}
			}

		} else if (estado == estadoJuego.En_Juego) {
			
//lo agregue para que cree 10 murcielagos mientras no haya murcielagos vivos, y 
//el contador general de murcielagos creados hasta ahora sea menor a 50 (El largo de murcielagos pedido para ganar)			
			
			if (murcielagosVivos == 0 && murcielagosCreados < murcielagos.length) { 
				crearMurcielagos();
			}
//condicion que en caso de ser true, indica que el jugador cumplio los objetivos, es decir, gano.
// (Por ahora es provisional esto, mas adelante deberia de imprimir un menu que indique la victoria)
			
			if (murcielagosVivos == 0 && murcielagosCreados == murcielagos.length) {
				System.out.println("GANASTE!");
				System.exit(0);
			}
			
			
			pantalla.dibujarPantalla(entorno);
			mago.dibujarMago(entorno);

			for (Murcielago m : murcielagos) {
				if (m != null) {
					m.dibujarMurcielago(entorno);
					m.seguir(mago);
				}
			}

			pantalla.dibujarPoderes(entorno);
			
			if (entorno.mousePresente() && entorno.sePresionoBoton(1)) {
		        double dx = entorno.mouseX() - mago.getX();
		        double dy = entorno.mouseY() - mago.getY();
		        double angulo = Math.atan2(dy, dx);
		        hechizos.add(new Hechizos(mago.getX(), mago.getY(), angulo));
		    }

			for (int k = 0; k < hechizos.size(); k++) {
			    Hechizos h = hechizos.get(k);
			    h.mover();
			    h.dibujar(entorno);

			    for (int j = 0; j < murcielagos.length; j++) {
			        if (murcielagos[j] != null && h.colisionaCon(murcielagos[j])) {
			            // Eliminar murciélago (setear en null)
			            murcielagos[j] = murcielagos[murcielagosVivos - 1];
			            murcielagos[murcielagosVivos - 1] = null;
			            murcielagosVivos--;

			            // Eliminar hechizo
			            hechizos.remove(k);
			            k--; // retroceder índice porque eliminaste un hechizo
			            break;
			         
			        }
			    }
			}
			
	    
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				mago.moverIzquierda(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				mago.moverDerecha(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				mago.moverArriba(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
				mago.moverAbajo(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_ESCAPE)) {
				System.exit(0);
			}

			for (int i = 0; i < piedras.length; i++) {
				piedras[i].dibujarPiedra(entorno);
			}
		}
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
