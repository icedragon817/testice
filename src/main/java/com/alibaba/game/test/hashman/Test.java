package com.alibaba.game.test.hashman;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HuffmanCompress sample = new HuffmanCompress();
        //    File inputFile = new File("C:\\Users\\long452a\\Desktop\\opencv链接文档.txt");
        //   File outputFile = new File("C:\\Users\\long452a\\Desktop\\opencv链接文档.rar");
        //    sample.compress(inputFile, outputFile);
        File inputFile = new File("C:\\Users\\long452a\\Desktop\\opencv链接文档.rar");
        File outputFile = new File("C:\\Users\\long452a\\Desktop\\opencv链接文档1.txt");
        sample.extract(inputFile, outputFile);
    }

}