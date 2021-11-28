package thread;

import java.util.concurrent.*;

/**
 * @author baiyumei
 * @title: CompletableFuture
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/268:07 下午
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.println("======》0.直接获得结果");
        Integer result0 = CompletableFuture.supplyAsync(()->{return 1;}).get(10000, TimeUnit.MILLISECONDS);
        System.out.println(result0);

        // 1.变换结果
        System.out.println("=====>1.变换结果");
        Integer result1 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;}).thenApplyAsync(s ->{return s*s+5;}).join();
        System.out.println(result1);

        // 2.消费
        CompletableFuture.supplyAsync(()->{return "Hello ";}).thenAccept(v -> {
            System.out.println("=====>2.消费");
            System.out.println("consumer: " + v);});

        // 3.组合
        System.out.println("=====>3.组合");
        String result3 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }),(s1,s2)->{return s1 + " " + s2;})
                .thenCombine(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "!";
        }),(s1,s2)->{return s1 + " " + s2;}).join();
        System.out.println("thenCombine:"+result3);

        CompletableFuture.supplyAsync(() -> "Hello, java course.")
                .thenApply(String::toUpperCase).thenCompose(s -> CompletableFuture.supplyAsync(s::toLowerCase))
                .thenAccept(v -> { System.out.println("thenCompose:"+v);});

        // 4.竞争
        System.out.println("=====>4.竞争");
        String result4 = CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Boy";
        }).applyToEither(CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hi Girl";
        }),(s)->{return s;}).join();
        System.out.println(result4);


    }
}
