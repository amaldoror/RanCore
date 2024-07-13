package org.rancore.pattern.bridge;

public class ConcreteImplementorA implements Implementor {
    @Override
    public void operationImp() {
        System.out.println(this.getClass().getName() + " operation");
    }
}
