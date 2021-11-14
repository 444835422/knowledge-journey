import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author baiyumei
 * @title: GCLogAnalysis
 * @projectName java-journey
 * @description: TODO
 * @date 2021/11/88:32 下午
 */
public class GCLogAnalysis {
    public static Random random = new Random();
    public static void main(String[] args){
        long startMillis = System.currentTimeMillis();

        long timeoutMillis = TimeUnit.SECONDS.toMillis(1);

        long endMillis = startMillis+timeoutMillis;

        LongAdder counter = new LongAdder();

        System.out.println("正在执行。。。。");

        int cacheSize = 2000;
        Object[] cacheGarbage = new Object[cacheSize];
        while (System.currentTimeMillis()<endMillis){
            Object garbage = generateGarbage(100*1024);
            counter.increment();
            int randomIndex = random.nextInt(2*cacheSize);
            if(randomIndex<cacheSize){
                cacheGarbage[randomIndex]=garbage;
            }
        }
        System.out.println("程序结束，共生成["+counter.longValue()+"]对象");
    }
    public static  Object generateGarbage(int max){
        int randomSize = random.nextInt(max);
        int type = randomSize%4;
        Object result=null;
        switch (type){
            case 0:
                result = new int[randomSize];
            case 1:
                result = new byte[randomSize];
            case 2:
                result = new double[randomSize];
            case 3:
                result = new float[randomSize];
        }
        return result;
    }
}
