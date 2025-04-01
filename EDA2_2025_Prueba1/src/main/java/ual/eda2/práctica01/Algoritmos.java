package main.java.ual.eda2.práctica01;

import java.util.Arrays;

public class Algoritmos {
	
	/* Algoritmo fuerzaBrutaV1
	 * Entrada: array de puntos
	 * Salida: par de puntos con la menor distancia
	 */
	public static Par AlgoritmoFuerzaBruta_V1 (Punto[] puntos) {
		//Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		//mejor ← Par vacío (con distancia infinita)
		Par mejor = new Par();
		
		/* BUCLES
		 * n ← longitud de puntos
		 *   Para i = 0 hasta n - 2 hacer
		 *   	Para j = 0 hasta n - 1 hacer
		 *   		Si i ≠ j entonces
		 *   			d ← distancia entre puntos[i] y puntos[j]
		 *   			Si d < mejor.distancia entonces
		 *   				Actualizar mejor con puntos[i], puntos[j] y distancia d
		 *   			FinSi
		 *   		FinSi
		 *   	FinPara
		 * 	FinPara
		 * 
		 * Retornar mejor
		 */
		for (int i = 0; i < puntos.length - 2; i++) {
			for (int j = 0; j < puntos.length - 1; j++) {
				if (i == j) continue; // Un punto NO se compara con sí mismo
				double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
				if (d < mejor.getDistancia()) mejor = new Par(puntos[i], puntos[j], d);
			}
		}
		return mejor;
	}
	
	/* Algoritmo fuerzaBrutaV2
	 * 
	 * A diferencia del anterior, evita comparar un punto consigo mismo
	 * 
	 * Entrada: array de puntos
	 * Salida: par de puntos con la menor distancia
	 */
	public static Par AlgoritmoFuerzaBruta_V2 (Punto[] puntos) {
		//Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		
		//mejor ← Par vacío (con distancia infinita)
        Par mejor = new Par();
        
        /* BUCLES
		 * n ← longitud de puntos
		 *   Para i = 0 hasta n - 2 hacer
		 *   	Para j = i + 1 hasta n - 1 hacer
		 *   			d ← distancia entre puntos[i] y puntos[j]
		 *   			Si d < mejor.distancia entonces
		 *   				Actualizar mejor con puntos[i], puntos[j] y distancia d
		 *   			FinSi
		 *   		FinSi
		 *   	FinPara
		 * 	FinPara
		 * 
		 * Retornar mejor
		 */
        for (int i = 0; i < puntos.length - 2; i++) {
        	for (int j = i + 1; j < puntos.length - 1; j++) { 
        		double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
        		if (d < mejor.getDistancia()) mejor = new Par (puntos[i], puntos[j], d);
        	}
        }
		return mejor;
	}
	
	/* Algoritmo divideYVenceras
     * 
     * Entrada: array de puntos
     * Salida: par de puntos con la menor distancia
     */
	public static Par divideYVenceras_V1 (Punto[] puntos) {
		// Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		
		/*	copia ← clon del array de puntos
		 * 	Ordenar copia por coordenada x
		 */
		Punto[] copias = Arrays.copyOf(puntos, puntos.length);
		Arrays.sort(copias, new Compare());
		
		/*
		 * mejor ← resultado de comprobarRepetidos(copia)
		 *   Si mejor ≠ nulo entonces
		 *       Retornar mejor
		 *   FinSi
		 */
		Par mejor = repetidos(copias);
		if (mejor != null) return mejor;
		return divideYVenceras_V1(copias, 0, copias.length - 1);
	}
	
