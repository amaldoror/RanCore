package org.rancore.ad.abgabe01.jonas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Liest Zahlen aus einem Eingabe-Stream aus und gibt diese an den entsprechenden (und dafür erstellten)
 * Akkumulator weiter.
 */
public class AccumulatorClient {

    /**
     * Nimmt drei oder ein Argument. Und setzt dann Klassenbeschreibung um.
     * @param args
     * Argument 1: Legt fest welcher Akkumulator verwendet werden soll. (0 = SimpleAccumulator, 1-9 = VisualAccumulator)
     * Argument 2: Gibt an wie viele Werte sich im Stream befinden.
     * Argument 3. Gibt den maximal Wert an.
     */
    public static void main(String[] args) {
        //Argumente Verarbeitung.
        int version,trials=0,maxValue=Integer.MIN_VALUE;
        List<Integer> integers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){

            int num;
            String input = scanner.next();
            try {
                num = Integer.parseInt(input);
                integers.add(num);
                trials++;
                maxValue = Math.max(num, maxValue);

            }
            catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }

        }
        if (args.length == 3) {
            version = Integer.parseInt(args[0]);
            trials = Integer.parseInt(args[1]);
            maxValue = Integer.parseInt(args[2]);

        }
        else if (args.length == 1) {
            version = Integer.parseInt(args[0]);
        }
        else {
            System.out.println("""
                    Usage: java AccumulatorClient <version> <trials> <maxValue> \

                     OR \

                     java AccumulatorClient <version> <trials>""");
            return;
        }

        //Welche Version soll verwendet werden.
        if(version == 0) {
            SimpleAccumulator simAccum = new SimpleAccumulator();
            for (Integer value : integers) {
                simAccum.addDataValue(value);
            }
        }
        else {
            VisualAccumulator visAccum = new VisualAccumulator(trials,maxValue);
            for (Integer value : integers) {
                visAccum.addDataValue(value);
            }
        }
    }
}
