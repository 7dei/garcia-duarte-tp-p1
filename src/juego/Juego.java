package juego;

import java.awt.Color;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	//meto en private las clases que voy a utilizar
	private Pantalla pantalla;
	
	// Variables y métodos propios de cada grupo
	// ...
	
	public Juego() {
		// Inicializa el objeto entorno y nombres de los grupos 
		this.entorno = new Entorno(this, "Titulo de TP - Grupo N10 - Garcia - Duarte - Apellido3", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		
		//asigno la pantalla a el juego
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
	{
		// Procesamiento de un instante de tiempo
		// ...
		pantalla.dibujarZonas(entorno);
		
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
