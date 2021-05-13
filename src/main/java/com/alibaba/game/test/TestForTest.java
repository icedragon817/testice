package com.alibaba.game.test;

import java.util.ArrayList;
import java.util.List;


public class TestForTest {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println("the result is : " + algorithm(1000,7) + "! total cost "
            + (System.currentTimeMillis() - start) + " ms");//5097
    }

    /**
     * 有m个人排成圈，每个人有一个编号，从1到m，
     * 1号开始报数，每报到n的倍数或者含有n的数字时出局,
     * 每出现一个出局者,在其后面至少出现一个安全者（即不会立即被出局，假设70号出局，71号则不会出局），
     * 返回最后的出局者编号！
     *
         * @param m
     * @param n
     * @return
         */
    private static int algorithm(int m, int n) {

        return 0;
    }

}
