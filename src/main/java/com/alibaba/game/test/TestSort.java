package com.alibaba.game.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

public class TestSort {

    public static void main(String[] args) {

        String test = UUID.randomUUID().toString();
        int len = 100000;
        Integer[] arr1 = createArray(len);

        long start = System.currentTimeMillis();

        //maopaoSort(arr1);
        Arrays.sort(arr1);
        System.out.println("total cost " + (System.currentTimeMillis() - start) + " ms");
        Integer[] arr2 = createArray(len);
        start = System.currentTimeMillis();
        quickSort(arr2);
        System.out.println("total cost " + (System.currentTimeMillis() - start) + " ms");
        Integer[] arr3 = createArray(len);
        start = System.currentTimeMillis();
        threeSort(arr3);
        System.out.println("total cost " + (System.currentTimeMillis() - start) + " ms");
        Integer[] arr4 = createArray(len);
        start = System.currentTimeMillis();
        maopaoSort(arr4);
        System.out.println("total cost " + (System.currentTimeMillis() - start) + " ms");
        System.out.println("over");
    }
    private static Integer [] createArray(int len) {
        Integer[] arr = new Integer[len];
        for (int i=0; i<len; i++) {
            arr[i] = RandomUtils.nextInt(0,1000);
        }
        return arr;
    }

    private static void threeSort(Integer [] arr) {
        int start = 0, end = arr.length-1;
        threeSort(arr, start, end);
    }

    private static void threeSort(Integer[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int i=start,j=start+1,k=end;
        int v = arr[i];
        while (j<=k) {
            if (arr[j] > v) {
                exch(arr, j, k--);
            } else if (arr[j] < v) {
                exch(arr, i++, j++);
            } else {
                j++;
            }
        }
        threeSort(arr, start, i-1);
        threeSort(arr, k+1, end);
    }

    private static void quickSort(Integer [] arr) {
        int start = 0, end = arr.length - 1;
        quickSort(arr, start, end);
    }

    private static void quickSort(Integer [] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = findPointer(arr, start, end);
        quickSort(arr, start, p-1);
        quickSort(arr, p+1, end);
    }
    private static int findPointer(Integer[] arr, int start, int end) {
        int i = start, j = end + 1;
        int v = arr[start];
        while (true) {
            while (arr[++i] < v) {
                if (i == end) {
                    break;
                }
            }
            while (arr[--j] > v) {
                if (j == start) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }

            exch(arr, i, j);

        }
        exch(arr, start, j);
        return j;
    }

    private static void maopaoSort(Integer[] arr) {
        for (int i=0;i<arr.length; i++) {
            for (int j= i+1; j<arr.length;j++) {
                if (arr[i] > arr[j]) {
                    exch(arr,i,j);
                }
            }
        }
    }

    private static void exch(Integer[] arr, int i, int j) {
        int v = arr[i];
        arr[i] = arr[j];
        arr[j] = v;
    }
}
