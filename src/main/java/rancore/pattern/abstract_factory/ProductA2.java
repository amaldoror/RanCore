package rancore.pattern.abstract_factory;

public class ProductA2 implements ProductA{
    @Override
    public void display() {
        System.out.println(this.getClass().getName());
    }
}
