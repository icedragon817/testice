package com.alibaba.game.test;

import com.alibaba.game.utils.CryptUtils;

public class Testeng {

    public static void main(String[] args) {
        //System.out.println(Integer.MIN_VALUE);
        //int a = 5,b=17,c=15,d=7;
        //
        //int e = en(a,b,c,d);
        //int f = 16;
        //System.out.println(e);
        //int a1 = e / (f*f*f);
        //int b1 = (e % (f*f*f)) / (f * f);
        //int c1 = ((e % (f*f*f)) % (f * f)) / f;
        //int d1 = ((e % (f*f*f)) % (f * f)) % f;
        //System.out.println(a1 + " " + b1 + " " + c1 + " " +d1);
        //093CB15F8F7B09DF1BF7B5AEC3C09FAF00D35AF7C0226F33C2CB94475738A2A78301FD314103557D7F8017BE5E37B7371AB5B5F8F170216862987258FB169864
        //093CB15F8F7B09DF1BF7B5AEC3C09FAF00D35AF7C0226F33C2CB94475738A2A78301FD314103557D7F8017BE5E37B7371AB5B5F8F170216862987258FB169864
        String key = "12345679gfgdadgadgaedsgadgadsgadgadgadgadgfadfassfgsdfg8";
        String data = "来来来来，发空间框架";

        String en = CryptUtils.encrypt(data,key);
        System.out.println(en);
        String de = CryptUtils.decrypt(en, key);
        System.out.println(de);
        System.out.println(CryptUtils.md5(data));
        System.out.println(CryptUtils.sha(data));

    }

    private static int en(int a, int b, int c, int d) {
        return a*16*16*16 + b*16*16 + c*16 + d;
    }


}
