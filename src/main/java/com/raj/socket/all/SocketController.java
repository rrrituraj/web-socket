package com.raj.socket.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

@RestController
@RequestMapping(value = "/test")
public class SocketController {

    @Autowired
    private TestEntityDao testEntityDao;
    @Autowired
    @Qualifier("taskExecutor")
    private Executor taskExecutor;

    @GetMapping(value = "/pool")
    public String testPool() {
        CompletableFuture<Object> supplyAsync = CompletableFuture.supplyAsync(() -> {
            printPool();
            return null;
        },taskExecutor);
        try {
            supplyAsync.get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(e.getMessage());
        }
        return "Success";
    }

    private void printPool() {
//        System.out.println("Printing by: "+Thread.currentThread().getName());
        try {
            TestEntity testEntity = new TestEntity().setTestId(1).setName("RITURAJ");
            TestEntity entity = testEntityDao.findByTestId(1);
//            System.out.println(entity);
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
