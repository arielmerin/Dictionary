import dict.Diccionario;
import util.ArbolAVL;
import util.Coleccionable;
import util.Lista;
import util.Pila;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello muuuuuuuundo");
        System.out.println("Hola");
        Diccionario diccionario = new Diccionario();
        Pila<String> lista = diccionario.leeTXT();
        System.out.println(lista);
        ArbolAVL<String> stringArbolAVL = new ArbolAVL<>((Coleccionable<String>) lista);
        System.out.println(stringArbolAVL);
    }
}
