package org.rancore.pattern.decorator;

public class ConcreteComponent implements Component {
    @Override
    public void operation() { System.out.println( this.getClass().getName() + " operation"); }
}
