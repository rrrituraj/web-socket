package com.raj.socket;


import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GeneralTestClass {

    @Test
    void firstGeneralTest() throws InterruptedException, ExecutionException, TimeoutException {
        long start = System.currentTimeMillis();

        //Testing completablefuture
        CompletableFuture<Boolean> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            return this.waitDefinitelly(3000L);
        });
        CompletableFuture<Boolean> uCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            return this.waitDefinitelly(3000L);
        });
        CompletableFuture<Boolean> uCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            return this.waitDefinitelly(3000L);
        });

        CompletableFuture.allOf(uCompletableFuture, uCompletableFuture1, uCompletableFuture2).get(4, TimeUnit.SECONDS);


        long end = System.currentTimeMillis();
        System.out.println("Total time taken with completable future is:  " + (end - start));

        this.waitDefinitelly(3000L);
        this.waitDefinitelly(3000L);
        this.waitDefinitelly(3000L);

        long end2 = System.currentTimeMillis();
        System.out.println("Total time taken without completable future is:  " + (end2 - end));


    }

    @Test
    void withoutCompletableFuture() {
        long start = System.currentTimeMillis();

        this.waitDefinitelly(3000L);
        this.waitDefinitelly(3000L);
        this.waitDefinitelly(3000L);

        long end = System.currentTimeMillis();
        System.out.println("Total time taken without completable future is:  " + (end - start));

    }

    private Boolean waitDefinitelly(Long time) {
        System.out.println("Currently printing: " + Thread.currentThread().getName());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
