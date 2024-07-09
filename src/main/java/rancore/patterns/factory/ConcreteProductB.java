package rancore.patterns.factory;

public class ConcreteProductB extends Product{
    @Override
    void use() {
        System.out.println(this.getClass().getName());
    }
}
