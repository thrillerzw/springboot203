package com.example.springboot.common.thread;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TestSubmit {
    public static void main(String[] args) {
//        ThreadFactory threadFactory=new Threadfactorybuilder
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2));
        CallableTast callableTast1 = new CallableTast();
        CallableTast callableTast2 = new CallableTast();
        CallableTast callableTast3 = new CallableTast();
        CallableTast callableTast4 = new CallableTast();
        CallableTast callableTast5 = new CallableTast();
        Future<String> futureResult1 = executorService.submit(callableTast1);
        System.out.println("加入1个后，LinkedBlockingQueue大小="+executorService.getQueue().size()+"getActiveCount="+executorService.getActiveCount());
        Future<String> futureResult2 = executorService.submit(callableTast2);
        System.out.println("加入2个后，LinkedBlockingQueue大小="+executorService.getQueue().size()+"getActiveCount="+executorService.getActiveCount());
        Future<String> futureResult3 = executorService.submit(callableTast3);
        System.out.println("加入3个后，LinkedBlockingQueue大小="+executorService.getQueue().size()+"getActiveCount="+executorService.getActiveCount());
        Future<String> futureResult4 = executorService.submit(callableTast4);
        System.out.println("加入4个后，LinkedBlockingQueue大小="+executorService.getQueue().size()+"getActiveCount="+executorService.getActiveCount());
//        Future<String> futureResult5 = executorService.submit(callableTast5);
        ArrayList<Future<String>> futures = new ArrayList<>();
        try {
            for (Future<String> future : futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

    static class CallableTast implements Callable {
        @Override
        public String call() throws Exception {
            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
            return threadName;
        }
    }
}