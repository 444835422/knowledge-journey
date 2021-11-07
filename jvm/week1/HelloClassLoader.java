package jvm.week1;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author baiyumei
 * @title: HelloClassLoader
 * @projectName simpletest
 * @description: TODO
 * @date 2021/11/511:00 上午
 */
public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        HelloClassLoader classLoader = new HelloClassLoader();
        classLoader.findClass("jvm.Hello").newInstance();

    }
    @Override
    protected Class findClass(String name){
        String base64="NQFFQf///8v/4/X/+f/x9v/w/+/3/+71/+3/7Pj/6/j/6v7/+cOWkZaLwf7//NfWqf7/+7yQm5r+//CzlpGasYqSnZqNq56dk5r+//qXmpOTkP7/9ayQio2cmrmWk5r+//W3mpOTkNGVnome8//4//f4/+nz/+j/5/7/7Leak5OQ09+ck56MjLOQnpuajd74/+bz/+X/5P7/+reak5OQ/v/vlZ6JntCTnpGY0LCdlZqci/7/75WeiZ7Qk56RmNCshoyLmpL+//yQiov+/+qzlZ6JntCWkNCvjZaRi6yLjZqeksT+/+yVnome0JaQ0K+NlpGLrIuNmp6S/v/4j42WkYuTkf7/6tezlZ6JntCTnpGY0KyLjZaRmMTWqf/e//r/+f///////f/+//j/9//+//b////i//7//v////rVSP/+Tv////7/9f////n//v////7//v/0//f//v/2////2v/9//7////2Tf/97fxJ//tO/////v/1////9f/9////+//3//r//v/z/////f/y";
        byte[] base64Decode=Base64.getDecoder().decode(base64);
        for (int i =0;i<base64Decode.length;i++){
            base64Decode[i] = (byte)(255-base64Decode[i]);
        }
        return defineClass(base64Decode,0,base64Decode.length);
    }





}
