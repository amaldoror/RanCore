package org.rancore.ad.ad_1_5;

public interface UF {
    boolean connected(int p, int q);

    // Komponentenbezeichner für p
    int find(int p);

    // Verbindet zwei Komponenten zu einer Komponente
    void union(int p, int q);

    // Zaehlt die Anzahl der Komponenten
    int count();

}
