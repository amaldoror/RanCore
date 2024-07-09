package rancore.patterns.decorator;

public abstract class Decorator {

    protected Component component;

    public Decorator(Component component) { this.component = component; }

    public void operation() { component.operation(); }
}
