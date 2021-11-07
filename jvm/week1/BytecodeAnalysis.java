package jvm.week1;

import java.util.Arrays;


public class BytecodeAnalysis {
    boolean lambdaFlag=false;
    public BytecodeAnalysis(boolean lambdaFlag){
        this.lambdaFlag=lambdaFlag;
    }
    public double sub(int a,int b){
        return a-b;
    }

    public int add(int[] ints){
        if(lambdaFlag){
            return Arrays.stream(ints).sum();
        }else{
            int sum=0;
            for(int i =0;i<ints.length;i++){
                sum+=ints[i];
            }
            return sum;
        }
    }
    public float mul(int a,int b){
        return a*b;
    }

    public long div(int a,int b){
        if(0==b)
            return 0;

        return a/b;

    }

    public static void main(String[] args) {
        BytecodeAnalysis bytecodeAnalysis = new BytecodeAnalysis(true);
        bytecodeAnalysis.add(new int[]{1,3,5,6});
        bytecodeAnalysis.lambdaFlag=false;
        bytecodeAnalysis.add(new int[]{1,3,5,6});
        bytecodeAnalysis.div(3,5);
        bytecodeAnalysis.mul(4,6);
        bytecodeAnalysis.sub(3,5);
    }
}
