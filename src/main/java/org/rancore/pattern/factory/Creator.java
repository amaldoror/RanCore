package org.rancore.pattern.factory;

public abstract class Creator {
    abstract Product factoryMethod();

    void someOperation() {
        Product product = factoryMethod();
        product.use();
    }
}
