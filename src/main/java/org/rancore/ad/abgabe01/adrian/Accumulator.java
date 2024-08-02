package org.rancore.ad.abgabe01.adrian;

public interface Accumulator {
	
	void addDataValue(double val);
	double mean();
	String toString();
}