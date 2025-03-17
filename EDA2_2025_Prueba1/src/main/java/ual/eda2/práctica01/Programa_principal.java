package main.java.ual.eda2.práctica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Programa_principal {

	public static void main(String[] args) {
        String carpeta = "src/main/java/ual/eda2/práctica01/";
        File directorio = new File(carpeta);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Obtener lista de archivos .txt en la carpeta
            File[] archivos = directorio.listFiles((d, name) -> name.endsWith(".txt"));
            if (archivos == null || archivos.length == 0) {
                System.out.println("No se encontraron archivos en la carpeta.");
                break;
            }

            // Mostrar menú de selección
            System.out.println("\nSelecciona un archivo:");
            for (int i = 0; i < archivos.length; i++) {
                System.out.println((i + 1) + ". " + archivos[i].getName());
            }
            System.out.println("0. Salir");

            // Leer la opción del usuario
            int opcion;
            while (true) {
                System.out.print("Introduce el número del archivo (0 para salir): ");
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                    if (opcion == 0) {
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        return; // Sale completamente del programa
                    }
                    if (opcion >= 1 && opcion <= archivos.length) break;
                } catch (NumberFormatException ignored) {}
                System.out.println("Opción no válida, intenta de nuevo.");
            }

            // Obtener el archivo seleccionado
            String nombreArchivo = archivos[opcion - 1].getPath();
            System.out.println("Has seleccionado: " + nombreArchivo);

            // Leer los puntos del archivo seleccionado
            List<Punto> puntos = leerPuntosDesdeArchivo(nombreArchivo);
            if (puntos.isEmpty()) {
                System.out.println("No se encontraron puntos en el archivo.");
                continue; // Vuelve a mostrar el menú
            }

            long inicio = System.nanoTime();
            Par mejorFuerzaBruta = Algoritmos.AlgoritmoFuerzaBruta_V1(puntos.toArray(new Punto[0]));
            long fin = System.nanoTime();
            long tiempoTotal = fin - inicio;
            System.out.println("Algoritmo Iterativo usando Fuerza Bruta:" + mejorFuerzaBruta);
            System.out.println("Tiempo requerido para el algoritmo Iterativo usando Fuerza Bruta:" + (tiempoTotal/1000000000.0) + " segundos");
            
            
            inicio = System.nanoTime();
            Par mejorFuerzaBruta2 = Algoritmos.AlgoritmoFuerzaBruta_V2(puntos.toArray(new Punto[0]));
            fin = System.nanoTime();
            tiempoTotal = fin - inicio;
            System.out.println("\nAlgoritmo Iterativo Mejorado usando Fuerza Bruta:" + mejorFuerzaBruta2);
            System.out.println("Tiempo requerido para el algoritmo Iterativo Mejorado usando Fuerza Bruta: " + (tiempoTotal/1000000000.0) + " segundos");
            
            inicio = System.nanoTime();
            Par divideYVenceras_Version1 = Algoritmos.divideYVenceras_V1(puntos.toArray(new Punto[0]));
            fin = System.nanoTime();
            tiempoTotal = fin - inicio;
            System.out.println("\nAlgoritmo Divide Y Vencerás version 1:" + divideYVenceras_Version1);
            System.out.println("Tiempo requerido para el algoritmo Divide y Vencerás version 1: " + (tiempoTotal/1000000000.0) + " segundos");
            
            inicio = System.nanoTime();
            Par divideYVenceras_Version2 = Algoritmos.divideYVenceras_V2(puntos.toArray(new Punto[0]));
            fin = System.nanoTime();
            tiempoTotal = fin - inicio;
            System.out.println("\nAlgoritmo Divide Y Vencerás version 2:" + divideYVenceras_Version2);
            System.out.println("Tiempo requerido para el algoritmo Divide y Vencerás version 2: " + (tiempoTotal/1000000000.0) + " segundos");
            
            inicio = System.nanoTime();
            Par divideYVenceras_Version3 = Algoritmos.divideYVenceras_V3(puntos.toArray(new Punto[0]));
            fin = System.nanoTime();
            tiempoTotal = fin - inicio;
            System.out.println("\nAlgoritmo Divide Y Vencerás version 3:" + divideYVenceras_Version3);
            System.out.println("Tiempo requerido para el algoritmo Divide y Vencerás version 3: " + (tiempoTotal/1000000000.0) + " segundos");
            
            inicio = System.nanoTime();
            Par divideYVenceras_Version4 = Algoritmos.divideYVenceras_V4(puntos.toArray(new Punto[0]));
            fin = System.nanoTime();
            tiempoTotal = fin - inicio;
            System.out.println("\nAlgoritmo Divide Y Vencerás version 4:" + divideYVenceras_Version4);
            System.out.println("Tiempo requerido para el algoritmo Divide y Vencerás version 4: " + (tiempoTotal/1000000000.0) + " segundos");
            
            inicio = System.nanoTime();
            Par divideYVenceras_V5 = Algoritmos.divideYVenceras_V5(puntos.toArray(new Punto[0]));
            fin = System.nanoTime();
            tiempoTotal = fin - inicio;
            System.out.println("\nAlgoritmo Divide Y Vencerás version 5:" + divideYVenceras_V5);
            System.out.println("Tiempo requerido para el algoritmo Divide y Vencerás version 5: " + (tiempoTotal/1000000000.0) + " segundos");
        }
    }

    private static List<Punto> leerPuntosDesdeArchivo(String nombreArchivo) {
        List<Punto> puntos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\t");
                if (partes.length >= 3) {
                    try {
                        double x = Double.parseDouble(partes[1].replace(",", "."));
                        double y = Double.parseDouble(partes[2].replace(",", "."));
                        puntos.add(new Punto(x, y));
                    } catch (NumberFormatException e) {
                        System.out.println("Error procesando línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return puntos;
    }
}
