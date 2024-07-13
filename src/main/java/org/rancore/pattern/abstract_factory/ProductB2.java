package org.rancore.pattern.abstract_factory;

public class ProductB2 implements ProductB {
    @Override
    public void display() {
        System.out.println(this.getClass().getName());
    }
}
