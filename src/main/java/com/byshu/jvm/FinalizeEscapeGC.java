package com.byshu.jvm;

public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 第一次拯救成功
        SAVE_HOOK = null;
        System.gc();
        // finalize方法优先级很低，暂定0.5s以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            System.out.println("i am still alive!");
        } else {
            System.out.println("no, i am dead..");
        }


        // 第二次拯救失败
        SAVE_HOOK = null;
        System.gc();
        // finalize方法优先级很低，暂定0.5s以等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            System.out.println("i am still alive!");
        } else {
            System.out.println("no, i am dead..");
        }
    }

}
