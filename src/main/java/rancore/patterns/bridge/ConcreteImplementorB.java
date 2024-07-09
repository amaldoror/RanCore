package rancore.patterns.bridge;

public class ConcreteImplementorB implements Implementor {
    @Override
    public void operationImp() {
        System.out.println(this.getClass().getName() + " operation");
    }
}