	/* Algoritmo divideYVencerasV1 (recursivo)
	 *   Entrada: array de puntos, inicio, fin
	 *   Salida: par de puntos con la menor distancia en ese rango
	 * 
	 */
	private static Par divideYVenceras_V1 (Punto[] puntos, int inicio, int fin) {
		//mejor ← Par vacío (con distancia infinita)
		Par mejor = new Par();
		
		/* 	BUCLE
		 *  Si fin - inicio ≤ 3 entonces
		 *  	Para i = inicio hasta fin hacer
		 *      	Para j = i + 1 hasta fin hacer
		 * 				d ← distancia entre puntos[i] y puntos[j]
		 *             	Si d < mejor.distancia entonces
		 *             		Actualizar mejor con puntos[i], puntos[j] y distancia d
		 *             FinSi
		 *        	FinPara
		 *     	FinPara
		 *     	Retornar mejor
		 * 	FinSi
		 */
		if (fin - inicio <= 3) {
	        for (int i = inicio; i < fin; i++) {
	            for (int j = i + 1; j <= fin; j++) {
	                double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
	                if (d < mejor.getDistancia()) {
	                    mejor = new Par(puntos[i], puntos[j], d);
	                }
	            }
	        }
	        return mejor;
	    }
		
		/*CASO RECURSIVO
		 *   mitad ← (inicio + fin) / 2
		 *   
		 *   pi ← divideYVencerasV1(puntos, inicio, mitad)
		 *   pd ← divideYVencerasV1(puntos, mitad + 1, fin)
		 */
		int mitad = (inicio + fin) / 2;
		
		Par pi = divideYVenceras_V1(puntos, inicio, mitad);
		Par pd = divideYVenceras_V1(puntos, mitad + 1, fin);
		
		/* Comprobaciones
		 * Si pi.distancia < pd.distancia entonces
		 *     mejor ← pi
		 * Sino
		 *    mejor ← pd
		 * FinSi
		 */
		if (pi.getDistancia() < pd.getDistancia()) {
		    mejor = pi;
		} else {
		    mejor = pd;
		}
		
		/* BUCLE DEL RECURSIVO
		 * Para i = inicio hasta mitad hacer
		 * 	    Para j = mitad + 1 hasta fin hacer
		 *     		d ← distancia entre puntos[i] y puntos[j]
		 *          	Si d < mejor.distancia entonces
		 *             		Actualizar mejor con puntos[i], puntos[j] y distancia d
		 *         		FinSi
		 *		FinPara
		 * FinPara
		 */
		for (int i = inicio; i < mitad; i++) {
	        for (int j = mitad + 1; j <= fin; j++) {
	            double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
	            if (d < mejor.getDistancia()) mejor = new Par(puntos[i], puntos[j], d);
	        }
	    }
	    return mejor;
	}
	
	/* Algoritmo divideYVencerasV2
     * 
     * Entrada: array de puntos
     * Salida: par de puntos con la menor distancia
     */
	public static Par divideYVenceras_V2 (Punto[] puntos) {
		// Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		
		/*	copia ← clon del array de puntos
		 * 	Ordenar copia por coordenada x
		 */
		Punto[] copias = Arrays.copyOf(puntos, puntos.length);
		Arrays.sort(copias, new Compare());
		
		/*
		 * mejor ← resultado de comprobarRepetidos(copia)
		 *   Si mejor ≠ nulo entonces
		 *       Retornar mejor
		 *   FinSi
		 */
		Par mejor = repetidos(copias);
		if (mejor != null) return mejor;
		return divideYVenceras_V2(copias, 0, copias.length - 1);
	}
	
