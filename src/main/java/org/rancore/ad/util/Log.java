package org.rancore.ad.util;

public class Log {
    public static final System.Logger logger = System.getLogger("Logger");
    public static final String msg01 = "Info";
    public static final String msg02 = "Warning";
    public static final String msg03 = "Error";
    public static final String msg04 = "Debug";
    public static final String msg05 = "Trace";

    public static final System.Logger.Level info = System.Logger.Level.INFO;
    public static final System.Logger.Level warning = System.Logger.Level.WARNING;
    public static final System.Logger.Level error = System.Logger.Level.ERROR;
    public static final System.Logger.Level debug = System.Logger.Level.DEBUG;
    public static final System.Logger.Level trace = System.Logger.Level.TRACE;

    public static void main(String[] args) {
        logger.log(info, msg01);
    }
}
