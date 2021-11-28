package thread;

import java.util.concurrent.*;

/**
 * @author baiyumei
 * @title: FeatureTest
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/263:58 下午
 */
public class FutureTaskTest1 {

    public static void main(String[] args) throws Exception {
        FutureTask<String> future = new FutureTask<String>(new Callable(){
            @Override
            public String call() throws Exception {
                return "FutureTaskTest1";
            }
        });
        new Thread(future).start();
        System.out.println(future.get(10000,TimeUnit.MILLISECONDS));
    }
}