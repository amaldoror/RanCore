package rancore.patterns.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void operation() { System.out.println( this.getClass().getName() + " operation"); }
}
