package org.rancore.pattern.abstract_factory;

public class ProductA1 implements ProductA{
    @Override
    public void display() {
        System.out.println(this.getClass().getName());
    }
}