	/* Algoritmo divideYVencerasV2 (recursivo)
	 *   Entrada: array de puntos, inicio, fin
	 *   Salida: par de puntos con la menor distancia en ese rango
	 * 
	 */
	private static Par divideYVenceras_V2 (Punto[] puntos, int inicio, int fin) {
		//mejor ← Par vacío (con distancia infinita)
		Par mejor = new Par();
		
		/* 	BUCLE
		 *  Si fin - inicio ≤ 3 entonces
		 *  	Para i = inicio hasta fin hacer
		 *      	Para j = i + 1 hasta fin hacer
		 * 				d ← distancia entre puntos[i] y puntos[j]
		 *             	Si d < mejor.distancia entonces
		 *             		Actualizar mejor con puntos[i], puntos[j] y distancia d
		 *             FinSi
		 *        	FinPara
		 *     	FinPara
		 *     	Retornar mejor
		 * 	FinSi
		 */
		if (fin - inicio <= 3) {
	        for (int i = inicio; i < fin; i++) {
	            for (int j = i + 1; j <= fin; j++) {
	                double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
	                if (d < mejor.getDistancia()) mejor = new Par(puntos[i], puntos[j], d);
				}
			}
			return mejor;
		}
		
		/*CASO RECURSIVO
		 *   mitad ← (inicio + fin) / 2
		 *   
		 *   pi ← divideYVencerasV1(puntos, inicio, mitad)
		 *   pd ← divideYVencerasV1(puntos, mitad + 1, fin)
		 */
		int mitad = (inicio + fin) / 2;
		
		Par pi = divideYVenceras_V2(puntos, inicio, mitad);
		Par pd = divideYVenceras_V2(puntos, mitad + 1, fin);
		
		/* Comprobaciones
		 * Si pi.distancia < pd.distancia entonces
		 *     mejor ← pi
		 * Sino
		 *    mejor ← pd
		 * FinSi
		 */
		if (pi.getDistancia() < pd.getDistancia()) {
		    mejor = pi;
		} else {
		    mejor = pd;
		}

		
		/* BUCLE DEL RECURSIVO --> AQUÍ es donde DyV cambia
		 * 
		 * 	Para i = mitad hasta inicio con paso -1 Y puntos[i].x > puntos[mitad].x - mejor.distancia hacer
		 * 		Para j = mitad + 1 hasta longitud de puntos - 1 Y puntos[j].x < puntos[mitad].x + mejor.distancia hacer
		 * 		d ← distancia entre puntos[i] y puntos[j]
		 * 			Si d < mejor.distancia entonces
		 * 				Actualizar mejor con puntos[i], puntos[j] y distancia d
		 * 			FinSi
		 * 		FinPara
		 * 	FinPara
		 */
		for (int i = mitad; i >= inicio && puntos[i].getx() > puntos[mitad].getx() - mejor.getDistancia(); i--) {
	        for (int j = mitad + 1; j <= fin && puntos[j].getx() < puntos[mitad].getx() + mejor.getDistancia(); j++) {
	            double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
	            if (d < mejor.getDistancia()) mejor = new Par(puntos[i], puntos[j], d);
			}
		}
		return mejor;
	}
	
	/* Algoritmo divideYVencerasV3
     * 
     * Entrada: array de puntos
     * Salida: par de puntos con la menor distancia
     */
	public static Par divideYVenceras_V3 (Punto[] puntos) {
		// Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		
		/*	copia ← clon del array de puntos
		 * 	Ordenar copia por coordenada x
		 */
		Punto[] copias = Arrays.copyOf(puntos, puntos.length);
		Arrays.sort(copias, new Compare());
		
		/*
		 * mejor ← resultado de comprobarRepetidos(copia)
		 *   Si mejor ≠ nulo entonces
		 *       Retornar mejor
		 *   FinSi
		 */
		Par mejor = repetidos(copias);
		if (mejor != null) return mejor;
		return divideYVenceras_V3(copias, 0, copias.length - 1);
	}
	
