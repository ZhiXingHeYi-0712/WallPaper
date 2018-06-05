package com.github.tom9163.library;

import jdk.nashorn.api.tree.CatchTree;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

class GetFile {

    static File getPngFile() {   //返回读取指定资源的输入流
        File pictureOrigin = new File("origin.png");
        if (!pictureOrigin.canRead()) {
            try {
                InputStream is = null;
                JarFile jarFile = null;
                try {
                    jarFile = new JarFile("WallPaperTimer.jar");
                    System.out.print("jar ok");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert jarFile != null;
                for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements(); ) { //这个循环会读取jar包中所有文件，包括文件夹

                    JarEntry jarEntry = e.nextElement();//jarEntry就是我们读取的jar包中每一个文件了，包括目录

                    if (jarEntry.getName().contains("origin.png")) { //getName()会获取文件全路径名称

                        //如果是aa.txt就将其拷贝到test文件夹下
                        is = jarFile.getInputStream(jarEntry); //将目标文件读到流中

                        File file = new File("origin.png");

                        //我们自己手写一个方法，用来读写文件
                        writeFile(is, file);
                    }
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return pictureOrigin;
    }

    static File getXmlFile() {   //返回读取指定资源的输入流
        File pictureOrigin = new File("exam.xml");
            try {
                InputStream is = null;
                JarFile jarFile = null;
                try {
                    jarFile = new JarFile("WallPaperTimer.jar");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert jarFile != null;
                for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements(); ) { //这个循环会读取jar包中所有文件，包括文件夹

                    JarEntry jarEntry = e.nextElement();//jarEntry就是我们读取的jar包中每一个文件了，包括目录

                    if (jarEntry.getName().contains("exam.xml")) { //getName()会获取文件全路径名称

                        //如果是aa.txt就将其拷贝到test文件夹下
                        is = jarFile.getInputStream(jarEntry); //将目标文件读到流中

                        File file = new File("exam.xml");

                        //我们自己手写一个方法，用来读写文件
                        writeFile(is, file);
                    }
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return pictureOrigin;
    }


    private static void writeFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
