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
        Lista<Lista<String>> doblePalabraCambiada  = new Lista<>();
        for ( String palabra: palabrasCambias ){
            doblePalabraCambiada.agregar(diccionario.cambiaPalabra(palabra));
        }
        System.out.println(doblePalabraCambiada);


        for (Lista<String> lista1: doblePalabraCambiada){
            for (String palabra1: lista1){
                if (stringArbolAVL.contiene(palabra1)){
                    ArbolAVL.NodoAVL nodoAVL = (ArbolAVL.NodoAVL) stringArbolAVL.buscando(palabra1);
                    System.out.println(nodoAVL);
                    System.out.println(nodoAVL.izquierdo);
                    System.out.println(nodoAVL.derecho);
                }
            }
        }
    }
}
