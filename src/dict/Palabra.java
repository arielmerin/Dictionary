package dict;

public class Palabra implements Comparable<Palabra> {

    String word;

    public Palabra(String word){
        this.word = word;
    }

    @Override
    public int compareTo(Palabra o) {
        return 0;
    }
}
