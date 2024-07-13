package org.rancore.annotation;


public class LogExecTimeDemo implements ILogExecTime {
    public static void main(String[] args) {
        ILogExecTime service = LogExecTimeHandler.createProxy(new LogExecTimeDemo(), ILogExecTime.class);

        service.complexOperation();
        String result = service.silentOperation();
        System.out.println("Ergebnis der stillen Operation:\t" + result);
    }

    // Simuliere eine komplexe Operation
    @Override
    public void complexOperation() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Diese Operation wird gemessen, aber nicht geloggt
    @Override
    public String silentOperation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "Fertig";
    }
}