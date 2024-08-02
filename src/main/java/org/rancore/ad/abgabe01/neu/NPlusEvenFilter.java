package org.rancore.ad.abgabe01.neu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NPlusEvenFilter {
    public static void filterAndPrint(List<Integer> list)  {
        list = list.stream().filter(n -> n > 0 && n % 2 == 0)
                .toList();
        for (Integer n : list) {
            System.out.print(n + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        List<Integer> integers = new ArrayList<>();

        integers = Helper.getIntegersListFromStdIn();
        filterAndPrint(integers);
    }
}
