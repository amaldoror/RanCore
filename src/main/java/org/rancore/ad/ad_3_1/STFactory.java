package org.rancore.ad.ad_3_1;


public class STFactory {

    public static STInterface<String, Integer> getSTInstance(int type) {
        return switch (type) {
            case 1 -> null;
            case 2 -> {
                System.out.println("No implementation for type " + type);
                yield null;
            }
            default -> new SequentialSearchST<>();
        };
    }
}