	/* Algoritmo divideYVencerasV3 (recursivo)
	 *   Entrada: array de puntos, inicio, fin
	 *   Salida: par de puntos con la menor distancia en ese rango
	 * 
	 */
	private static Par divideYVenceras_V3 (Punto[] puntos, int inicio, int fin) {
		//mejor ← Par vacío (con distancia infinita)
		Par mejor = new Par();
		
		/* 	BUCLE
		 *  Si fin - inicio ≤ 3 entonces
		 *  	Para i = inicio hasta fin hacer
		 *      	Para j = i + 1 hasta fin hacer
		 * 				d ← distancia entre puntos[i] y puntos[j]
		 *             	Si d < mejor.distancia entonces
		 *             		Actualizar mejor con puntos[i], puntos[j] y distancia d
		 *             FinSi
		 *        	FinPara
		 *     	FinPara
		 *     	Retornar mejor
		 * 	FinSi
		 */
		if (fin - inicio <= 3) {
			for (int i = inicio; i < fin; i++) {
	            for (int j = i + 1; j <= fin; j++) {
	                double d = Utilidades.distancia_euclidea(puntos[i], puntos[j]);
	                if (d < mejor.getDistancia()) mejor = new Par(puntos[i], puntos[j], d);
	            }
	        }
	        return mejor;
	    }
		
		/*CASO RECURSIVO
		 *   mitad ← (inicio + fin) / 2
		 *   
		 *   pi ← divideYVencerasV1(puntos, inicio, mitad)
		 *   pd ← divideYVencerasV1(puntos, mitad + 1, fin)
		 */
		int mitad = (inicio + fin) / 2;
		
		Par pi = divideYVenceras_V3(puntos, inicio, mitad);
		Par pd = divideYVenceras_V3(puntos, mitad + 1, fin);
		
		/* Comprobaciones
		 * Si pi.distancia < pd.distancia entonces
		 *     mejor ← pi
		 * Sino
		 *    mejor ← pd
		 * FinSi
		 */
		if (pi.getDistancia() < pd.getDistancia()) {
	        mejor = pi;
	    } else {
	        mejor = pd;
	    }
		
		/* Construir franja central
		 * franja ← array vacío de tamaño (fin - inicio + 1)
		 * k ← 0
		 */
		Punto[] franja = new Punto[fin - inicio + 1];
		int k = 0;
		
		/* 	Para i = mitad hasta inicio con paso -1 Y puntos[i].x > puntos[mitad].x - mejor.distancia hacer
		 * 		franja[k] ← puntos[i]
		 * 		Incrementar k
		 * 	FinPara
		 */
		for (int i = mitad; i >= inicio && puntos[i].getx() > puntos[mitad].getx() - mejor.getDistancia(); i--) {
	        franja[k] = puntos[i];
	        k++;
	    }
		
		/* 	Para j = mitad + 1 hasta fin Y puntos[j].x < puntos[mitad].x + mejor.distancia hacer
		 * 		franja[k] ← puntos[j]
		 * 		Incrementar k
		 * 	FinPara
		 */
		for (int j = mitad + 1; j <= fin && puntos[j].getx() < puntos[mitad].getx() + mejor.getDistancia(); j++) {
			franja[k] = puntos[j];
            k++;
		}
		
		//Ordenar franja desde posición 0 hasta k - 1 por coordenada y
		Arrays.sort(franja, 0, k, new CompareY());
		
		/*	BUCLE
		 * 
		 * 	Para i = 0 hasta k - 2 hacer
		 * 		Para j = i + 1 hasta k - 1 Y franja[j].y - franja[i].y < mejor.distancia hacer
		 * 			d ← distancia entre franja[i] y franja[j]
		 * 			Si d < mejor.distancia entonces
		 * 				Actualizar mejor con franja[i], franja[j] y distancia d
		 * 			FinSi
		 * 		FinPara
		 * 	FinPara
		 */
		for (int i = 0; i < k - 1; i++) {
	        for (int j = i + 1; j < k && franja[j].gety() - franja[i].gety() < mejor.getDistancia(); j++) {
	            double d = Utilidades.distancia_euclidea(franja[i], franja[j]);
	            if (d < mejor.getDistancia()) mejor = new Par(franja[i], franja[j], d);
			}
		}
		return mejor;
	}
	
