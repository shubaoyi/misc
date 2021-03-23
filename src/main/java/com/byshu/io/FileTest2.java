package com.byshu.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTest2 {

    public static void main(String[] args) {
        RandomAccessFile raf = null;
        try {
            // create a new RandomAccessFile with filename test
            raf = new RandomAccessFile("f:/test.txt", "rw");

            // write something in the file
            raf.writeUTF("Hello World");

            // set the file pointer at 0 position
            raf.seek(0);

            // print the string
            System.out.println("" + raf.readUTF());

            // print current length
            System.out.println("" + raf.length());

            // set the file length to 30
            raf.setLength(30);

            // print the new length
            System.out.println("" + raf.length());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
