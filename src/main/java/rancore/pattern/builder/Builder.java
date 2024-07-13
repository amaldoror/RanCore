package rancore.pattern.builder;

public abstract class Builder {
    protected Product product = new Product();

    public abstract void buildPart1();
    public abstract void buildPart2();
    public Product getResult() { return product; }
}
