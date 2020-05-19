package dict;

public class Palabra implements Comparable {

    String word;

    public Palabra(String word){
        this.word = word;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
