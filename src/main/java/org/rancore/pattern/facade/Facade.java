package org.rancore.pattern.facade;

public class Facade {
    private final Subsystem1 subsystem1 = new Subsystem1();
    private final Subsystem2 subsystem2 = new Subsystem2();

    void operation() {
        subsystem1.operation();
        subsystem2.operation();
    }
}
