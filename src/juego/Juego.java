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
	private Piedra [] piedras;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	public Juego() {
		// Inicializa el objeto entorno y nombres de los grupos 
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N10 - Garcia - Duarte - Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		//asigno la pantalla a el juego
		this.pantalla = new Pantalla();
		this.mago = new Mago(300, 300, 30);
		
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
	{
		// Procesamiento de un instante de tiempo
		// ...
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
		
		for (int i = 0; i < piedras.length; i++) {
			piedras[i].dibujarPiedra(entorno);
		}
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
