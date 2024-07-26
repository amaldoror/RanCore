package org.rancore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Diese Annotation wird verwendet, um die Ausführungszeit einer Methode zu messen und zu protokollieren.
 * Sie kann auf Methoden angewendet werden und benötigt einen Proxy, um die Funktionalität zu implementieren.<br>
 * <br>
 * <p>Verwendung:</p>
 * {@LogExecTime} <br>
 * public void someMethod() { <br>
 * // Methodeninhalt <br>
 * }<br>
 * <p>Die Ausführungszeit wird in Millisekunden gemessen und in der Konsole ausgegeben.</p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecTime {
    /**
     * <p>Bestimmt, ob die Ausführungszeit in der Konsole ausgegeben werden soll.</p>
     * <p>Standardmäßig auf <code>true</code> gesetzt.</p>
     * @return boolean Wert, der angibt, ob die Zeit ausgegeben werden soll
     */
    boolean logToConsole() default true;

    /**
     * <p>Legt eine benutzerdefinierte Nachricht fest, die vor der Zeitmessung ausgegeben wird.</p>
     * @return String mit der benutzerdefinierten Nachricht
     */
    String message() default "";
}