package org.rancore.pattern.abstract_factory;

public class ConcreteFactory1 implements AbstractFactory{
    @Override
    public ProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public ProductB createProductB() {
        return new ProductB1();
    }
}
