package com.alibaba.game.test;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.aligame.cdstudio.wings.notify.rmi.TestHander;

public class TestRMI {

    public static void main(String[] args) {
        //try {
        //    Remote hander = Naming.lookup("rmi://30.17.2.35:1098/com.aligame.cdstudio.wings.notify.rmi.TestHander");
        //
        //    //hander.test();
        //
        //    System.out.println(hander.getClass().getMethod("test").invoke(hander));
        //    System.out.println(hander.getClass().getMethod("sendAlarmMsg").invoke(hander, "哈哈哈"));
        //} catch (RemoteException e) {
        //    e.printStackTrace();
        //} catch (MalformedURLException e) {
        //    e.printStackTrace();
        //} catch (NotBoundException e) {
        //    e.printStackTrace();
        //} catch (NoSuchMethodException e) {
        //    e.printStackTrace();
        //} catch (IllegalAccessException e) {
        //    e.printStackTrace();
        //} catch (InvocationTargetException e) {
        //    e.printStackTrace();
        //}
        String s = "aaabbb";
        System.out.println(s.substring(3,5));
        List<String > ls = new ArrayList<>();
        s.length();
        int [][] nums = new int [] [] {
            new int [] {0,1},
            new int [] {1,3},
            new int [] {2,3},
            new int [] {4,0},
            new int [] {4,5}
        };
        int [][] nums1 = new int [] [] {
            new int [] {0,1,1,1,1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,1,0,0,0,1,0,1,0,0,0,1},
            new int [] {1,0,0,0,1,1,1,0,1,0,1,0,0,1,0,1,1,0,1,0,0,0,1,1,1,0,1,1,0,1},
            new int [] {1,1,1,0,1,0,1,1,0,0,1,1,1,0,1,0,1,0,0,0,1,1,0,0,0,0,0,1,1,0},
            new int [] {0,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,0,1,1,1,0,1,0,1,0,1,0,0,1},
            new int [] {1,1,0,1,0,0,1,1,1,0,1,1,0,0,1,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0},
            new int [] {1,1,1,0,0,1,1,1,1,1,1,1,0,0,1,1,0,1,1,1,0,0,0,1,0,1,0,1,0,0},
            new int [] {1,1,1,1,0,1,1,0,0,0,1,1,1,0,0,1,1,1,0,0,0,1,0,0,1,0,1,1,0,0},
            new int [] {1,0,1,0,0,1,1,0,0,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,0,1},
            new int [] {0,0,1,0,1,0,1,1,1,1,1,1,0,0,0,0,0,1,0,1,1,1,0,1,0,0,1,1,0,1},
            new int [] {0,0,0,0,1,1,0,1,1,0,1,0,0,1,0,1,1,1,0,1,0,1,1,0,1,1,1,1,0,1},new int [] {1,0,0,0,1,0,0,0,0,0,1,1,1,1,1,0,0,1,0,1,1,0,1,0,0,1,1,0,1,0},new int [] {0,0,1,0,0,0,0,0,0,0,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1},
            new int [] {0,1,1,1,0,0,0,1,1,0,0,1,1,0,1,1,0,1,0,0,0,0,0,0,0,0,1,1,1,1},new int [] {0,1,0,1,0,0,0,1,1,1,0,0,0,1,1,0,0,0,0,1,0,0,0,0,1,0,1,1,1,1},new int [] {0,1,0,0,0,1,0,1,0,0,0,1,0,1,0,1,1,0,0,0,1,1,0,0,1,1,1,0,1,1},
            new int [] {0,0,1,0,0,1,0,0,1,0,1,0,0,0,1,1,0,1,1,1,0,0,1,0,1,0,1,1,1,1},new int [] {1,1,0,0,1,0,0,1,0,0,1,1,1,0,0,1,0,1,0,0,1,0,0,0,1,0,0,1,1,0},new int [] {1,1,1,0,1,1,0,0,0,0,0,1,1,0,0,1,0,0,1,1,1,0,1,1,0,1,1,0,0,1},
            new int [] {1,1,1,1,0,0,0,1,0,0,1,0,1,1,1,1,0,0,0,0,1,1,0,1,0,0,1,1,0,1},new int [] {1,0,0,0,1,1,0,0,1,1,0,0,0,1,1,0,0,1,0,0,1,0,0,1,0,1,0,0,0,1},new int [] {1,0,0,0,1,0,1,0,0,1,1,0,1,1,0,1,1,0,0,0,1,0,1,1,1,0,1,0,1,1},
            new int [] {0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,1,0,0,1,0,0,0,1,1,1,0,0,1,1,0},new int [] {0,0,1,0,0,0,1,1,0,0,1,0,0,0,1,1,0,0,0,0,1,1,1,1,1,1,0,0,1,1},new int [] {0,1,0,1,0,0,0,0,1,1,0,1,0,1,0,0,1,1,1,1,0,1,0,0,0,0,0,0,0,1},
            new int [] {1,0,1,1,0,0,0,1,1,0,1,0,1,1,1,1,1,1,0,0,1,1,0,0,1,1,1,1,1,0},new int [] {0,1,0,0,0,1,1,1,1,0,0,1,0,0,0,0,1,1,1,0,1,0,0,0,0,1,1,0,0,0},new int [] {0,0,1,1,1,0,0,0,1,0,0,0,0,0,1,1,0,0,1,0,0,1,0,1,1,0,1,1,0,1},
            new int [] {0,0,1,1,1,0,0,0,0,0,0,1,1,0,0,1,0,0,1,1,0,0,1,0,0,0,1,0,1,0},new int [] {1,0,0,1,0,0,1,0,0,1,0,0,1,1,0,1,1,1,0,1,0,0,1,0,0,0,1,0,0,0},new int [] {0,1,1,1,0,0,1,0,1,1,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0,0,1,1,0,0}
        };

        //minReorder(6, nums);
        System.out.println(maxDistance(nums1));
    }

