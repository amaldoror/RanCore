package org.rancore.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>Ein InvocationHandler, der die Ausführungszeit von Methoden misst und protokolliert,
 * die mit der {@Retry} Annotation versehen sind.</p>
 *
 * <p>Dieser Handler kann verwendet werden, um ein Proxy-Objekt zu erstellen, das
 * die Ausführungszeit von annotierten Methoden automatisch misst und protokolliert.</p>
 *
 * <p><b>Verwendung:</b></p>
 * <code>
 * IMyInterface proxy = RetryHandler.createProxy(myObject, IMyInterface.class);<br>
 * proxy.someMethod(); // Ausführungszeit wird gemessen und protokolliert
 * </code>
 */
public class RetryHandler implements InvocationHandler {
    private final Object target;

    public RetryHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Retry retry = method.getAnnotation(Retry.class);
        if (retry != null) {
            int attempts = 0;
            while (attempts < retry.maxAttempts()) {
                try {
                    return method.invoke(target, args);
                } catch (Exception e) {
                    attempts++;
                    if (attempts >= retry.maxAttempts()) {
                        throw e.getCause();
                    }
                    Thread.sleep(retry.delay());
                }
            }
        }
        // Wenn keine @Retry-Annotation vorhanden ist, rufen wir die Methode einfach auf
        return method.invoke(target, args);
    }
}