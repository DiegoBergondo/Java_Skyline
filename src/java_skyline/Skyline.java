package java_skyline;

import java.util.*;
// Clase que implementa un problema del Skyline
public class Skyline {
	// Clase interna Edificio
	private class Edificio {
		private int xini;
		private int xfin;
		private int altura;

		Edificio (int x1, int x2, int h) {
			this.xini = x1;
			this.xfin = x2;
			this.altura = h;
		}

		int getXini() { return this.xini; }
		int getXfin() { return this.xfin; }
		int getAltura() { return this.altura; }

		// Un Edificio sabe convertirse en una Lista de Coordenadas
		// Resuelve el caso trivial del algoritmo del Skyline
		public ArrayList<Coordenada> toSkyline() {
			ArrayList<Coordenada> salida=new ArrayList<Coordenada>();
			salida.add( new Coordenada (this.getXini(),this.getAltura()) );
			salida.add( new Coordenada (this.getXfin(),0) );
			return salida;
		}
	}

	// Clase interna Coordenada
	private class Coordenada {
		private int x;
		private int h;

		Coordenada (int abcisa,int altura) {
			this.x = abcisa;
			this.h = altura;
		}
		int getAbcisa() { return this.x; }
		int getAltura() { return this.h; }
	}

	// Clase interna Subproblemas
	private class Subproblemas {
		private ArrayList<Edificio> primero;
		private ArrayList<Edificio> segundo;

		// Un objeto Subproblemas se construye dividiendo un problema en dos
		Subproblemas (ArrayList<Edificio> entrada) {
			this.primero = new ArrayList<Edificio>();
			this.segundo = new ArrayList<Edificio>();
			Iterator<Edificio> miIterador = entrada.iterator();
			while ( miIterador.hasNext() ) {
				primero.add(miIterador.next());
				if ( miIterador.hasNext() ) {
					segundo.add(miIterador.next());
				}
			}
		}
		ArrayList<Edificio> getPrimero() { return this.primero; }
		ArrayList<Edificio> getSegundo() { return this.segundo; }	
	}

	// Atributos de la clase Skyline

	private ArrayList<Edificio> buildings; // Se alimenta el Skyline con edificios
	private ArrayList<Coordenada> skyline; // Al resolver el Skyline se produce una
	                                       //  lista de coordenadas

	// Constructor que crea un nuevo problema de Skyline
	Skyline () {
		this.buildings = new ArrayList<Edificio>();
	}

	// Añadimos un edificio al problema de Skyline
	public void addEdificio(int x1, int x2, int h) {
		buildings.add(new Edificio(x1,x2,h));
	}

	// Llamada inicial a la ejecución del algoritmo del Skyline
	public void resuelveSkyline() {
		this.skyline = resuelveSkyline(this.buildings);
	}

	// Implementación del algoritmo del Skyline mediante Divide y Vencerás
	private ArrayList<Coordenada> resuelveSkyline(ArrayList<Edificio> listaEdificios) {
		ArrayList<Coordenada> salida;
		if ( listaEdificios.size() == 1 ) {
			// Caso trivial, convertimos el edificio en un skyline
			salida = listaEdificios.get(0).toSkyline();
		} else {
			// Caso recursivo, dividimos la lista en dos
			Subproblemas subproblemas = new Subproblemas(listaEdificios);
			ArrayList<Coordenada> solucionA = resuelveSkyline(subproblemas.getPrimero());
			ArrayList<Coordenada> solucionB = resuelveSkyline(subproblemas.getSegundo());
			salida = combina(solucionA,solucionB);
		}
		return salida;
	}

