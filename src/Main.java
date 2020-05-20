import dict.Diccionario;
import util.Lista;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello muuuuuuuundo");
        System.out.println("Hola");
        Diccionario diccionario = new Diccionario();
        Lista<String> lista = diccionario.leeTXT();
        System.out.println(lista);

    }
}
