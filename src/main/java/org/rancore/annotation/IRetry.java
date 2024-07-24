package org.rancore.annotation;

public interface IRetry {
    @Retry
    void retryMethod() throws RetryFailedException;
}
