package dict;

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
        }
        return palabrasConCambio;
    }
}
