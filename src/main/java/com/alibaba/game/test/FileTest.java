package com.alibaba.game.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.game.model.FileEntry;
import com.alibaba.game.utils.FileUtils;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

public class FileTest {

    public static void main(String[] args) throws Exception {
        String fileName = "test.sh";
        String filePath = "http://10.0.1.31/test.sh";
        List operate = new ArrayList();
        Map step1 = new HashMap();
        Map s1 = new HashMap();
        s1.put("module", "command");
        s1.put("args", "wget -P /opt/ -N " + filePath + " -q");
        step1.put("action", s1);
        operate.add(step1);

        //执行脚本
        String format = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (format.equals("py")) {
            Map step2 = new HashMap();
            Map s2 = new HashMap();
            s2.put("module", "command");
            s2.put("args", "python /opt/" + fileName);
            step2.put("action", s2);
            operate.add(step2);
        } else {
            Map step2 = new HashMap();
            Map s2 = new HashMap();
            s2.put("module", "command");
            s2.put("args", "chmod 0755 /opt/" + fileName);
            step2.put("action", s2);
            operate.add(step2);
            Map step3 = new HashMap();
            Map s3 = new HashMap();
            s3.put("module", "command");
            s3.put("args", "/opt/" + fileName);
            step3.put("action", s3);
            operate.add(step3);
        }

        Map step4 = new HashMap();
        Map s4 = new HashMap();
        s4.put("module", "file");
        s4.put("args", "path='/opt/" + fileName + "' state=absent");
        step4.put("action", s4);
        operate.add(step4);
        System.out.println(JSONObject.toJSONString(operate));
        File f = new File("D:\\t\\1.txt");

        f.delete();
        long start = System.currentTimeMillis();

        List<FileEntry> list = new ArrayList<FileEntry>();
        //analysis(f,"D:\\t\\Octopod_TEST", list);
        //File[] files = f.listFiles();
        //FileOutputStream out = new FileOutputStream("D:\\t\\test1");
        //write(out,"D:\\t\\test1","哈哈哈\n");
        //write(out,"D:\\t\\test1","嘎嘎嘎");
        long end = System.currentTimeMillis();

        System.out.println("total cost " + (end -start) + " ms");
    }

