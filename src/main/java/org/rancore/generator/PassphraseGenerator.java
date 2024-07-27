package org.rancore.generator;

import org.rancore.annotation.Retry;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 */
public class PassphraseGenerator {

    public static void main(String[] args) {
        int wordCount = 4;
        Long seed = 12345L;

        String passphrase = generatePassphrase(wordCount, seed);
        System.out.println("Generated Passphrase: " + passphrase);

        String passphrase2 = generatePassphrase(wordCount, null);
        System.out.println("Generated Passphrase: " + passphrase2);
    }

    @Retry
    public static String generatePassphrase(int wordCount, Long seed) {
        if (wordCount < 1) throw new IllegalArgumentException("Word count must be greater than 0");

        Random random = (seed != null) ? new Random(seed) : new SecureRandom();
        StringBuilder passphrase = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            int randomIndex = random.nextInt(WORD_LIST.length);
            passphrase.append(WORD_LIST[randomIndex]);
            if (i < wordCount - 1) {
                passphrase.append("-");
            }
        }

        return passphrase.toString();
    }

    private static final String[] WORD_LIST = {
            "apfel", "orange", "banane", "traube", "pfirsich", "kirsche", "pflaume", "mango", "kiwi", "erdbeere",
            "ananas", "birne", "zitrone", "himbeere", "blaubeere", "melone", "passionsfrucht", "papaya", "granatapfel", "kokosnuss",
            "hund", "katze", "maus", "elefant", "tiger", "löwe", "pferd", "schwein", "kuh", "schaf",
            "rot", "blau", "grün", "gelb", "schwarz", "weiß", "lila", "pink", "orange", "braun",
            "auto", "haus", "stuhl", "tisch", "lampe", "uhr", "telefon", "buch", "fahrrad", "flugzeug",
            "lehrer", "arzt", "ingenieur", "polizist", "feuerwehrmann", "pilot", "kellner", "koch", "wissenschaftler", "anwalt", "architekt",
            "pizza", "brot", "käse", "milch", "wasser", "bier", "wein", "kaffee", "tee", "saft",
            "sommer", "herbst", "winter", "frühling", "montag", "dienstag", "mittwoch", "donnerstag", "freitag", "samstag", "sonntag",
            "berg", "fluss", "meer", "see", "wald", "insel", "wüste", "tal", "hügel", "strand",
            "computer", "fernseher", "radio", "kamera", "drucker", "scanner", "tablet", "smartphone", "kopfhörer", "mikrofon",
            "buchstabe", "zahl", "freund", "familie", "schule", "universität", "stadt", "dorf", "garten", "park",
            "straße", "brücke", "gebäude", "restaurant", "café", "markt", "supermarkt", "geschäft", "kino", "theater",
            "museum", "bibliothek", "hotel", "bank", "krankenhaus", "apotheke", "bäckerei", "metzgerei", "friseur", "schwimmbad",
            "spielplatz", "stadion", "büro", "werkstatt", "fabrik", "lager", "tankstelle", "zoo", "aquarium", "freizeitpark",
            "wald", "gebirge", "see", "strand", "insel", "fluss", "wüste", "regenwald", "sumpf", "steilküste",
            "nebel", "regen", "schnee", "sonne", "wetter", "wolke", "sturm", "blitz", "donner", "wind",
            "blume", "baum", "strauch", "gras", "moos", "pilz", "alge", "blatt", "ast", "wurzel",
            "affe", "bär", "vogel", "fisch", "frosch", "giraffe", "hase", "igel", "jaguar", "känguru",
            "lamm", "marder", "nilpferd", "otter", "pinguin", "qualle", "reh", "schildkröte", "taube", "unke",
            "vulkan", "wasserfall", "yxilon", "zeppelin", "zug", "ananas", "bikini", "diamant", "essen", "ferien",
            "grusel", "hexerei", "insel", "judo", "karate", "lehrer", "miete", "nacht", "ofen", "pinsel",
            "quatsch", "rätsel", "spinne", "turm", "ufo", "versteck", "wetter", "xylophon", "yoga", "zeitung",
            "äther", "überraschung", "öffnung", "zäune", "ämter", "übung", "örtlich", "züchten", "ähnlich", "überweisen"
    };
}
