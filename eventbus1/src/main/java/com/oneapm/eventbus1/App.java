package com.oneapm.eventbus1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Hello world!
 *
 */
public class App {
    
    private EventBus bus = new EventBus("test");
    
    /**
     * getNotified: <br/>
     * @author xushjie
     * @param what
     * @since JDK 1.7
     */
    @Subscribe
    public void getNotified(String what) {
        System.out.println("I am notified: " +
                           what);
    }
    
    /**
     * main: <br/>
     * @author xushjie
     * @param args
     * @throws InterruptedException
     * @since JDK 1.7
     */
    public static void main(String[] args) throws InterruptedException {
        final App a = new App();
        a.bus.register(a);
        ScheduledExecutorService exe = Executors.newScheduledThreadPool(5);
        exe.scheduleAtFixedRate(() -> {
                                    a.bus.post(Thread.currentThread()
                                                     .getName());
                                },
                                0,
                                2,
                                TimeUnit.SECONDS);
        Thread.sleep(Long.MAX_VALUE);
        exe.shutdownNow();
    }
}
