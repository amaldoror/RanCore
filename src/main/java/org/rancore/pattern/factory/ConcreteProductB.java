package org.rancore.pattern.factory;

public class ConcreteProductB extends Product{
    @Override
    void use() {
        System.out.println(this.getClass().getName());
    }
}
