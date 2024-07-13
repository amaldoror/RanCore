package org.rancore.pattern.builder;

public class Product {
    private String part1;
    private String part2;

    public void setPart1(String part1) { this.part1 = part1; }
    public void setPart2(String part2) { this.part2 = part2; }

    public void showProduct() {
        System.out.println("Product with " + part1 + " and " + part2);
    }
}
