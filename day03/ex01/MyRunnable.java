public class MyRunnable implements Runnable{
    public final Object mute;
    public String phrase;
    public int count;

    public MyRunnable(Object mute, String phrase, int count) {
        this.mute = mute;
        this.phrase = phrase;
        this.count = count;
    }

    @Override
    public void run() {
        synchronized (mute) {
            for (int i = 0; i < count; i++) {
                mute.notify();
                System.out.println(phrase);
                try {
                    if (i + 1 != count) {
                        mute.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
