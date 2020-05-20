package dict;

import util.Lista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Diccionario {

    public Lista<String> leeTXT(){
        Lista<String> palabrasTXT = new Lista<>();
        try {
            Scanner input = new Scanner( new File("diccionario.txt"));
            while (input.hasNextLine()){
                palabrasTXT.agregar(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return palabrasTXT;
    }
}