	/* Algoritmo divideYVencerasV4
     * 
     * Entrada: array de puntos
     * Salida: par de puntos con la menor distancia
     */
	public static Par divideYVenceras_V4 (Punto[] puntos) {
		// Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		
		/*	n ← longitud de puntos
		 * 	px ← array vacío de tamaño (n)
		 * 	py ← array vacío de tamaño (n)
		 */
		Punto[] px = new Punto[puntos.length];
		Punto[] py = new Punto[puntos.length];
		
		/*
		 *	Ordenar px por coordenada y
		 *	Ordenar px por coordenada x
		 */
		System.arraycopy(puntos, 0, px, 0, puntos.length);
		System.arraycopy(puntos, 0, py, 0, puntos.length);
		Arrays.sort(px, new CompareY());
		Arrays.sort(py, new CompareX());
		
		/*
		 * 	mejor ← resultado de comprobarRepetidos(px) 
		 * 	Si mejor ≠ nulo entonces
		 * 		Retornar mejor 
		 * 	FinSi
		 */
		Par mejor = repetidos(px);
		if(mejor != null) return mejor;
		
		//py ← copia de px
		py = px.clone();
		
		//aux ← array vacío de tamaño (n)
		Punto[] aux = new Punto[puntos.length];
		
		//Retornar divideYVencerasV4(px, py, aux, 0, longitud de px - 1)
		return divideYVenceras_V4(px, py, aux, 0, px.length - 1);
	}
	
	/* Algoritmo divideYVencerasV4 (recursivo)
     *   Entrada: arrays px, py, aux; inicio; fin
     *   Salida: par de puntos con la menor distancia en ese rango
     */
	private static Par divideYVenceras_V4 (Punto[] px, Punto[] py, Punto[] aux, int inicio, int fin) {
		// mejor ← Par vacío (con distancia infinita)
		Par mejor = new Par();
		
		/*	Si inicio >= fin
		 *      Retornar mejor
		 *  FinSi
		 */
		if (inicio >= fin) return mejor;
		
		// mitad ← (inicio + fin) / 2
		int mitad = (inicio + fin) / 2;
		
		/*  pi ← divideYVencerasV4(px, py, aux, inicio, mitad)
		 * 	pd ← divideYVencerasV4(px, py, aux, mitad + 1, fin)
		 */
		Par pi = divideYVenceras_V4(px, py, aux, inicio, mitad);
		Par pd = divideYVenceras_V4(px, py, aux, mitad + 1, fin);
		
		/*	Si pi.distancia < pd.distancia entonces
		 * 		mejor ← pi
		 * 	Sino
		 * 		mejor ← pd
		 * 	FinSi
		 */
		if (pi.getDistancia() < pd.getDistancia()) {
	        mejor = pi;
	    } else {
	        mejor = pd;
	    }
		
		// mezcla(py, aux, inicio, mitad, fin)
		mezcla(py, aux, inicio, mitad, fin);
		
		/* 	k ← 0
		 * 
		 * 	Para i = inicio hasta fin hacer
		 * 		Si valor absoluto de (py[i].x - px[mitad].x) < mejor.distancia entonces
		 * 			aux[k] ← py[i]
		 * 			Incrementar k
		 * 		FinSi
		 * 	FinPara
		 */
		int k = 0;
		
		for (int i = inicio; i <= fin; i++) {
			if (Math.abs(py[i].getx() - px[mitad].getx()) < mejor.getDistancia()) {
				aux[k] = py[i];
				k++;
			}
		}
		
		/* 	Para i = 0 hasta k - 1 hacer
		 * 		Para j = i + 1 hasta k - 1 Y aux[j].y - aux[i].y < mejor.distancia hacer
		 * 			d ← distancia entre aux[i] y aux[j]
		 * 			Si d < mejor.distancia entonces
		 *                 Actualizar mejor con aux[i], aux[j] y distancia d
		 *          FinSi
		 * 		FinPara
		 * 	FinPara
		 */
		for (int i = 0; i < k - 1; i++) {
	        for (int j = i + 1; j < k && aux[j].gety() - aux[i].gety() < mejor.getDistancia(); j++) {
	            double d = Utilidades.distancia_euclidea(aux[i], aux[j]);
	            if (d < mejor.getDistancia()) mejor = new Par(aux[i], aux[j], d);
            }
		}
		return mejor;
	}
	
