package rancore.annotation;

public interface ILogExecTime {
    @LogExecTime(message = "Komplexe Operation dauerte:\t\t")
    void complexOperation();

    @LogExecTime(logToConsole = false)
    String silentOperation();
}