    public static int minReorder(int n, int[][] connections) {
        int rs = 0, st = 0;
        //ls 待执行的起点城市 初始0
        int [] citys = new int[n];
        // List<Integer> ls = new ArrayList<Integer>();
        // ls.add(st);
        citys[0] = st;
        int cityIndex = 0;
        //ls 循环起点城市
        for(int i=0; i<n; i++) {
            //读取起点城市
            // st = ls.get(0);
            for(int [] connection : connections) {
                if(connection[0] == citys[i]) {
                    //存在与起点城市相连 添加下一个起点 修改数组 标识已标记
                    // ls.add(connection[1]);
                    citys[++cityIndex] = connection[1];
                    connection[1] = citys[i];
                    //起点在前 需改道
                    rs++;
                } else if (connection[1] == citys[i]) {
                    //存在与起点城市相连 添加下一个起点 修改数组 标识已标记
                    // ls.add(connection[0]);
                    citys[++cityIndex] = connection[0];
                    connection[0] = citys[i];
                }
            }
            //删除已查找过的起点城市
            // ls.remove(0);
        }
        return rs;
    }

    public static int maxDistance(int[][] grid) {
        int range = 0;
        int len = grid.length;
        int [] w = grid[0];
        int r=0;
        boolean find = false;
        boolean none = false;
        for(int y=0;y<len;y++) {
            w = grid[y];
            for(int x=0;x<w.length;x++) {
                if(w[x]==0) {
                    find=false;
                    r = 1;
                    while(true) {
                        int o1=0,o2=0,o3=0,o4=0;
                        none = true;
                        for(int m=0;m<=r;m++) {
                            int x1=x-m, x2=x+m;
                            int y1=y-r+m, y2=y+r-m;
                            o1 = x1>=0 && y1>=0 ? grid[y1][x1] : -1;
                            o2 = x1>=0 && y2<grid.length ? grid[y2][x1] : -1;
                            o3 = x2<w.length && y1>=0 ? grid[y1][x2] : -1;
                            o4 = x2<w.length && y2<grid.length ? grid[y2][x2] : -1;
                            if(o1==1 || o2==1 || o3==1 || o4==1) {
                                find=true;
                                break;
                            } else if (o1 != -1 || o2 !=-1 || o3!=-1 || o4!=-1) {
                                none = false;
                            }
                        }

                        if(find) {
                            break;
                        }

                        if(none) {
                            return -1;
                        }

                        r++;
                    }
                    range = range < r ? r : range;
                }
            }
        }
        return range;
    }
}
