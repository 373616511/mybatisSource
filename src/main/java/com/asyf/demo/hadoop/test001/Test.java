package com.asyf.demo.hadoop.test001;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

//error:
//java.lang.NoClassDefFoundError: org/apache/hadoop/fs/FSDataInputStream
public class Test {

    public static void main(String[] args) throws Exception {

        //Hadoop HDFS路径
        String uri = "hdfs://192.168.78.129:9000/test/abc.txt";
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(URI.create(uri), configuration);
        //遍历文件夹
        FileStatus[] listStatus = fileSystem.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {
            System.out.println(fileStatus.isDir() ? "文件夹,owner:" + fileStatus.getOwner() : "文件" + " " + fileStatus.getOwner() + " " + fileStatus.getReplication() + " " + fileStatus.getPath());
        }
        //下载文件
        //look(fileSystem, uri);
        //上传文件,同名文件会被覆盖
        //upload(fileSystem);
        //删除文件
        //delete(fileSystem);

    }

    private static void delete(FileSystem fileSystem) throws IOException {
        String fileName = "/test/abc.txt";
        fileSystem.delete(new Path(fileName), true);
    }

    private static void look(FileSystem fileSystem, String uri) throws IOException {
        FSDataInputStream in = fileSystem.open(new Path(uri));
        IOUtils.copyBytes(in, System.out, 4096, false);
        IOUtils.closeStream(in);
    }

    private static void upload(FileSystem fileSystem) throws IOException {
        String fileName = "/test/abc.txt";
        String path = "D:/logs/abc.txt";
        FSDataOutputStream out = fileSystem.create(new Path(fileName));
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        IOUtils.copyBytes(fileInputStream, out, 4096, true);
    }
}
