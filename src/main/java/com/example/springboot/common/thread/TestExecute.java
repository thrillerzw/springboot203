package com.example.springboot.common.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class TestExecute {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task4Execute task = new Task4Execute();
        executorService.execute(task);
        BlockingQueue<User> list = task.getList();
        try {
            //take阻塞
            System.out.println("user = " + list.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task4Execute implements Runnable {

        BlockingQueue<User> list = new LinkedBlockingQueue<>();
        @Override
        public void run() {
            User user = new User();
            list.offer(user);
        }

        public BlockingQueue<User> getList() {
            return list;
        }
    }

    static class User{
        public User() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}