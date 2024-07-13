package rancore.pattern.factory;

public class ConcreteCreatorB extends Creator{
    @Override
    Product factoryMethod() {
        return new ConcreteProductB();
    }
}
