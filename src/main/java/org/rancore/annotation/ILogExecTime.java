package org.rancore.annotation;

/**
 * Diese Schnittstelle bietet die Methoden an, deren Ausführungszeit geloggt werden soll.<br>
 * <br>
 * Verwendung mit Interface<br><br>
 * Vorteile:<br>
 * - Bessere Trennung von Schnittstelle und Implementierung<br>
 * - Einfacher zu mocken in Tests<br>
 * - Ermöglicht einfachere Erweiterungen oder alternative Implementierungen<br>
 * - Erleichtert die Verwendung von Dependency Injection<br>
 * <br>
 * Nachteile:<br>
 * - Mehr Code<br>
 * - Kann übertrieben sein für einfache Anwendungsfälle<br>
 * <br>
 * Es ist auch möglich, die Annotation direkt in der Implementierung zu verwenden.<br>
 */
public interface ILogExecTime {
    @LogExecTime(message = "Komplexe Operation dauerte:\t\t")
    void complexOperation();

    @LogExecTime(logToConsole = false)
    String silentOperation();
}