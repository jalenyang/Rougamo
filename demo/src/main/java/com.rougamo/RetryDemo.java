package com.rougamo;

import com.github.rholder.retry.*;
import com.google.common.base.Predicate;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class RetryDemo {


    public static void main(String[] args) throws ExecutionException, RetryException {
        int interval = 10;
        int times = 10;
        Retryer<Integer> retryManager = RetryerBuilder.<Integer>newBuilder().retryIfResult(new Predicate<Integer>() {
            public boolean apply(@Nullable Integer integer) {
                if (integer == 1) {
                    return true;
                }
                return false;
            }
        }).retryIfRuntimeException().withWaitStrategy(WaitStrategies.fixedWait(interval, TimeUnit.SECONDS)).withStopStrategy(StopStrategies.stopAfterAttempt(times)).build();

        retryManager.call(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("retrying.......");
//                throw new IllegalStateException("test");
////                if(Math.random()>0.5){
////                    return 0;
////                }
                return 1;
            }
        });
    }
}
