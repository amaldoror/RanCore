package org.rancore.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
                } catch (InvocationTargetException e) {
                    attempts++;
                    Throwable cause = e.getCause();
                    if (cause instanceof RetryFailedException) {
                        RetryFailedException rfe = (RetryFailedException) cause;
                        rfe.setAttempts(attempts);
                        if (attempts >= retry.maxAttempts()) {
                            throw rfe;
                        }
                    } else {
                        throw cause;
                    }
                    Thread.sleep(retry.delay());
                }
            }
        }
        // If no @Retry annotation is present, we simply call the method
        return method.invoke(target, args);
    }

    /**
     * Erstellt ein Proxy-Objekt, das die Retry-Annotation verarbeitet.
     *
     * @param target Das Zielobjekt, dessen Methoden annotiert sind.
     * @param interfaceType Das Interface, das das Zielobjekt implementiert
     * @param <T> Der Typ des Interfaces
     * @return Ein Proxy-Objekt, das die Annotation verarbeitet
     */
    public static <T> T createProxy(T target, Class<T> interfaceType) {
        Object proxy = Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[] { interfaceType },
                new RetryHandler(target)
        );

        if (interfaceType.isInstance(proxy)) {
            return interfaceType.cast(proxy);
        } else {
            throw new IllegalStateException("Proxy does not implement the expected interface");
        }
    }
}