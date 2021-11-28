package thread;

import java.util.concurrent.*;

/**
 * @author baiyumei
 * @title: FeatureTest
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/263:58 下午
 */
public class FutureTaskTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> future = new FutureTask<String>(new Callable1());
        executorService.submit(future);
        System.out.println(future.get(10000,TimeUnit.MILLISECONDS));
        executorService.shutdown();
    }

    private static class Callable1 implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "FutureTaskTest";
        }
    }
}