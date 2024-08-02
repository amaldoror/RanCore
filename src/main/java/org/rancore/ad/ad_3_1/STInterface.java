package org.rancore.ad.ad_3_1;


public interface STInterface<Key,Val> {
    default void delete(Key key) {
        if (key ==null) throw new IllegalArgumentException("Schlüssel darf nicht null sein!");
        put(key,null);
    }

    default boolean contains(Key key){return get(key)!=null;}

    default boolean isEmpty(){return size()==0;}

    void put(Key word, Val i);

    Val get(Key word);

    String[] keys();

    int size();
}