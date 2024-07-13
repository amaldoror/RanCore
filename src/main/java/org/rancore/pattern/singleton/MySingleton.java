package org.rancore.pattern.singleton;

public class MySingleton {
    private static MySingleton instance;

    private MySingleton() {}

    private static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
            System.out.println("Instance created");
        } else {
            System.out.println("Instance already exists");
            return null;
        }
        return instance;
    }

    public static void main(String[] args) {
        MySingleton s1 = MySingleton.getInstance();
        MySingleton s2 = MySingleton.getInstance();
        MySingleton s3 = MySingleton.getInstance();

        System.out.println();

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);
        System.out.println("s3: " + s3);

        System.out.println();

        System.out.println("s1 equals s2: " + s1.equals(s2));
        System.out.println("s1 equals s3: " + s1.equals(s3));
    }
}
