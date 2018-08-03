package org.pork.kafka;

import java.util.concurrent.*;


public class ThreadPoolDemo {
    private Executor singleThreadExecutor = Executors.newSingleThreadExecutor();
    private Executor fixedThreadPoolExecutor = Executors.newFixedThreadPool(3);
    private Executor cachedThreadPoolExecutor = Executors.newCachedThreadPool();


    public void single() {
        int num = 0;
        while (num++ < 3) {
            singleThreadExecutor.execute(new Task());
        }
    }

    public void fixed() {
        int num = 0;
        while (num++ < 3) {
            fixedThreadPoolExecutor.execute(new Task());
        }
    }

    public void cached() {
        int num = 0;
        while (num++ < 3) {
            cachedThreadPoolExecutor.execute(new Task());
        }
    }

    class Task implements Runnable {
        @Override
        public void run() {
            System.out.println(String.format("Thread %s working now...", Thread.currentThread().getName()));
            new LoadTest().test();
        }
    }

    static class CallableTask implements  Callable<String>{
        @Override
        public String call() throws Exception {
            return String.format("CallableTask %s working now...", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ThreadPoolDemo demo = new ThreadPoolDemo();
        demo.single();
        demo.fixed();
        demo.cached();

        FutureTask<String> futureTask= new FutureTask<>(new CallableTask());
        new Thread(futureTask).start();
        System.out.println(futureTask.get(1, TimeUnit.MINUTES));
    }
}
