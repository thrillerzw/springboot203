package com.example.springboot.common.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class ResourceManage {
    private final Semaphore semaphore ;
    private boolean resourceArray[];
    private final ReentrantLock lock;
    private final int resourceSize=2;
    public ResourceManage() {
        this.resourceArray = new boolean[resourceSize];//存放厕所状态
        this.semaphore = new Semaphore(resourceSize,true);//控制2个共享资源的使用，使用先进先出的公平模式进行共享;公平模式的信号量，先来的先获得信号量
        this.lock = new ReentrantLock(true);//公平模式的锁，先来的先选
        for(int i=0 ;i<resourceSize; i++){
            resourceArray[i] = true;//初始化为资源可用的情况
        }
    }
    public void useResource(int userId){
        try{
            //没坑会阻塞
            semaphore.acquire();
            int id = getResourceId();//占到一个坑
            System.out.print("userId:"+userId+"正在使用资源，资源id:"+id+" "+"threadName:"+Thread.currentThread().getName()+"\n");
            Thread.sleep(10*1000);//do something，相当于于使用资源
            resourceArray[id] = true;//退出这个坑
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semaphore.release();//释放信号量，计数器加1
        }
    }
    private int getResourceId(){
        int id = -1;
        try {
            lock.lock();//虽然使用了锁控制同步，但由于只是简单的一个数组遍历，效率还是很高的，所以基本不影响性能。
            for(int i=0; i<resourceSize; i++){
                if(resourceArray[i]){
                    resourceArray[i] = false;
                    id = i;
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return id;
    }
}