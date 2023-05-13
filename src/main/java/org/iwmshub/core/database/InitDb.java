package org.iwmshub.core.database;

public class InitDb {
    public void start() throws InterruptedException{
        System.out.println("wait for 5000");
        Thread.sleep(5000);
        System.out.println("wait for 10000");
        Thread.sleep(10000);
        System.out.println("init db");
    }
}
