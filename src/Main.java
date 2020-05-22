import dict.Diccionario;
import serializer.Serializer;
import util.ArbolAVL;
import util.Lista;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        char acento = 'ü';
        int valor = (int)acento;
        System.out.println("Hello muuuuuuuundo" + valor);
        Diccionario diccionario = new Diccionario();
        Lista<String> lista = diccionario.leeTXT();

        File archivo = new File("dataD.dat");
        Serializer serializer = new Serializer();
        ArbolAVL<String> stringArbolAVL;
        stringArbolAVL = (ArbolAVL<String>) serializer.read("dataD.ser");
        //System.out.println(stringArbolAVL);



        //System.out.println(stringArbolAVL);
        Lista<String> palabrasCambias = diccionario.cambiaPalabra("aei");
        System.out.println(palabrasCambias);
        System.out.println("Las palabras que le va a sugerir son estas; ");
        for (String palabra: palabrasCambias){
            if (stringArbolAVL.contiene(palabra)){
                ArbolAVL.NodoAVL nodoAVL = (ArbolAVL.NodoAVL) stringArbolAVL.buscando(palabra);
                System.out.println(nodoAVL);
                if (nodoAVL.hayIzquierdo()){
                    System.out.println(nodoAVL.izquierdo);
                }
                if (nodoAVL.hayDerecho()){
                    System.out.println(nodoAVL.derecho);
                }
            }
        }
    }
}
