package dict;

public class Palabra implements Comparable<Palabra> {

    String word;

    public Palabra(String word){
        this.word = word;
    }



    @Override
    public int compareTo(Palabra o) {
        return removerTildes(word.toLowerCase()) == removerTildes(o.word.toLowerCase()) ? 1: -1;
    }

    static String removerTildes(String cadena) {
        return cadena.replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u");
    }
}
