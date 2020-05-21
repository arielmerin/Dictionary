import dict.Diccionario;
import serializer.Serializer;
import util.ArbolAVL;
import util.Coleccionable;
import util.Lista;

import java.io.File;
import java.util.logging.FileHandler;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello muuuuuuuundo");
        Diccionario diccionario = new Diccionario();
        Lista<String> lista = diccionario.leeTXT();

        File archivo = new File("dataD.dat");
        Serializer serializer = new Serializer();

        ArbolAVL<String> stringArbolAVL;
        stringArbolAVL = new ArbolAVL<>(lista);

        serializer.write(stringArbolAVL, "dataD.ser");

        System.out.println(stringArbolAVL);
        Lista<String> palabrasCambias = diccionario.cambiaPalabra("vallena");
        System.out.println(palabrasCambias);
        for (String palabra: palabrasCambias){
            if (stringArbolAVL.contiene(palabra)){
                ArbolAVL.NodoAVL nodoAVL = (ArbolAVL.NodoAVL) stringArbolAVL.buscando(palabra);
                System.out.println(nodoAVL);
                System.out.println(nodoAVL.izquierdo);
                System.out.println(nodoAVL.derecho);
            }
        }
    }
}
