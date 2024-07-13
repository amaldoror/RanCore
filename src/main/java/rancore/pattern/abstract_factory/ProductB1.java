package rancore.pattern.abstract_factory;

public class ProductB1 implements ProductB {
    @Override
    public void display() {
        System.out.println(this.getClass().getName());
    }
}
