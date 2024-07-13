package org.rancore.pattern.factory;

public class ConcreteProductA extends Product{
    @Override
    void use() {
        System.out.println(this.getClass().getName());
    }
}