    public static void write(FileOutputStream out, String path, String st) {
        try {
            if (out == null) {
                out = new FileOutputStream(path);
            }
            out.write(st.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分析压缩文件
     * @param file 压缩文件对象
     * @param path 要解压到某个指定的目录下
     * @throws IOException
     */
    public static void analysis(File file,String path,List<FileEntry> list) throws IOException {

        String fileName = file.getName();

        String [] fileInfo = fileName.split("\\.");

        int len = fileInfo.length;
        String format = fileInfo[len - 1];
        if ("gz".equals(format)) {
            String attr = fileInfo[len - 2];
            if ("tar".equals(attr)) {
                format = attr + ".gz";
            }
        }

        switch (format) {
            case "zip" :
                analysisZip(file,path,list);
                break;
            case "tar" :
                break;
            case "gz" :
                analysisGz(path,list);
                break;
            case "tar.gz" :
            case "tgz" :
                break;
            default:
                break;
        }
    }

    /**
     * 分析tar压缩文件
     * @param path 文件目录
     * @param isGz 是否gz压缩 .tar.gz
     * @throws IOException
     */
    private static void analysisTar(String path,String p, boolean isGz){

        TarInputStream tarIn = null;
        try{
            FileInputStream fis = new FileInputStream(path);
            InputStream is = isGz ? new GZIPInputStream(fis) : fis;
            tarIn = new TarInputStream( is, 1024 * 4);

            TarEntry entry = null;
            while( (entry = tarIn.getNextEntry()) != null ){


                String entryPath = entry.getName();
                java.io.File file = entry.getFile();
                if (file != null) {
                    System.out.println(FileUtils.genMd5(file));
                } else {

                    long size = entry.getSize();
                    long readsize = 0;
                    int length =  (int) size % 2048;
                    long t =  size / 2048;
                    byte[] b = new byte[2048];
                    //tarIn.read(b);
                    //if (entry.getSize() > 0) {
                    //    entryPath = isGz ? "tgz\\" + entryPath : "tar\\" + entryPath;
                    //    FileOutputStream out = new FileOutputStream("D:\\t\\test\\tt\\" + entryPath);
                    //    out.write(b);
                    //}


                    //List<Map> byteInfos = new ArrayList<>();
                    //
                    //for (int i=0;i<t;i++) {
                    //    tarIn.read(b);
                    //    Map byteInfo = new HashMap();
                    //    byte[] bak = b;
                    //    byteInfo.put("b",bak);
                    //    byteInfo.put("len",2048);
                    //    byteInfos.add(byteInfo);
                    //}
                    //if (length > 0) {
                    //    byte[] b_bak = new byte[length];
                    //    tarIn.read(b_bak);
                    //    Map byteInfo = new HashMap();
                    //    byte[] bak = b_bak;
                    //    byteInfo.put("b",bak);
                    //    byteInfo.put("len",length);
                    //    byteInfos.add(byteInfo);
                    //}
                    if (size>0) {
                        System.out.println(entry.getName() + ":" + FileUtils.genMd5(tarIn, size));
                    }
                }


            }
        }catch(IOException e){
        } finally{
            try{
                if(tarIn != null){
                    tarIn.close();
                }
            }catch(IOException e){
            }
        }
    }

    private static void genEntryInfo(List<FileEntry> list, ZipEntry entry, String outputDir) {

        String path = entry.getName();
        FileEntry fe = new FileEntry();
        String [] infos = path.split("/");
        int len = infos.length;
        fe.setName(infos[len-1]);
        fe.setPath(path);
        fe.setParentFile(outputDir);
        fe.setSize(entry.getSize());
        fe.setType(entry.isDirectory() ? 0 : 2);
        File file = new File(outputDir + File.separator + entry.getName());
        fe.setMd5(FileUtils.genMd5(file));
        list.add(fe);
    }

    /**
     * 分析zip压缩文件
     * @param file 压缩文件对象
     * @param outputDir 要解压到某个指定的目录下
     * @throws IOException
     */
    public static void analysisZip(File file,String outputDir,List<FileEntry> list) throws IOException {

        ZipFile zipFile = null;
        try {
            //Charset gbk = Charset.forName("GBK");
            //zipFile =  new ZipFile(file, gbk);
            zipFile =  new ZipFile(file);
            Enumeration<?> enums = zipFile.entries();
            ZipInputStream zin = new ZipInputStream(new FileInputStream(file));
            while(enums.hasMoreElements()){
                ZipEntry entry = (ZipEntry) enums.nextElement();
                System.out.println("解压." +  entry.getName());
                FileUtils.write(outputDir + File.separator + entry.getName(), zipFile.getInputStream(entry));
                genEntryInfo(list,entry, outputDir);
                //InputStream is = zipFile.getInputStream(entry);
                //String md5 = FileUtils.genMd5(is);
                //System.out.println(md5);
            }
            System.out.println("over");
        } catch (IOException e) {
            throw new IOException("分析压缩文件出现异常",e);
        } finally{
            try{
                if(zipFile != null){
                    zipFile.close();
                }
            }catch(IOException ex){
                throw new IOException("关闭zipFile出现异常",ex);
            }
        }
    }

    public static void analysisGz(String sourcedir, List<FileEntry> list) {
        //建立gzip压缩文件输入流
        GZIPInputStream gzin = null;
        try {
            File file = new File(sourcedir);
            //建立gzip解压工作流
            gzin = new GZIPInputStream(new FileInputStream(sourcedir));

            FileEntry fe = new FileEntry();
            fe.setType(2);
            fe.setSize(FileUtils.getSize(gzin));
            fe.setName(file.getName().substring(0,file.getName().indexOf('.')));
            fe.setPath(sourcedir);
            gzin = new GZIPInputStream(new FileInputStream(sourcedir));
            fe.setMd5(FileUtils.genMd5(gzin));
            list.add(fe);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return;
    }

    public static void analysisTar(File file,String outputDir, List<FileEntry> list, boolean isGz) throws IOException{
        TarInputStream tarIn = null;
        try{
            FileInputStream fis = new FileInputStream(file);
            InputStream is = isGz ? new GZIPInputStream(fis) : fis;
            tarIn = new TarInputStream( is, 1024 * 4);

            TarEntry entry = null;
            while( (entry = tarIn.getNextEntry()) != null ){

                //是目录
                if(entry.isDirectory()){
                    entry.getName();
                    //创建空目录
                    createDirectory(outputDir,entry.getName());

                }else{
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    //创建输出目录
                    createDirectory(tmpFile.getParent() + "/",null);
                    OutputStream out = null;
                    try{
                        out = new FileOutputStream(tmpFile);
                        int length = 0;
                        byte[] b = new byte[2048];
                        while((length = tarIn.read(b)) != -1){
                            out.write(b, 0, length);
                        }

                    }catch(IOException ex){
                        throw ex;
                    }finally{
                        if(out!=null) {
                            out.close();
                        }
                    }
                }
            }
        }catch(IOException ex){
            throw new IOException("解压归档文件出现异常",ex);
        } finally{
            try{
                if(tarIn != null){
                    tarIn.close();
                }
            }catch(IOException ex){
                throw new IOException("关闭tarFile出现异常",ex);
            }
        }
    }

    public static void unGzipFile(String sourcedir) {
        String ouputfile = "";
        try {
            //建立gzip压缩文件输入流
            FileInputStream fin = new FileInputStream(sourcedir);
            //建立gzip解压工作流
            GZIPInputStream gzin = new GZIPInputStream(fin);
            //建立解压文件输出流
            ouputfile = sourcedir.substring(0,sourcedir.lastIndexOf('.'));
            //ouputfile = ouputfile.substring(0,ouputfile.lastIndexOf('.'));
            FileOutputStream fout = new FileOutputStream(ouputfile);

            int num;
            byte[] buf=new byte[1024];
            while ((num = gzin.read(buf,0,buf.length)) != -1)
            {
                fout.write(buf,0,num);
            }
            gzin.close();
            fout.close();
            fin.close();
        } catch (Exception ex){
            System.err.println(ex.toString());
        }
        return;
    }

    /**
     * 解压缩zipFile
     * @param file 要解压的zip文件对象
     * @param outputDir 要解压到某个指定的目录下
     * @throws IOException
     */
    public static void unZip(File file,String outputDir,List<FileEntry> list) throws IOException {
        ZipFile zipFile = null;
        try {
            zipFile =  new ZipFile(file);
            //创建输出目录
            createDirectory(outputDir,null);
            Enumeration<?> enums = zipFile.entries();
            while(enums.hasMoreElements()){
                ZipEntry entry = (ZipEntry) enums.nextElement();
                System.out.println("解压." +  entry.getName());
                genEntryInfo(list,entry, outputDir);
                //是目录
                if(entry.isDirectory()){
                    //创建空目录
                    createDirectory(outputDir,entry.getName());
                }else{//是文件
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    //创建输出目录
                    createDirectory(tmpFile.getParent() + "/",null);
                    InputStream in = null;
                    OutputStream out = null;
                    try{
                        in = zipFile.getInputStream(entry);;
                        out = new FileOutputStream(tmpFile);
                        int length = 0;
                        byte[] b = new byte[2048];
                        while((length = in.read(b)) != -1){
                            out.write(b, 0, length);
                        }

                    }catch(IOException ex){
                        throw ex;
                    }finally{
                        if(in!=null) {
                            in.close();
                        }
                        if(out!=null) {
                            out.close();
                        }
                    }
                }
            }
            System.out.println("over");
        } catch (IOException e) {
            throw new IOException("解压缩文件出现异常",e);
        } finally{
            try{
                if(zipFile != null){
                    zipFile.close();
                }
            }catch(IOException ex){
                throw new IOException("关闭zipFile出现异常",ex);
            }
        }
    }

    /**
     * 解压tar.gz 文件
     * @param file 要解压的tar.gz文件对象
     * @param outputDir 要解压到某个指定的目录下
     * @throws IOException
     */
    public static void unTarGz(File file,String outputDir) throws IOException{
        TarInputStream tarIn = null;
        try{
            tarIn = new TarInputStream( new FileInputStream(file), 1024 * 4);
            //创建输出目录
            createDirectory(outputDir,null);

            TarEntry entry = null;
            while( (entry = tarIn.getNextEntry()) != null ){

                //是目录
                if(entry.isDirectory()){
                    entry.getName();
                    //创建空目录
                    createDirectory(outputDir,entry.getName());

                }else{
                    File tmpFile = new File(outputDir + "/" + entry.getName());
                    //创建输出目录
                    createDirectory(tmpFile.getParent() + "/",null);
                    OutputStream out = null;
                    try{
                        out = new FileOutputStream(tmpFile);
                        int length = 0;
                        byte[] b = new byte[2048];
                        while((length = tarIn.read(b)) != -1){
                            out.write(b, 0, length);
                        }

                    }catch(IOException ex){
                        throw ex;
                    }finally{
                        if(out!=null) {
                            out.close();
                        }
                    }
                }
            }
        }catch(IOException ex){
            throw new IOException("解压归档文件出现异常",ex);
        } finally{
            try{
                if(tarIn != null){
                    tarIn.close();
                }
            }catch(IOException ex){
                throw new IOException("关闭tarFile出现异常",ex);
            }
        }
    }

    /**
     * 构建目录
     * @param outputDir
     * @param subDir
     */
    public static void createDirectory(String outputDir,String subDir){
        File file = new File(outputDir);
        //子目录不为空
        if(!(subDir == null || subDir.trim().equals(""))){
            file = new File(outputDir + "/" + subDir);
        }
        if(!file.exists()){
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }
}
