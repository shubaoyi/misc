package com.byshu.test;


import com.byshu.interview.User;

import java.util.HashMap;

public class ExitTest {

    public static void main(String[] args) throws Exception {
        User user1 = new User();
        user1.setName("lilin");
        User user2 = user1;
        System.out.println(user2.getName());
        user1.setName("limei");
        System.out.println(user2.getName());

        /*try {
            new Thread(() -> {
                while (true) {
                    System.out.println(1);
                }
            }).start();
        } finally {
            System.exit(0);
        }*/

        /*HashMap<String, Object> map = new HashMap<>();
        Object obj = new Object();
        map.put("a", obj);
        System.out.println(map.get("a"));
        obj = new Object();
        // map.put("a", obj);
        System.out.println(map.get("a"));*/


        // ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);

        // buffer = null;
        /*Field cleanerField= buffer.getClass().getDeclaredField("cleaner");
        // 获取cleaner字段在 DirectByteBuffer类中的偏移量
        long fieldOffset = UNSAFE.objectFieldOffset(cleanerField);
        // 根据偏移量获取具体实例中的cleaner对象
        Object cleaner = UNSAFE.getObject(buffer, fieldOffset);
        Method method = Cleaner.class.getDeclaredMethod("clean");
        method.invoke(cleaner);*/
        // System.gc();

        // System.in.read();

        /*ExitTest test = new ExitTest();
        test = null;
        System.gc();*/
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("ExitTest finalizing...");
    }
}
