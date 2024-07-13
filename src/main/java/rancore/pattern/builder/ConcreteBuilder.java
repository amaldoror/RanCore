package rancore.pattern.builder;

public class ConcreteBuilder extends Builder{
    @Override
    public void buildPart1() {
        product.setPart1("Part1");
    }

    @Override
    public void buildPart2() {
        product.setPart2("Part2");
    }
}
