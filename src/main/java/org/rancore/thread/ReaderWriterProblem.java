package org.rancore.thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// TODO: Überarbeiten des Readers und des Writers
/**
 * Die Klasse ReaderWriterProblem implementiert das Leser-Schreiber-Problem.
 * <p>
 * Das Ziel ist es, mehrere Leser und Schreiber so zu koordinieren, dass Leser gleichzeitig auf Ressourcen zugreifen können,
 * Schreiber jedoch exklusiven Zugriff haben. Der Algorithmus verwendet Synchronisationsmechanismen, um Konflikte zu vermeiden.
 */
public class ReaderWriterProblem {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static int sharedResource = 0;

    public static void main(String[] args) {
        Thread readerThread1 = new Thread(new Reader());
        Thread readerThread2 = new Thread(new Reader());
        Thread writerThread = new Thread(new Writer());

        readerThread1.start();
        readerThread2.start();
        writerThread.start();
    }

    static class Reader implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.readLock().lock();
                    System.out.println(Thread.currentThread().getName() + " liest: " + sharedResource);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.readLock().unlock();
                }
            }
        }
    }

    static class Writer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    lock.writeLock().lock();
                    sharedResource++;
                    System.out.println(Thread.currentThread().getName() + " schreibt: " + sharedResource);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.writeLock().unlock();
                }
            }
        }
    }
}
