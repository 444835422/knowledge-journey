package thread;

import java.util.concurrent.Callable;

/**
 * @author baiyumei
 * @title: GetThreadValue1
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/263:10 下午
 */
public class CallableTest {
    public static void main(String[] args) throws Exception {
        String value = "test1";
        value = new ThreadTest().call();
        System.out.println(value);

    }

    private static class ThreadTest implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "ThreadTest";
        }
    }

}
