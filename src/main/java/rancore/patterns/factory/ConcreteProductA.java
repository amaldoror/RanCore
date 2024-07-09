package rancore.patterns.factory;

public class ConcreteProductA extends Product{
    @Override
    void use() {
        System.out.println(this.getClass().getName());
    }
}
