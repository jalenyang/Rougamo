package org.pork.gc;

/**
 * Created by jalyang on 2018/2/6.
 */
public class FinalizeEscapeGC {
    private static FinalizeEscapeGC SAVE_HOOK = null;

    private void isAlive() {
        System.out.println("yes, I am alive");
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("fianlize method executed");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead");
        }

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (null != SAVE_HOOK) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead");
        }
    }
}
