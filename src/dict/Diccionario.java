package dict;

import com.sun.corba.se.impl.interceptors.PICurrent;
import util.Lista;
import util.Pila;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Diccionario {

    public Pila<String> leeTXT(){
        Pila<String> palabrasTXT = new Pila();
        try {
            Scanner input = new Scanner( new File("diccionario.txt"));
            while (input.hasNextLine()){
                palabrasTXT.push(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return palabrasTXT;
    }
}
