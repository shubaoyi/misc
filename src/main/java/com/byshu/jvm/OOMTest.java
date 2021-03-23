package com.byshu.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 */
public class OOMTest {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}
