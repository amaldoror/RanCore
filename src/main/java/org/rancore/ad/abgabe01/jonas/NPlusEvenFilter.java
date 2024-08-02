package org.rancore.ad.abgabe01.jonas;

import java.io.IOException;
import java.util.Scanner;

/**
 * Filtert einen Eingabe-Stream und sortiert alle ungeraden und nicht-Ganzzahlen aus.
 */
public class NPlusEvenFilter {
    public static void filterAndPrint()  {
        //Filter alle ungeraden Objekte heraus.
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){

                int num;
                String input = scanner.next();
                try {
                    num = Integer.parseInt(input);
                    if(num > 0 && num % 2 == 0) {
                        System.out.println(num + " ");
                    }
                }
                catch (NumberFormatException nfe) {
                    System.out.println(nfe.getMessage());
                }

        }
        scanner.close();
        /*
        list = list.stream().filter(n -> n > 0 && n % 2 == 0)
                .toList();
        for (Integer n : list) {
            System.out.print(n + " ");
        }
        */
    }

    public static void main(String[] args) throws IOException {
        filterAndPrint();
    }
}
