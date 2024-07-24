package org.rancore.annotation;

/**
 * Exception when the operation fails after a number of attempts.
 */
public class RetryFailedException extends RuntimeException {
    private int attempts;

    public RetryFailedException() {
        super("Retry failed");
    }

    public RetryFailedException(int attempts) {
        super("Operation failed after " + attempts + " attempts");
        this.attempts = attempts;
    }

    public RetryFailedException(int attempts, String message) {
        super(message);
        this.attempts = attempts;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }
}