	/* Algoritmo divideYVencerasV5 --> NO SE IMPLEMENTA EN PRÁCTICA 4
     * 
     * Entrada: array de puntos
     * Salida: par de puntos con la menor distancia
     */
	public static Par divideYVenceras_V5 (Punto[] puntos) {
		// Verificar que el array de puntos no sea nulo → Excepción
		if (puntos == null) throw new IllegalArgumentException("Array nulo");
		
		/*	n ← longitud de puntos
		 * 	px ← array vacío de tamaño (n)
		 * 	py ← array vacío de tamaño (n)
		 */
		Punto[] px = new Punto[puntos.length];
		Punto[] py = new Punto[puntos.length];
		
		/*
		 *	Ordenar px por coordenada y
		 *	Ordenar px por coordenada x
		 */
		System.arraycopy(puntos, 0, px, 0, puntos.length);
		System.arraycopy(puntos, 0, py, 0, puntos.length);
		Arrays.sort(px, new CompareY());
		Arrays.sort(py, new CompareX());
		
		/*
		 * 	mejor ← resultado de comprobarRepetidos(px) 
		 * 	Si mejor ≠ nulo entonces
		 * 		Retornar mejor 
		 * 	FinSi
		 */
		Par mejor = repetidos(px);
		if(mejor != null) return mejor;
		
		//py ← copia de px
		py = px.clone();
		
		//aux ← array vacío de tamaño (n)
		Punto[] aux = new Punto[puntos.length];
		
		//mejor ← Par vacío (con distancia infinita)
		mejor = new Par();
		
		//Retornar divideYVencerasV5(mejor, px, py, aux, 0, longitud de px - 1)
		return divideYVenceras_V5(mejor, px, py, aux, 0, px.length - 1);
	}
	
	/* Algoritmo divideYVencerasV5 (recursivo)
     *   Entrada: mejor, arrays px, py, aux; inicio; fin
     *   Salida: par de puntos con la menor distancia en ese rango
     */
	private static Par divideYVenceras_V5 (Par mejor, Punto[] px, Punto[] py, Punto[] aux, int inicio, int fin) {
		/*	Si inicio >= fin
		 *      Retornar mejor
		 *  FinSi
		 */
		if (inicio >= fin) return mejor;
		
		// mitad ← (inicio + fin) / 2
		int mitad = (inicio + fin) / 2;
		
		/*  pi ← divideYVencerasV5(mejor, px, py, aux, inicio, mitad)
		 * 	pd ← divideYVencerasV5(mejor, px, py, aux, mitad + 1, fin)
		 */
		Par pi = divideYVenceras_V5(mejor, px, py, aux, inicio, mitad);
		Par pd = divideYVenceras_V5(mejor, px, py, aux, mitad + 1, fin);
		
		/*	Si pi.distancia < pd.distancia entonces 
		 * 		mejor ← pi 
		 * 	Sino 
		 * 		mejor ← pd 
		 * 	FinSi
		 */
		if (pi.getDistancia() < pd.getDistancia()) mejor = pi;
		mejor = pd;
		
		/*	Determinar el rango de la franja central en px
		 * 	
		 * 	start ← mitad
		 * 	Mientras start > inicio Y px[start].x > px[mitad].x - mejor.distancia hacer
		 * 		Decrementar start
		 * 	FinMientras
		 */
		int start = mitad;
		while (start > inicio && px[start].getx() > px[mitad].getx() - mejor.getDistancia()) {
			start--;
		}
		
		/*	end ← mitad + 1
		 * 	Mientras end < fin Y px[end].x < px[mitad].x + mejor.distancia hacer
		 * 		Incrementar end
		 * 	FinMientras
		 */
		int end = mitad + 1;
		while (end < fin && px[end].getx() < px[mitad].getx() + mejor.getDistancia()) {
			end++;
		}
		
		//mezcla(py, aux, start, mitad, end)
		mezcla(py, aux, start, mitad, end);
		
		/*	Determinar el rango de la franja central en py
		 * 	
		 * 	start ← mitad
		 * 	Mientras start > inicio Y py[start].x > px[mitad].x - mejor.distancia hacer
		 * 		Decrementar start
		 * 	FinMientras
		 */
		while (start > inicio && py[start].getx() > px[mitad].getx() - mejor.getDistancia()) {
			start--;
		}
		
		/*	end ← mitad + 1
		 * 	 Mientras end < fin Y py[end].x < px[mitad].x + mejor.distancia hacer
		 * 		Incrementar end
		 * 	FinMientras
		 */
		while (end < fin && py[end].getx() < px[mitad].getx() + mejor.getDistancia()) {
			end++;
		}
		
		/* 	k ← end - start + 1
		 * 	aux[0..k] ← Copiar py[start..end]
		 */
		int k = end - start + 1;
		System.arraycopy(py, start, aux, 0, k);
		
		/* Comparar cada punto con sus 7 siguientes (Regla de los 7)
		 *	
		 *	Para i =0 hasta k - 2 hacer
		 *		Para j = i + 1 hasta k - 1 Y aux[j].y - aux[i].y < mejor.distancia hacer
		 *			d ← distancia entre aux[i] y aux[j]
		 *			Si d < mejor.distancia entonces
		 *				Actualizar mejor con aux[i], aux[j] y distancia d
		 *			FinSi
		 *		FinPara
		 *	FinPara
		 */
		for (int i = 0; i < k - 1; i++) {
			for (int j = i + 1; j < k && aux[j].gety() - aux[i].gety() < mejor.getDistancia(); j++) {
				double d = Utilidades.distancia_euclidea(aux[i], aux[j]);
				if (d < mejor.getDistancia())
					mejor = new Par(aux[i], aux[j], d);
			}
		}
		return mejor;
	}
	
