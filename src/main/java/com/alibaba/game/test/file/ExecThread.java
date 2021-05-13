package com.alibaba.game.test.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExecThread extends Thread {
    /**
     * 任务执行数量（服务器数量）
     */
    protected int index;

    public static ExecThread getInstance(int index) {
        ExecThread thread = new ExecThread();
        thread.setIndex(++index);
        return thread;

    }

    @Override
    public void run() {
        System.out.println("waitFor 当前线程池号" + index);
        String result = "";
        String[] args = new String[2];
        //args[0] = "ssh";
        //args[1] = "-p";
        //args[2] = "65522";
        //args[3] = "ccd_admin@10.0.152.142";
        args[0] = "sh";
        args[1] = "/opt/ansible/work/python/test.sh";
        //args[1] = "/home/ccd_admin/test.py";
        //args[3] = "wings";
        //args[3] = "/opt/ansible/work/conf/hosts276";
        //args[4] = "276";
        //args[5] = "script";
        //args[6] = "{\"args\":\"wget -P /opt/ -N http://10.0.1.31/test001/script/get_timezone.py -q\",\"module\":\"command\"}";
        //args[7] = "{\"args\":\"python /opt/get_timezone.py\",\"module\":\"command\"}";
        //args[8] = "{\"args\":\"path=/opt/get_timezone.py state=absent\",\"module\":\"file\"}";
        //args[0] = "ansible";
        //args[1] = "10.16.57.193 ";
        //args[2] = "-m";
        //args[3] = "ping";
        //args[4] = "";


        try {
            long start = System.currentTimeMillis();
            String exec = "";
            for (String arg : args) {
                System.out.println(arg);
                exec += arg + " ";
            }
            //exec = "(python /opt/ansible/work/python/exec_ansible.py wings 276 script &)";
            System.out.println(exec);
            Process proc = Runtime.getRuntime().exec(exec);

            System.out.println("执行成功，等待结果！");
            proc.waitFor();
            //int flag = 0;
            //System.out.println("线程号：" + index + "，doing: " + proc.exitValue());
            //while (proc.exitValue() != 0) {
            //    flag++;
            //    System.out.println("线程号：" + index + "，doing: " + proc.exitValue());
            //    Thread.sleep(1000);
            //    if (flag > 5) {
            //        break;
            //    }
            //}

            Thread.sleep(5000);

            //this.notify();
            InputStream is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while((line = br.readLine())!=null){
                result += line;
            }
            InputStream eis = proc.getErrorStream();
            InputStreamReader eisr = new InputStreamReader(eis);
            BufferedReader ebr = new BufferedReader(eisr);
            String eline = null;
            while((eline = ebr.readLine())!=null){
                result += eline;
            }
            System.out.println("执行完成");
            //
            //args[0] = "killall";
            //args[1] = "ssh";
            //args[2] = "-u";
            //args[3] = "ccd_admin";
            //Runtime.getRuntime().exec(args);
            long end = System.currentTimeMillis();
            System.out.println("线程号：" + index + "，over, result: " + result + ", 耗时：" + (end - start) + "ms");
        } catch (Exception e) {
            System.out.println("IO error, index: " + index);
            e.printStackTrace();
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
