package org.rancore.ad.ad_2_1;

import javax.management.ReflectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SortClassWrapper {
    private static Class<?> getClass(Sorter alg){
        return alg.getSorterClass();
    }
    public static <T extends Comparable<? super T>> void doSort(Sorter sorter, T[] a) throws ReflectionException {
        Class<?> sorterClass = getClass(sorter);
        Method sort = null;
        try {
            sort = sorterClass.getMethod("sort", Comparable[].class );
            sort.invoke(null, new Object[]{a});
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException(e);
        }
        // weitere Exceptions
    }
}