	// Método auxiliar para comprobar si hay puntos repetidos
	private static Par repetidos(Punto[] puntos) {
		for (int i = 0; i < puntos.length - 1; i++) {
			if (puntos[i].equals(puntos[i+1])) return new Par(puntos[i], puntos[i+1], 0);
		}
		return null;
	}
	
	/* Algoritmo mezcla
	 * 	Entrada: arrays py, aux; inicio; mitad; fin
	 * 	Salida: py ordenado por coordenada y en el rango [inicio, fin]
	 */
	private static void mezcla(Punto[] py, Punto[] aux, int inicio, int mitad, int fin) {
		//aux[inicio..fin] ← Copiar py[inicio..fin]
		System.arraycopy(py, inicio, aux, inicio, fin - inicio + 1);
		
		/*	BUCLE
		 * 
		 *   i ← inicio
		 *   j ← mitad + 1
		 *   
		 *   Para k = inicio hasta fin hacer
		 *   	Si i > mitad entonces
		 *   		py[k] ← aux[j]
		 *   		Incrementar j
		 *   	Sino Si j > fin entonces
		 *   		py[k] ← aux[i]
		 *   		Incrementar i
		 *   	Sino Si aux[j].y < aux[i].y entonces
		 *   		py[k] ← aux[j]
		 *   		Incrementar j
		 *   	Sino
		 *   		py[k] ← aux[i]
		 *   		Incrementar i
		 *   	FinSi
		 *   FinPara
		 */

		int i = inicio;
		int j = mitad + 1;

	    for (int k = inicio; k <= fin; k++) {
	        if (i > mitad) {
	            py[k] = aux[j];
	            j++;
	        } else if (j > fin) {
	            py[k] = aux[i];
	            i++;
	        } else if (aux[j].gety() < aux[i].gety()) {
	            py[k] = aux[j];
	            j++;
	        } else {
	            py[k] = aux[i];
	            i++;
	        }
		}
	}
}
