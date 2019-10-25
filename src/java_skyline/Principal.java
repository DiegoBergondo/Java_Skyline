package java_skyline;

import java.io.FileNotFoundException;
import java.util.StringTokenizer;

public class Principal {
	static int xini,xfin,altura;
	static int numLinea = 1;
	
	// Método main
	public static void main(String[] args) {
		if ( args.length > 0 ) {
			try {
				// Creamos un nuevo problema de Skyline
				Skyline problemaSkyline = new Skyline();
				// Alimentamos el problema con los datos de la entrada
				Entrada entrada = new Entrada(args[0]);
				try  {
					String linea = entrada.leeLinea();
					while ( linea != null ) {
						StringTokenizer tokens = new StringTokenizer(linea,",");
						try {
							xini = Integer.parseInt(tokens.nextToken());
							xfin = Integer.parseInt(tokens.nextToken());
							altura = Integer.parseInt(tokens.nextToken());
							problemaSkyline.addEdificio(xini,xfin,altura);
							System.out.println("Añadido nuevo edificio al Skyline: (" + xini + "," + xfin + "," + altura + ").");
						} catch ( Exception e ) {
							System.err.println("Error, no hay suficientes datos correctos en la línea " + numLinea +".");
						}
						linea = entrada.leeLinea();
						numLinea++;
					}	
				} catch ( Exception e ) {
					System.err.println("Error en la lectura del fichero de entrada");
				}
				entrada.cierra();
				// Una vez alimentado el problema de Skyline, le pedimos que se resuelva
				problemaSkyline.resuelveSkyline();
				// Imprimimos los resultados
				System.out.println();
				System.out.println("Resultado del algoritmo del Skyline:");
				System.out.println();
				System.out.println(problemaSkyline.imprimeSkyline());
				System.out.println();
				System.out.println(problemaSkyline.dibujaSkyline());
			} catch (FileNotFoundException e) {
				System.err.println("Error: no se ha podido abrir el fichero indicado "+args[0]);
			}
		} else {
			System.err.println("Error: no se ha especificado el fichero esperado.");
		}
	}
}
