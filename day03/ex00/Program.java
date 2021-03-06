public class Program {

    public static void main(String[] args) {
        if (args.length == 1) {
            int count = checkArg(args[0]);
            try {
                Thread sayEgg = new Thread(sayPhrase("Egg", count));
                Thread sayHen = new Thread(sayPhrase("Hen", count));
                sayEgg.start();
                sayHen.start();
                sayEgg.join();
                sayHen.join();
            for (int i = 0; i < count; i++) {
                System.out.println("Human");
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Runnable sayPhrase(String phrase, int count) {
        return (() -> {
            for (int i = 0; i < count; i++) {
                System.out.println(phrase);
            }
        });
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
