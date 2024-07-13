package org.rancore.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Führt automatische Wiederholungsversuche durch.<br>
 * <code>maxAttempts</code> default = 3<br>
 * <code>delay</code> default = 1000 ms<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Retry {
    int maxAttempts() default 3;
    long delay() default 1000; // Verzögerung in Millisekunden
}