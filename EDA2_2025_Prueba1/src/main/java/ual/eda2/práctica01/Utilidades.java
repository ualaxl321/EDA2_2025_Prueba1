package main.java.ual.eda2.práctica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Utilidades {

    private static final String dir = System.getProperty("user.dir") + File.separator
            + "src" + File.separator
            + "ual" + File.separator
            + "eda2" + File.separator
            + "práctica01" + File.separator;

//    private static final String dir = System.getProperty("user.dir") + File.separator
//    		+ "src" + File.separator
//            + "main" + File.separator
//            + "java" + File.separator
//            + "ual" + File.separator
//            + "eda2" + File.separator
//            + "practica01" + File.separator;
    
    public static double distancia_euclidea(Punto p1, Punto p2) {
        return Math.sqrt(Math.pow(p2.getx() - p1.getx(), 2) + Math.pow(p2.gety() - p1.gety(), 2));
    }

    public static String format(double distancia) {
        return String.format("%.2f", distancia);
    }

    public static File[] mostrarArchivos() {
        File f = new File(dir);
        File[] lista = f.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        });
        if (lista == null || lista.length == 0) {
            System.out.println("No se encontraron archivos .txt en el directorio.");
            return new File[0];
        }
        Arrays.sort(lista, (f1, f2) -> f1.getName().compareTo(f2.getName()));
        for (int i = 0; i < lista.length; i++) {
            System.out.println((i + 1) + ". " + lista[i].getName());
        }
        return lista;
    }

    public static Punto[] cargarDatos(String archivo) {
        Punto[] puntos = null;
        ArrayList<Punto> aux = new ArrayList<>();
        File f = new File(dir + archivo);
        try (FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replace(",", ".");
                String[] tokens = linea.split("\t");
                aux.add(new Punto(Double.valueOf(tokens[1]), Double.valueOf(tokens[2])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        puntos = new Punto[aux.size()];
        aux.toArray(puntos);
        return puntos;
    }

    public static int leerEntero(String mensaje, int min, int max) {
        try (Scanner scanner = new Scanner(System.in)) {
            int numero;
            while (true) {
                System.out.print(mensaje + " (" + min + " - " + max + "): ");
                if (scanner.hasNextInt()) {
                    numero = scanner.nextInt();
                    if (numero >= min && numero <= max) {
                        break;
                    } else {
                        System.out.println("Por favor, ingrese un número entre " + min + " y " + max + ".");
                    }
                } else {
                    System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                    scanner.next(); 
                }
            }
            return numero;
        }
    }
}