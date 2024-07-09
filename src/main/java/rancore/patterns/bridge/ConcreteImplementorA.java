package rancore.patterns.bridge;

public class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImp() {
        System.out.println(this.getClass().getName() + " operation");
    }
}
