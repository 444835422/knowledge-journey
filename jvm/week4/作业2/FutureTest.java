package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author baiyumei
 * @title: FeatureTest
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/263:58 下午
 */
public class FutureTest {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        System.out.println(executorService.submit(new Callable1()).get());
        executorService.shutdown();
    }

    private static class Callable1 implements Callable<String>{

        @Override
        public String call() throws Exception {
            return "Callable1";
        }
    }
}