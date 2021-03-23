package com.byshu.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileChannelTransferTest {


    public static void main(String[] args) throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);

        toFile.close();
        fromFile.close();
    }
}
