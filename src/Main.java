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
        System.out.println(stringArbolAVL);

        System.out.println(diccionario.cambiaPalabra("vallena"));

    }
}