	// Combinación de dos Skylines en uno nuevo
	private ArrayList<Coordenada> combina(ArrayList<Coordenada> a, ArrayList<Coordenada> b) {
		ArrayList<Coordenada> salida = new ArrayList<Coordenada>();
		int ultimaAlturaA = 0;
		int ultimaAlturaB = 0;
		int ultimaAlturaExtraida = 0;
		Coordenada coordenadaA,coordenadaB;
		int indiceA = 0;
		int indiceB = 0;
		int nuevaAbcisa,nuevaAltura;
		while ( indiceA < a.size() && indiceB < b.size() ) {
			coordenadaA = a.get(indiceA);
			coordenadaB = b.get(indiceB);
			if ( coordenadaA.getAbcisa() == coordenadaB.getAbcisa() ) {
				// Las dos coordenadas a procesar tienen la misma abcisa
				nuevaAbcisa = coordenadaA.getAbcisa();
				nuevaAltura = java.lang.Math.max(coordenadaA.getAltura(),coordenadaB.getAltura());
				ultimaAlturaA = coordenadaA.getAltura();
				ultimaAlturaB = coordenadaB.getAltura();
				indiceA++;
				indiceB++;
			} else if ( coordenadaA.getAbcisa() < coordenadaB.getAbcisa() ) {
				// La coordenada de la lista a tiene una abcisa menor
				nuevaAbcisa = coordenadaA.getAbcisa();
				nuevaAltura = java.lang.Math.max(coordenadaA.getAltura(), ultimaAlturaB);
				ultimaAlturaA = coordenadaA.getAltura();
				indiceA++;
			} else {
				// La coordenada de la lista b tiene una abcisa menor
				nuevaAbcisa = coordenadaB.getAbcisa();
				nuevaAltura = java.lang.Math.max(coordenadaB.getAltura(), ultimaAlturaA);
				ultimaAlturaB = coordenadaB.getAltura();
				indiceB++;
			}
			// Si la última altura extraída coincide con la nueva altura -> no añadimos
			if ( ultimaAlturaExtraida != nuevaAltura ) {
				salida.add(new Coordenada(nuevaAbcisa,nuevaAltura));
				ultimaAlturaExtraida = nuevaAltura;
			}
		}
		// Hemos salido -> se ha consumido una de las dos listas de entrada
		// Añadimos el resto de la lista que no esté vacía
		while ( indiceA < a.size() ) { salida.add(a.get(indiceA++)); }
		while ( indiceB < b.size() ) { salida.add(b.get(indiceB++)); }
		return salida;
	}

	// Generación de un String con la lista de coordenadas del Skyline
	public String imprimeSkyline() {
		StringBuffer salida = new StringBuffer();
		salida.append("[");
		Iterator<Coordenada> iterador = this.skyline.iterator();
		while( iterador.hasNext() ) {
			Coordenada c = iterador.next();
			salida.append("("+ c.getAbcisa() +(",")+ c.getAltura() +")");
			if (iterador.hasNext()) { salida.append(","); }
		}
		salida.append("]");
		return salida.toString();
	}

	// Generación de un String con el dibujo de un Skyline
	public String dibujaSkyline() {
		StringBuffer salida = new StringBuffer();
		int x = 0;
		int h = 0;
		int maximo = 0;
		// Definimos el nuevo array de altura máxima
		ArrayList<Integer> maxh = new ArrayList<Integer>();
		// Primer paso: transformamos la lista de coordenadas en un array de máximos
		//              y calculamos la altura máxima al mismo tiempo
		Iterator<Coordenada> skyline_it = this.skyline.iterator();
		while( skyline_it.hasNext() ) {
			Coordenada c = skyline_it.next();
			int cx = c.getAbcisa();
			int ch = c.getAltura();
			while ( x < cx ) {
				maxh.add(h);
				x++;
			}
			h = ch;
			maxh.add(h);
			x++;
			if ( maximo < h ) { maximo = h; }
		}
		// Segundo paso: generamos una línea para cada altura, desde el máximo hasta 0
		for ( h = maximo ; h > 0 ; h-- ) {
			for ( x = 0 ; x < maxh.size() ; x++ ) {
				if ( h <= maxh.get(x) ) {
					salida.append("*");
				} else {
					salida.append(" ");
				}
			}
			salida.append("\n");
		}
		for ( x = 0 ; x < maxh.size() ; x++ ) {
			salida.append("-");
		}
		salida.append("\n");
		return salida.toString();
	}
}
