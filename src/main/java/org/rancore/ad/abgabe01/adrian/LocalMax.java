package org.rancore.ad.abgabe01.adrian;

import java.util.Arrays;

public class LocalMax {

    public static void main(String[] args) {
        int[] ary1 = {1, 61, 89, 75, 16, 33, 89, 59, 28, 3, 3, 97, 61, 85, 47, 38, 78, 7, 6, 15};
        int[] ary2 = {1, 61, 16, 75, 89, 133, 89, 59, 28, 3, 3, 97, 61, 85, 47, 38, 78, 7, 6, 15};
        int[] ary3 = {99, 1, 61, 89, 75, 16, 33, 89, 59, 28, 3, 3, 97, 61, 85, 47, 38, 78, 7, 6, 15};

        int[] result1 = findLocalMax(ary1, 0, 20, 2);
        int[] result2 = findLocalMax(ary2, 0, 20, 3);
        int[] result3 = findLocalMax(ary3, 0, 20, 1);
        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.toString(result2));
        System.out.println(Arrays.toString(result3));
    }


//System.out.println("Ergebnis:" + printArray(array, index - radius, index + radius, index));
    /**
	 * Findet ein lokales Maximum in einem Array innerhalb eines bestimmten Suchbereichs.
     *
     * @param array Eingabe Array
     * @param lo Minimaler Index des Suchbereiches
     * @param hi Maximaler Index des Suchbereiches
     * @param radius Suchradis um den Mittelpunkt der Werte
     * @return Ausgabe des gefundenen Bereiches, falls Algorithmus erfolgreich. Sonst null
     */
    public static int[] findLocalMax(int[] array, int lo, int hi, int radius){
        
		// Berechnung des Mittelpunkts des Suchbereichs
        int index = lo + (hi - lo) / 2;
		
		// Vorab als true setzen, falls keine Bedingungen zur Änderung erfüllt sind
        boolean localMaxFound = true;


        // Abbruchbedingung: Bereich ist kleiner als der doppelte Radius
        if (hi - lo < ((radius*2))){
            System.out.println("Ergebnis: [Null]");
            return null;
        }
		
		// Linken Bereich überprüfen
        for (int i = index - radius; i < index; i++){
            if (array[i] >= array[i+1]){
                localMaxFound = false;
                break;
            }
        }

		// Rechten Bereich überprüfen
        for (int i = index; i < index + radius; i++){
            if (array[i] <= array[i+1]){
                localMaxFound = false;
                break;
            }
        }

		// Ergebnisrückgabe, wenn lokales Maximum gefunden wurde
        if(localMaxFound) {
			return Arrays.copyOfRange(array, index - radius, index + radius+1);
		}

		// Lokales Minimum links und rechts finden und vergleichen.
		// Entscheidung treffen, welcher Bereich als nächstes durchsucht werden soll.
		int minLeft = array[lo];
        int minRight = array[hi-1];

        for(int i = lo; i < index; i++){
           if (array[i] < minLeft){
               minLeft = array[i];
           }
        }

        for(int i = index + 1; i < hi; i++){
            if (array[i] < minRight){
                minRight = array[i];
            }
        }

        return (minLeft <= minRight) ?  findLocalMax(array, lo, index, radius) : findLocalMax(array, index, hi, radius);
    }

    /**
     * Erstellt eine Zeichenfolge zur Ausgabe des Arrays mit markiertem lokalen Maximum
	 * 
     * @param array Eingabe Array mit Werten
     * @param start anfang des gefundenen Bereiches
     * @param end ende des gefundenen Bereiches
     * @param index Mitte des Suchbereiches
     * @return String zur Ausgabe auf der Konsole
     */
	public static String printArray(int[] array, int start, int end, int index) {
		if (array == null) {
			return "Kein lokales Maximum gefunden.";
		}

		StringBuilder result = new StringBuilder("[");

		for (int i = start; i < end; i++) {
			if (i == end - 1) {
				result.append(String.format("%d", array[i]));
			} else if (i == index) {
				result.append(String.format("(%d),", array[i]));
			} else {
				result.append(String.format("%d,", array[i]));
			}
		}
		result.append("]");

		return "Ergebnis:" + result;
	}

}
