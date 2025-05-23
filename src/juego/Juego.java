package juego;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import entorno.Entorno;
import entorno.InterfaceJuego;

import java.util.ArrayList; //Agregamos lista de arreglos para los hechizos

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	//meto en private las clases que voy a utilizar
	private Pantalla pantalla;
	private Mago mago;
	private Piedra piedra;
	private String[] opciones = {"Jugar", "Salir"};
    private int opcionSeleccionada = 0;
    private enum estadoJuego{Menu_Principal, En_Juego}; //Creamos un private enum, que toma 2 posibles valores, Menu_Principal, En_Juego. Que luego sera utilizado para saber en que estado se encuentra el jugador.
    private estadoJuego estado = estadoJuego.Menu_Principal;
	private Piedra [] piedras;
	private ArrayList <Murcielago> murcielagos = new ArrayList<>(); //creamos una lista dinamica la cual es utilizada para contar la cantidad de murcielagos que hay en el arreglo
	private ArrayList <Hechizos> hechizos= new ArrayList<>(); //creamos una lista dinamica la cual es usada para contar todos los hechizos que fueron utilizados
	private Boton botonDisparo;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	public Juego() {
		// Inicializa el objeto entorno y nombres de los grupos
		this.entorno = new Entorno(this, "Gondolf un nuevo Mago - Grupo N10 - Garcia - Duarte - Apellido3", 800, 600);
		
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		//asigno la pantalla a el juego
		this.mago = new Mago(300, 300, 30);
		
		Point[] zonasSpawn = {
			    new Point((int) Math.random() * -50, -50),
			    new Point((int) (Math.random() * 600 + 50), -50),
			    new Point((int) Math.random() * -50, 600 + 50),
			    new Point((int) Math.random() * 600 + 50, 600 + 50)
			    
			};
			
			for (int l = 0; l < 10 ;l++) {
				Point spawn= zonasSpawn [new Random().nextInt(zonasSpawn.length)];
				murcielagos.add(new Murcielago(spawn.x,spawn.y,10));
				
 		}
		
		//arreglo de piedras donde se recorre cada piedra y se ubica en cierta posicion de x o y, segundo corresponda.
		this.piedras = new Piedra[6];
		for (int i = 0; i < piedras.length; i++) {
			double [] posX = {150, 150, 200, 400, 450, 450};
			double [] posY = {100, 500, 300, 300, 500, 100};
			piedras[i] = new Piedra(posX[i], posY[i], 60);
		}
		this.pantalla = new Pantalla();	
 
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{ if (estado == estadoJuego.Menu_Principal) { //Verificamos si el estado del juego esta en menu. Si esta en el Menu se ejecutara las lineas de codigo del menu. 
		//Fondo negro
		entorno.colorFondo(Color.BLACK);
		
		// Fuente blanca y grande
		entorno.cambiarFont("Arial bold" , 60, Color.WHITE);
		//Titulo
		entorno.escribirTexto("Menu principal",195,140);
		//Opciones

		for (int i=0; i < opciones.length; i++) {
			Color color=(i==opcionSeleccionada) ? Color.YELLOW : Color.WHITE;
			entorno.cambiarFont("Arial", 35, color);
			entorno.escribirTexto(opciones[i],360, 300 + i* 80);
		}
		if (entorno.sePresiono(entorno.TECLA_ABAJO)) {
			opcionSeleccionada= (opcionSeleccionada + 1) % opciones.length;
		}
		else if
			(entorno.sePresiono(entorno.TECLA_ARRIBA)) {
				opcionSeleccionada = (opcionSeleccionada - 1 + opciones.length) % opciones.length;
        }
			
		if (entorno.sePresiono(entorno.TECLA_ENTER)) {
			if (opcionSeleccionada == 0) {
				System.out.println("Elegiste Jugar"); //Si el jugador elige la opcion jugar, se lo mandara a otro menu donde alli podra jugar al juego.
				estado = estadoJuego.En_Juego;
			}
			else if (opcionSeleccionada == 1) {
				System.out.println("Elegiste Salir"); // si el jugador elige la opcion salir el programa se cerrara automaticamente
				System.exit(0);
			}
		}
	}
	else if (estado == estadoJuego.En_Juego) {
		entorno.dibujarRectangulo(700, 300, 200, 600, 0, Color.MAGENTA);
	    pantalla.dibujarPantalla(entorno);
	    mago.dibujarMago(entorno);

	    // Dibujar los murciélagos
	    for (Murcielago m : murcielagos) {
	        m.dibujarMurcielago(entorno);
	        m.seguir(mago);
	    }

	    // Dibujar hechizos
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
	        for (int j = 0; j < murcielagos.size(); j++) {
	            Murcielago m = murcielagos.get(j);
	            if (h.colisionaCon(m)) {
	                murcielagos.remove(j);
	                hechizos.remove(k);
	                k--;
	                break;
	            }
	        }
	    }

	    for (int i = 0; i < piedras.length; i++) {
	        piedras[i].dibujarPiedra(entorno);
	    }

	    // Movimiento del mago
	    if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a')) { // cuando presionamos la flecha izquierda o la letra a, el mago se mueve para la izquierda
	        mago.moverIzquierda(piedras);
	    }
	    if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d')) { // cuando presionamos la flecha derecha o la letra d, el mago se mueve para la derecha
	        mago.moverDerecha(piedras);
	    }
	    if (entorno.estaPresionada(entorno.TECLA_ARRIBA) || entorno.estaPresionada('w')) { // cuando presionamos la flecha arriba o la letra w, el mago se mueve para arriba
	        mago.moverArriba(piedras);
	    }
	    if (entorno.estaPresionada(entorno.TECLA_ABAJO) || entorno.estaPresionada('s')) { // cuando presionamos la flecha abajo o la letra s, el mago se mueve para abajo
	        mago.moverAbajo(piedras);
	    }
	    if (entorno.estaPresionada(entorno.TECLA_ESCAPE)) { // cuando presionamos tecla escape, salimos del juego y se cierra el programa
	        System.exit(0);
	    }
	}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
