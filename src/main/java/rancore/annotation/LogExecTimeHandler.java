package rancore.annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogExecTimeHandler implements InvocationHandler {
    private final Object target;

    private LogExecTimeHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogExecTime annotation = method.getAnnotation(LogExecTime.class);
        if (annotation != null) {
            long startTime = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            if (annotation.logToConsole()) {
                String message = annotation.message().isEmpty()
                        ? "Method execution time"
                        : annotation.message();
                System.out.printf("%s%d ms%n", message, executionTime);
            }

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    /**
     * Erstellt ein Proxy-Objekt, das die LogExecutionTime-Annotation verarbeitet.
     *
     * @param target Das Zielobjekt, dessen Methoden überwacht werden sollen
     * @param interfaceType Das Interface, das das Zielobjekt implementiert
     * @param <T> Der Typ des Interfaces
     * @return Ein Proxy-Objekt, das die LogExecutionTime-Annotation verarbeitet
     */
    public static <T> T createProxy(T target, Class<T> interfaceType) {
        Object proxy = Proxy.newProxyInstance(
                interfaceType.getClassLoader(),
                new Class<?>[] { interfaceType },
                new LogExecTimeHandler(target)
        );

        if (interfaceType.isInstance(proxy)) {
            return interfaceType.cast(proxy);
        } else {
            throw new IllegalStateException("Proxy does not implement the expected interface");
        }
    }
}