package com.byshu.jvm;

import sun.misc.Cleaner;

import java.lang.ref.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

import static io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess.UNSAFE;

/**
 * 强引用（StrongReference）：jvm宁愿抛出oom异常也不会回收
 * 软引用（SoftReference）：只有在内存不足时 jvm才会回收
 * 弱引用（WeakReference）：无论内存是否充足，jvm都会进行回收
 * 虚引用（PhantomReference）：虚引用和前面的软引用、弱引用不同，它并不影响对象的生命周期。
 * 如果一个对象与虚引用关联，则跟没有引用与之关联一样，也无法通过虚引用获得对象的实例，
 * 在任何时候都可能被垃圾回收器回收，为一个对象设置虚引用关联的唯一目的就是在对象被回收时收到一个通知
 */
public class ReferenceTest {

    public static void main(String[] args) throws Exception {
        /*ReferenceQueue<String> queue = new ReferenceQueue<String>();
        PhantomReference<String> wr = new PhantomReference<String>(new String("hello"), queue);
        System.gc();
        // PhantomReference入列  并且referent仍然可达
        System.out.println(getReferent(wr));
        Reference reference1;
        //System.gc();
        while ((reference1 = queue.remove()) != null) {
            // gc后PhantomReference入列 referent仍不为空？
            System.gc();
            System.err.println(getReferent(reference1));
        }*/

        // 虚引用必须和引用队列关联使用
        /*WeakReference<String> wr = new WeakReference<String>(new String("hello"), queue);
        System.out.println(wr.get());
        System.gc();
        // WeakReference入列  referent已不可达
        System.out.println(wr.get());
        Reference reference1;
        // 入列了并且referent不可达了
        while ((reference1 = queue.remove()) != null) {
            System.err.println(reference1.get());
        }
        System.err.println(wr.get());*/

        /*SoftReference<String> wr = new SoftReference<String>(new String("hello"), queue);
        System.out.println(wr.get());
        System.gc();
        // 没有入列  referent仍然可达
        System.out.println(wr.get());
        Reference reference1;
        // 未入列
        while ((reference1 = queue.remove()) != null) {
            System.err.println(reference1.get());
        }
        System.err.println(wr.get());*/

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);
        buffer = null;
        System.gc();
        /*try {
          Thread.sleep(15000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        Field cleanerField= buffer.getClass().getDeclaredField("cleaner");
        long fieldOffset = UNSAFE.objectFieldOffset(cleanerField);
        Object cleaner = UNSAFE.getObject(buffer, fieldOffset);
        Method method = Cleaner.class.getDeclaredMethod("clean");
        method.invoke(cleaner);

        // System.gc();

        try {
          Thread.sleep(100000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }*/
    }

    private static Object getReferent(Reference reference) throws Exception {
        Field field = reference.getClass().getSuperclass().getDeclaredField("referent");
        field.setAccessible(true);
        return field.get(reference);
    }
}
