package com.jmtech.core.ui.actions;

import java.util.function.BooleanSupplier;

public class WaitUtils {
    /**
     * Sleeps for the specified number of milliseconds.
     */
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sleep interrupted", e);
        }
    }

    /**
     * Sleeps for the specified number of seconds.
     */
    public void waitForSeconds(long seconds) {
        sleep(seconds * 1000);
    }

    /**
     * Waits until the given condition is true or the timeout is reached.
     * 
     * @param condition         the condition to check
     * @param maxWaitTime       the maximum time to wait in milliseconds
     * @param retryIntervalTime how often to check the condition
     * @return true if the condition was met, false if timed out
     */
    public boolean waitForCondition(BooleanSupplier condition, long maxWaitTime, long retryIntervalTime) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < maxWaitTime) {
            if (condition.getAsBoolean()) {
                return true;
            }
            sleep(retryIntervalTime);
        }
        return false;
    }
}