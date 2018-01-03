package com.bbtree.librec.functional.testing;

import java.util.*;
import java.util.Random;





public class test_java {
    public static void main(String[] agrs)
    {
        System.out.println("HelloWorld!");
        String[] strb = {"120.27.152.224", "120.27.128.16", "120.27.143.56", "120.27.153.103", "120.27.149.186"};
        Random rand = new Random();
        int num2 = rand.nextInt(strb.length);
        String IMPALAD_HOST1 =strb[num2];
        System.out.println(strb[num2]);
        System.out.println(IMPALAD_HOST1);

    }
}
