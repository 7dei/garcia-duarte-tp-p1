package juego;

import java.awt.Color;
import java.util.ArrayList;
import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	//meto en private las clases que voy a utilizar
	private Pantalla pantalla;
	private Mago mago;
	//arreglos
	private Piedra[] piedras;
	private Murcielago[] murcielagos;

	//parametros de instancia
	private int murcielagosVivos = 0;
	private int murcielagosCreados = 0;
	private int murcielagosEliminados = 0;
	private int vidaMago = 100;
	private int manaMago = 100;
	private int contadorTicks = 0;

	
	private ArrayList <Hechizos> hechizos= new ArrayList<>(); //creamos una lista dinamica la cual es usada para contar todos los hechizos que fueron utilizados
	private Boton boton;
	private String[] opciones = {"Jugar", "Salir"};
    private int opcionSeleccionada = 0;
    private enum estadoJuego {Menu_Principal, En_Juego};
    private estadoJuego estado = estadoJuego.Menu_Principal;


	public Juego() {
		this.entorno = new Entorno(this, "Gondolf un nuevo Mago - Grupo N10 - Garcia - Duarte - Apellido3", 800, 600);
		
	// objetos
		this.mago = new Mago(300, 300, 10);
		this.murcielagos = new Murcielago[50];
		this.boton = new Boton(700, 360);
		this.pantalla = new Pantalla();	
		// arreglo de piedras
		this.piedras = Piedra.crearPiedras();

		this.entorno.iniciar();
	}
	
//este metodo se encarga de mostrar la enemigos eliminado, y la vida y mana restante del mago en el lado 
//derecho de la pantalla.

	public void mostrarVida() {
		entorno.cambiarFont("Arial bold", 35, Color.white);
		entorno.escribirTexto("Vida: " + vidaMago, 624, 90);
		entorno.dibujarRectangulo(700, 120, vidaMago * 1.8, 20, 0, Color.RED);
	}
	public void mostrarMana() {
		entorno.cambiarFont("Arial bold", 35, Color.WHITE);
		entorno.escribirTexto("Mana: " + manaMago, 624, 220);
		entorno.dibujarRectangulo(700, 240, manaMago * 1.8, 20, 0, Color.MAGENTA);
	}
	
	public void mostrarEliminados() {
		entorno.cambiarFont("Arial bold", 25, Color.yellow);
		entorno.escribirTexto("Eliminados: " + murcielagosEliminados, 612, 590);
	}

	
	

//este metodo se encarga de crear de a 10 murcielagos nuevos y agregarlos al arreglo murcielagos,
//como su limite es 50, si el contador de murcielagos creados llega a 50 termina (Ya que el largo de murcielagos es 50)
	
	public void crearMurcielagos() {
		Murcielago.crearMurcielagos(murcielagos, murcielagosCreados);
		for (int i = 0; i < 10 && murcielagosCreados < murcielagos.length; i++) {
			murcielagosCreados++;
			murcielagosVivos++;
		}
	}
	

	public void tick() {

		contadorTicks++;	
		
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
		}
		else if (estado == estadoJuego.En_Juego) {
			if (contadorTicks % 5 == 0 && manaMago<100) { //cada cierta cantidad de tiempo se regenera 1 de mana
				manaMago++;
		}
			
//Se encarga de crear 10 murcielagos mientras no haya murcielagos vivos, y 
//el contador general de murcielagos creados sea menor a 50 (El largo de murcielagos pedido para ganar)			
			
			if (murcielagosVivos == 0 && murcielagosCreados < murcielagos.length) { 
				crearMurcielagos();
			}

// dibuja la pantalla jugable
			pantalla.dibujarPantalla(entorno);


// esta seccion dibuja los objetos, como el mago o las piedras, excepto los murcielagos que 
// fueron dibujados antes para que tarden mucho en spawnear debido a que aparecen fuera del mapa.
				
			// recorre el arreglo de piedras, donde spawnea las 6 en el mapa			
			for (int i = 0; i < piedras.length; i++) {
				piedras[i].dibujarPiedra(entorno);
			}

			// dibuja el mago
			mago.dibujarMago(entorno);		
		
			//se encarga de crear cada murcielago, dibujar e indicarle que debe seguir al mago.
			// ademas si el murcielago (m) colisiona con el mago lo convierte en null y le baja vida al mago.
			for (int i = 0; i < murcielagos.length; i++) {
				Murcielago m = murcielagos[i];
				if (m != null) {
					m.dibujarMurcielago(entorno);
					m.seguir(mago);
					if (m.colisionaConMago(mago)) {
						vidaMago-=10;
						murcielagos[i] = null;
						murcielagosVivos -=1;
						murcielagosEliminados++;
					}
				}
			}
			
			
			if (boton.estaActivo() && entorno.mousePresente() && entorno.sePresionoBoton(1)) { //si el boton esta activo, dispara 1 hechizo 
				if (manaMago >= 10 ) { //si el mago tiene mas o de 10 de mana, se le resta 10. Si tiene menos de 10 de mana no podra lanzar el hechizo
					manaMago= manaMago - 10;
					double dx = entorno.mouseX() - mago.getX();
					double dy = entorno.mouseY() - mago.getY();
					double angulo = Math.atan2(dy, dx);
					hechizos.add(new Hechizos(mago.getX(), mago.getY(), angulo, 30));
					boton.desactivar(); //una vez disparado el hechizo el boton se desactivara
				}
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
			            murcielagosEliminados++;

			            // Eliminar hechizo
			            hechizos.remove(k);
			            k--; // retroceder índice porque eliminaste un hechizo
			            break;
			         
			        }
			    }			   
			}
//la pantalla de poderes, colocada en esta parte para que no se vea nada que deba pasar por abajo, como
//los poderes o los murcielagos.
			pantalla.dibujarPoderes(entorno);


// botones
			boton.dibujar(entorno);
			boton.estaSobreMouse(entorno);
			
// dibuja la barra de mana en la pantalla			
			mostrarMana();
			
// dibuja la vida en la pantalla
			mostrarVida();
	
// muestra los murcielagos eliminados por el jugador
			mostrarEliminados();

			
//se encarga de darle movimiento al mago segun las indicaciones que de el jugador,
//ademas, revisa si el proximo movimiento hace colision con la piedra, en ese caso lo evita.			
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a')) {
				mago.moverIzquierda(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d')) {
				mago.moverDerecha(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA) || entorno.estaPresionada('w')) {
				mago.moverArriba(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_ABAJO) || entorno.estaPresionada('s')) {
				mago.moverAbajo(piedras);
			}
			if (entorno.estaPresionada(entorno.TECLA_ESCAPE)) {
				System.exit(0);
			}
				
// pantallas de victoria y derrota respectivamente	
			//victoria	
			if (vidaMago <= 0) {
				pantalla.dibujarDerrota(entorno);
			}
			
			//derrota
			if (murcielagosVivos == 0 && murcielagosCreados == murcielagos.length) {
				pantalla.dibujarVictoria(entorno);
				}		
			}
		}


	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
