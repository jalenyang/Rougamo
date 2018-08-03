package com.pork;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class RetryDemo {
    static class RetryAbleException extends Exception {

    }


    public static void main(String[] args) throws ExecutionException, RetryException {
        int interval = 10;
        int times = 10;
        final long startTime = System.currentTimeMillis();
        Retryer<Throwable> retryManager = RetryerBuilder.<Throwable>newBuilder().retryIfException(new Predicate<Throwable>() {
            public boolean apply(Throwable throwable) {
                if (startTime - System.currentTimeMillis() < 20000 && throwable instanceof RetryAbleException) {
                    return true;
                }
                return false;
            }
        }).retryIfRuntimeException().retryIfExceptionOfType(RetryAbleException.class).
                withWaitStrategy(WaitStrategies.fixedWait(interval, TimeUnit.SECONDS)).
                withStopStrategy(StopStrategies.stopAfterAttempt(times)).build();

        retryManager.call(new Callable<Throwable>() {
            public Throwable call() throws Exception {
                System.out.println("retrying.......");
                test();
                return new Exception("");
            }
        });
    }


    public static void test() {
        try {
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("eeee"+e);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("runtime exception");
        }
    }
}
