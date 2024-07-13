package org.rancore.playground;
import org.rancore.math.*;

public class Playground {
    public static void main(String[] args) {

        int zahl = 10;
        double fakultaet;
        boolean aufsteigend = true;
        if(!aufsteigend){
            for (int i = zahl; i>0; i--){
                fakultaet = Factorial.factorial(i);
                System.out.println(fakultaet);
            }
        } else {
            for (int i = 1; i<zahl; i++){
                fakultaet = Factorial.factorial(i);
                System.out.println(fakultaet);
            }
        }

        Euler.euler();
        //Euler2.euler(100);


    }
}
