package dict;

/**
 * <h1>Clase palabra</h1>
 * Aquí se modelan los elementos de tipo palabra que serán almacenados en un diccionario, son tambien las componentes
 * principales de éstos, por lo que resultan escenciales.
 * @author Ariel Merino and Armando Aquino
 * @version 1
 */
public class Palabra implements Comparable<Palabra> {

    String word;

    public Palabra(String word){
        this.word = word;
    }

    @Override
    public int compareTo(Palabra o) {
        return word.compareTo(o.word);
    }

    static String removerTildes(String cadena) {
        return cadena.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u");
    }

    public int sonSimilares(Palabra palabra){
        return word.toLowerCase().compareTo(palabra.word.toLowerCase());
    }
}
