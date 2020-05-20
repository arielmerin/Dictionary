import dict.Diccionario;
import util.ArbolAVL;
import util.Coleccionable;
import util.Lista;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello muuuuuuuundo");

        Diccionario diccionario = new Diccionario();
        Lista<String> lista = diccionario.leeTXT();
        ArbolAVL<String> stringArbolAVL = new ArbolAVL<>(lista);
        //System.out.println(stringArbolAVL);
        Lista<String> palabrasCambias = diccionario.cambiaPalabra("valjena");
        System.out.println(palabrasCambias);

        int contador = 0;
         for (String palabra1: palabrasCambias){
                if (stringArbolAVL.contiene(palabra1)){
                    contador++;
                    ArbolAVL.NodoAVL nodoAVL = (ArbolAVL.NodoAVL) stringArbolAVL.buscando(palabra1);
                    System.out.println(nodoAVL);
                    System.out.println(nodoAVL.izquierdo);
                    System.out.println(nodoAVL.derecho);
                }
            }
         if (contador == 0){
             System.out.println("No se encontró ninguna coincidencia");
         }
    }
}
