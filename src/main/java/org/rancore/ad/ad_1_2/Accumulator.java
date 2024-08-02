package org.rancore.ad.ad_1_2;

public interface Accumulator {

    void addDataValue(double d);

    double mean();

    String toString();
}
