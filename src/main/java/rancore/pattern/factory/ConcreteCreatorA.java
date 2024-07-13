package rancore.pattern.factory;

public class ConcreteCreatorA extends Creator{
    @Override
    Product factoryMethod() {
        return new ConcreteProductA();
    }
}
