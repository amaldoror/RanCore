package org.rancore.ad.abgabe01.jonas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Kleine Helper-Klasse um Code-Dopplungen zu vermeiden.
 */
//Ist das richtig so oder den Code jeweils einzeln in die Klasse? Ist ja eigentlich kein zusammenhängendes Projekt.
public class Helper {
    public static ArrayList<Integer> getIntegersListFromStdIn() {
        ArrayList<Integer> integers = new ArrayList<>();

        //Buffered Reader liest Output ein. (Alles was über System.in-Stream kommt)
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //Einzelne Zahlen rausfiltern aus String.
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(line);
                // Extrahiere Zahlen und konvertiere sie in Integer
                while (matcher.find()) {
                    String match = matcher.group();
                    integers.add(Integer.parseInt(match));
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return integers;
    }
}
