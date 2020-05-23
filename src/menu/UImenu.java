package menu;
import dict.Diccionario;
import serializer.Serializer;
import util.ArbolAVL;
import util.Lista;

import java.io.File;

import static util.Utilidades.getInt;
import static util.Utilidades.getStr;

/**
 * <h1>Clase Menú</h1>
 * En esta clase se modela la interacción con el usuario, con opciones de consultar y agregar alguna palabra al diccionario
 * también se considera el arbol principal aquí
 * @author Ariel Merino and Armando Aquino
 * @version 1
 */
public class UImenu {

    /**
     * Estructura principal donde se alojará el árbol con el contenido del diccionario
     */
    private ArbolAVL<String> arbolAVL;
    /**
     * Objeto para manejar la persistencia de datos dentro del programa
     */
    private Serializer serializer = new Serializer();
    /**
     * Entidad encargada de proveernos los métodos suficientes para modelar el comportamiento del diccionario
     */
    private Diccionario diccionario = new Diccionario();

    /**
     * Método cuya función es dar la bienvenida al usuario y mostrar el menú <i>principal</i>
     * donde se piden las opciones fundamentales para la consulta
     */
    public void principal(){
        boolean continuar = true;
        int respuesta;
        System.out.println("Bienvenidx al diccionario");
        revisaExistencia();
        do {
            System.out.println("\n-----------------\nMenú Principal\n-----------------\n[1]Consultar si una palabra es correcta\n[2]Salir");
            respuesta = getInt("Ingrese la opción deseada: ", "Error, intente de nuevo");
            switch (respuesta){
                case 1:
                    consultar();
                    break;
                case 2:
                    continuar = false;
                    System.out.println("¡Hasta pronto!");
                    guardar();
                    break;
                default:
                    System.out.println("Eliga una opción correcta");
                    break;
            }
        }while (continuar);
    }

    /**
     * Se emplea para delegar la tarea de preguntarle al usuario si desea agregar una palabra nueva al diccionario
     * @param palabraNueva palabra que potencialmente será agregada
     * @return true en caso de haber sido agregada, false en otro caso.
     */
    private boolean preguntaAgregar(String palabraNueva){
        System.out.println("¿Deseas agregar la palabra " + palabraNueva + " al diccionario?");
        System.out.println("[1]Agregar\n[2]No Agregar");
        int debeAgregar = getInt("Ingrese la opción deseada: ", "Error, ingrese una opción válida");
        boolean añadir = debeAgregar == 1? true: false;
        if (añadir){
            arbolAVL.agrega(palabraNueva);
        }
        return añadir;
    }

    /**
     * Delega la tarea de guardar <i> serializar </i> el objeto de tipo arbol que funge como diccionario
     */
    private void guardar(){
        serializer.write(arbolAVL, "dataD.ser");
    }

    /**
     * Se encarga de buscar si existe un archivo con información de diccionario, en caso de encontrarlo lo lee para
     * trabajar con esa información, en otro caso crea un nuevo archivo y lee el archivo txt que se supone que siempre encontrará
     */
    private void revisaExistencia(){
        File archivo = new File("dataD.ser");
        if (archivo.exists()){
            arbolAVL = (ArbolAVL<String>) serializer.read("dataD.ser");
        }else {
            System.out.println("No existía el archivo");
            Lista<String> lista = diccionario.leeTXT();
            arbolAVL = new ArbolAVL<>(lista);
            guardar();
        }
    }

    /**
     * Delega la tarea de consultar alguna palabra en el diccionario, encargándose de revisar si está bien escrita y si no
     * mostrar sugerencias a la vez que preguntar si la usuaria desea agregar dicha palabra al diccionario
     */
    private void consultar(){
        String palabraConsulta = getStr("Ingrese la palabra: ", "Error, intente de nuevo");
        if (diccionario.estaCorrecto(palabraConsulta,arbolAVL)){
            System.out.println(palabraConsulta + " está bien escrita.");
            return;
        }
        Lista<String> candidatas = diccionario.cambiaPalabra(palabraConsulta);
        Lista<String> sugerencias = diccionario.consultar(candidatas, arbolAVL);
        if (sugerencias.esVacia()){
            System.out.println("No hemos encontrado sugerencias para esa palabra");
        }else {
            System.out.println("Las sugerencias son:");
            System.out.println(sugerencias + "\n");
        }
        if (preguntaAgregar(palabraConsulta)){
            System.out.println(palabraConsulta+" fue añadida con éxito al diccionario");
        }
        guardar();
    }
}
