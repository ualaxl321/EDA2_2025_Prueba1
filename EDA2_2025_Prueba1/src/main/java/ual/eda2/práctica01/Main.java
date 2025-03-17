package main.java.ual.eda2.práctica01;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        System.out.println("CARGAR DATOS");
        File[] archivos = Utilidades.mostrarArchivos();
        if (archivos.length == 0) {
            System.out.println("No se encontraron archivos .txt en el directorio.");
            return;
        }
        int opcion = Utilidades.leerEntero("Seleccione un archivo", 1, archivos.length);
        File archivo = archivos[opcion - 1];
        System.out.println("Archivo seleccionado: " + archivo.getName());
        Punto[] puntos = Utilidades.cargarDatos(archivo.getName());

        // Procesar o mostrar los puntos cargados
        System.out.println("Puntos cargados:");
        for (Punto punto : puntos) {
            System.out.println("Punto: (" + punto.getx() + ", " + punto.gety() + ")");
        }
    }
}
