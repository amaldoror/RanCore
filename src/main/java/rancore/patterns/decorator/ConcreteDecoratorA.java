package rancore.patterns.decorator;

public class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) { super(component); }

    public void operation() {
        super.operation();
        addedBehavior();
    }

    void addedBehavior() { System.out.println( this.getClass().getName() + " added behavior"); }
}
