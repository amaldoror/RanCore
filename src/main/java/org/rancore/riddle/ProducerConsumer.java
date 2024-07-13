package org.rancore.riddle;

import java.util.LinkedList;
import java.util.Queue;

// TODO: Überarbeiten des Producers und des Consumers
/**
 * Die Klasse ProducerConsumer implementiert das Erzeuger-Verbraucher-Problem mit Threads.
 * <p>
 * Das Ziel ist es, einen gemeinsamen Speicherbereich (Puffer) zu verwalten, in dem Erzeuger (Producer) Daten produzieren und
 * Verbraucher (Consumer) Daten konsumieren, ohne dass Daten verloren gehen oder doppelt verarbeitet werden.
 * Der Algorithmus verwendet Warteschlangen und Synchronisationsmechanismen zur Verwaltung des Zugriffs auf den Puffer.
 */
public class ProducerConsumer {
    private static final int BUFFER_SIZE = 10;
    private static final Queue<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread = new Thread(new Consumer());

        producerThread.start();
        consumerThread.start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            int value = 0;
            while (true) {
                synchronized (buffer) {
                    while (buffer.size() == BUFFER_SIZE) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    buffer.add(value);
                    System.out.println("Produziert: " + value);
                    value++;
                    buffer.notifyAll();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int value = buffer.poll();
                    System.out.println("Konsumiert: " + value);
                    buffer.notifyAll();
                }
            }
        }
    }
}
