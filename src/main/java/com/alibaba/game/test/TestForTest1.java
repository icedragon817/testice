package com.alibaba.game.test;

import java.util.ArrayList;
import java.util.List;


/**
 * @author binlong.lbl
 */
public class TestForTest1 {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        System.out.println("the result is : " + algorithm(5,3) + "! total cost "
            + (System.currentTimeMillis() - start) + " ms");//5097
    }

    /**
     * 有m个人排成圈，每个人有一个编号，从1到m，
     * 1号开始报数，每报到n的倍数或者含有n的数字时出局,
     * 每出现一个出局者,在其后面至少出现一个安全者（即不会立即被出局，假设70号出局，71号则不会出局），
     * 返回最后的出局者编号！
     *
     * @param i
     * @param j
     * @return
     */
    private static int algorithm(int m, int n) {
        /*
         * Write down your ideas.
         */
        //		List<Integer> ids = new ArrayList<Integer>();
        int [] ids = new int[m];
        List<Integer> sortIds = new ArrayList<Integer>();
        for(int i=0;i<m;i++) {
            ids[i] = i+1;
            //			ids.add(i+1);
        }
        int index = 0;//报数
        int rest = m;//剩余
        int last = 0;
        boolean safe = false;
        while(rest>0) {
            for(int i=0;i<m;i++) {
                if(ids[i] != 0 ) {
                    index++;
                    //					String preStr = "" + (index-1);
                    String nowStr = "" + index;
                    //					System.out.println("号码："+ ids[i] + "报数："+index);
                    if(/*verify(preStr, n) &&*/!safe && !verify(nowStr, n)) {
                        //						System.out.println(ids[i]+","+rest);
                        rest--;
                        if(rest == 0) last = ids[i];
                        ids[i] = 0;
                        safe = true;
                    } else {
                        safe = false;
                    }
                }
            }
        }
        //		while(sortIds.size()<m) {
        //			for(int i : ids) {
        //				index++;
        //				System.out.println("号码："+ i + "报数："+index);
        //				String preStr = "" + (index-1);
        //				String nowStr = "" + index;
        //				if(verify(preStr,n) && !verify(nowStr, n)) {
        //					System.out.println(i);
        //					sortIds.add(i);
        //				}
        //			}
        //			ids.removeAll(sortIds);
        //		}
        return last;
        //		return sortIds.get(sortIds.size()-1);
    }

    /**
     * false 出局 true 安全
     * @param str
     * @param m
     * @return
     */
    private static boolean verify(String str,int m) {
        int num = Integer.parseInt(str);
        if(num % m == 0) {
            return false;
        } else if(str.contains(m+"")) {
            return false;
        }
        return true;
    }
}
