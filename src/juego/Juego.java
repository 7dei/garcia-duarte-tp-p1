package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

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
	private Murcielago [] murcielagos;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	public Juego() {
		// Inicializa el objeto entorno y nombres de los grupos
		this.entorno = new Entorno(this, "Gondolf un nuevo Mago - Grupo N10 - Garcia - Duarte - Apellido3", 800, 600);
		
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		//asigno la pantalla a el juego
		this.pantalla = new Pantalla();
		this.mago = new Mago(300, 300, 30);
		
		this.murcielagos = new Murcielago[10];
		for (int i = 0; i<murcielagos.length; i++) {
			double posX = Math.random() * 600;
			double posY = Math.random() * 600;
			murcielagos[i] = new Murcielago(posX, posY, 10);
 		}
		
		//arreglo de piedras donde se recorre cada piedra y se ubica en cierta posicion de x o y, segundo corresponda.
		this.piedras = new Piedra[6];
		for (int i = 0; i < piedras.length; i++) {
			double [] posX = {150, 150, 200, 400, 450, 450};
			double [] posY = {100, 500, 300, 300, 500, 100};
			piedras[i] = new Piedra(posX[i], posY[i], 60);
		}
		

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
		entorno.cambiarFont("Arial" , 30, Color.WHITE);
		//Titulo
		entorno.escribirTexto("Menu principal",200,100);
		//Opciones

		for (int i=0; i < opciones.length; i++) {
			Color color=(i==opcionSeleccionada) ? Color.YELLOW : Color.WHITE;
			entorno.cambiarFont("Arial", 25, color);
			entorno.escribirTexto(opciones[i],250, 180 + i* 40);
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
		else if (estado == estadoJuego.En_Juego) { //Una vez que confirmamos que el estado de juego sea En_Juego, el jugador podra jugar al videojuego.
			pantalla.dibujarZonas(entorno);
		
			mago.dibujarMago(entorno);
//se agregaron funciones que detecten si la tecla presionada es correcta se dirigue para la direccion indicada.		
		if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
			mago.moverIzquierda();
		}
		if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
			mago.moverDerecha();
		}
		if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
			mago.moverArriba();
		}
		if (entorno.estaPresionada(entorno.TECLA_ABAJO)) {
			mago.moverAbajo();
		}
		if (entorno.estaPresionada(entorno.TECLA_ESCAPE)) { 
			System.exit(0); // Si el jugador toca el escape mientras esta jugando, se cerrara el programa
		}
		
		for (int i = 0; i < piedras.length; i++) {
			piedras[i].dibujarPiedra(entorno);
		}
		for (Murcielago m : murcielagos) {
			m.dibujarMurcielago(entorno);
			m.seguir(mago);
		}
		
		
	}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
