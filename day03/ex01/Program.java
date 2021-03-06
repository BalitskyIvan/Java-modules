public class Program {

    public static void main(String[] args) {
        if (args.length == 1) {
            int count = checkArg(args[0]);
            try {
                Object mute = new Object();
                Thread sayEgg = new Thread(new MyRunnable(mute, "Egg", count));
                Thread sayHen = new Thread(new MyRunnable(mute, "Hen", count));
                sayEgg.start();
                sayHen.start();
                sayEgg.join();
                sayHen.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int checkArg(String count) {
        String countString = count.substring(0, 8);
        Integer result = Integer.parseInt(count.substring(8,count.length()));
        if (result < 0 || !(countString.equals("--count="))) {
            System.err.println("Wrong argument");
            System.exit(-1);
        }
        return result;
    }
}
