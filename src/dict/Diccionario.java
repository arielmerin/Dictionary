package dict;

import util.ArbolAVL;
import util.Lista;;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <h1> Clase Diccionario</h1>
 * Aquí se modela la mayor parte del comportamiento del programa, cada que se quiera crear un diccionario siempre se tendrán
 * los siguientes métodos para el apoyo en la búsqueda de palabras similares
 * @author Ariel Merino and Armando Aquino
 * @version 1
 */
public class Diccionario {

    /**
     * Se encarga de leer la dirección por defecto de un archivo .txt para obtener el valor de sus lineas en una estructura
     * de datos
     * @return Lista que contiene todas las lineas del archivo .txt como elementos
     */
    public Lista<String> leeTXT(){
        Lista<String> palabrasTXT = new Lista<>();
        try {
            Scanner input = new Scanner( new File("diccionario.txt"));
            while (input.hasNextLine()){
                palabrasTXT.agregar(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return palabrasTXT;
    }

    /**
     * El siguiente método tiene como fin cambiar cada una de las letras en cada una de las posiciones con el fin de
     * obtener todas las combinaciones posibles si cambiaramos una sola letra de alguna palabra por todas las letras
     * del alfabeto
     * @param palabra palabra que se cambiará
     * @return conjunto de palabras posibles cambiando cada posición por una letra distinta
     */
    public Lista<String> cambiaPalabra(String palabra){
        Lista<String> palabrasConCambio = new Lista<>();
        for (int i = 0; i < palabra.length(); i++) {
            char[] arreglo = palabra.toLowerCase().toCharArray();
            for (int j = 97; j < 123 ; j++ ){
                arreglo[i] = (char)j;
                palabrasConCambio.agregar(String.valueOf(arreglo));
            }
            int[] acentos = new int[6];
            acentos[0] = 225;
            acentos[1] = 233;
            acentos[2] = 237;
            acentos[3] = 243;
            acentos[4] = 250;
            acentos[5] = 252;
            for (int j = 0; j < acentos.length; j++) {
                arreglo[i] = (char)acentos[j];
                palabrasConCambio.agregar(String.valueOf(arreglo));
            }
        }
        return palabrasConCambio;
    }

    /**
     * Dado un conjunto de palabas candidatas a aparecer en el arbol, se toma cada uno de los elementos de dicha lista y se
     * analiza si se encuentran en el arbol, de ser así, se agregan evitando que haya sugerencias repetidas
     * @param palabras conjunto de palabas candidatas a aparecer en el arbol
     * @param arbolAVL lugar donde se buscara cada una de las palabras
     * @return sugerencias, palabras encontradas en el arbol
     */
    public Lista<String> consultar(Lista<String> palabras, ArbolAVL arbolAVL){
        Lista<String> sugerencias = new Lista<>();
        for (String palabra: palabras){
            if (arbolAVL.contiene(palabra)){
                ArbolAVL.NodoAVL nodoAVL = (ArbolAVL.NodoAVL) arbolAVL.buscando(palabra);
                if (!sugerencias.contiene(nodoAVL.toString())){
                    sugerencias.agregar(nodoAVL.toString());
                }
                if (nodoAVL.hayIzquierdo()){
                    if (!sugerencias.contiene(String.valueOf(nodoAVL.izquierdo))){
                        sugerencias.agregar(String.valueOf(nodoAVL.izquierdo));
                    }
                }
                if (nodoAVL.hayDerecho()){
                    if (!sugerencias.contiene(String.valueOf(nodoAVL.derecho))){
                        sugerencias.agregar(String.valueOf(nodoAVL.derecho));
                    }
                }
            }
        }
        return sugerencias;
    }

    /**
     * Identifica si una palabra está bien escrita, esto es; que se encuentre en el arbol y además que al compararlas
     * sean iguales
     * @param palabra candidato a estar bien o mal escrito
     * @param arbolAVL lugar de referencia, donde se buscara si la palabra existe
     * @return true en caso de encontrarla, falso en cualquier otro caso
     */
    public boolean estaCorrecto(String palabra, ArbolAVL<String> arbolAVL){
        if (arbolAVL.contiene(lcFirst(palabra)) || arbolAVL.contiene(palabra)){
            ArbolAVL.NodoAVL nodoAVL = (ArbolAVL.NodoAVL) arbolAVL.buscando(palabra.toLowerCase());
            if (nodoAVL.elemento.equals(palabra.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    /**
     * Cambia la primera letra de cualquier cadena a minúsculas
     * @param str cadena a ser reemplazada
     * @return mismos caracteres de entrada, pero con la primera letra en minúsculas
     */
    private static String lcFirst(String str) {
        if (str.isEmpty()) {
            return str;
        } else {
            return Character.toLowerCase(str.charAt(0)) + str.substring(1);
        }
    }
}
