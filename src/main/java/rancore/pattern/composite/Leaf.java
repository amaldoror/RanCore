package rancore.pattern.composite;

public class Leaf implements Component {
    @Override
    public void operation() {
        System.out.println( this.getClass().getName() + " operation");
    }
}
