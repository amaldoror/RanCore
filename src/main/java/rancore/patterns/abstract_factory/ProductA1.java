package rancore.patterns.abstract_factory;

public class ProductA1 implements ProductA{
    @Override
    public void display() {
        System.out.println(this.getClass().getName());
    }
}