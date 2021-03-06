import java.io.FileInputStream;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Wrong number of arguments!");
            return;
        }
        try {
            FileInputStream finA = new FileInputStream(args[0]);
            FileInputStream finB = new FileInputStream(args[1]);
            String[] inputA = toStringArr(finA);
            String[] inputB = toStringArr(finB);
            TreeSet<String> uniqWords = new TreeSet<String>(Arrays.asList(inputA));
            uniqWords.addAll(Arrays.asList(inputB));
            ArrayList<Integer> vectorA = convertToVector(uniqWords, inputA);
            ArrayList<Integer> vectorB = convertToVector(uniqWords, inputB);
            calculate(vectorA, vectorB);
            finA.close();
            finB.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static ArrayList<Integer> convertToVector(TreeSet<String> uniqWords, String[] array) {
        List<String> list = Arrays.asList(array);
        ArrayList<Integer> vector = new ArrayList<>();
        for (String i : uniqWords) {
            vector.add(Collections.frequency(list, i));
        }
        return vector;
    }

    private static void calculate(ArrayList<Integer> vectorA, ArrayList<Integer> vectorB) {
        Double similarity = calculationSimilarity(vectorA, vectorB);
        if (similarity == 0) {
            System.out.println("Similarity = 0.0");
            return;
        }
        String resultSimilarity = similarity.toString();
        System.out.print("Similarity = ");
        if (resultSimilarity.length() < 4) {
            System.out.print(resultSimilarity);
        } else {
            if (resultSimilarity.equals("0.9999999999999999")) {
                System.out.println("1.0");
                return;
            }
            System.out.println(resultSimilarity.substring(0, 4));
        }
    }
    private static double calculationSimilarity(ArrayList<Integer> vectorA, ArrayList<Integer> vectorB) {
        int numerator = 0;
        double leftDenominator = 0;
        double rightDenominator = 0;
        for (int i = 0; i < vectorA.size(); ++i) {
            numerator += vectorA.get(i) * vectorB.get(i);
            leftDenominator += vectorA.get(i) * vectorA.get(i);
            rightDenominator += vectorB.get(i) * vectorB.get(i);
        }
        return numerator / (sqrt(leftDenominator) * sqrt(rightDenominator));
    }

    private static double sqrt(double number) {
        double r;
        double squareRoot = number / 2;
        do {
            r = squareRoot;
            squareRoot = (r + (number / r)) / 2;
        } while ((r - squareRoot) != 0);
        return squareRoot;
    }

    private static String[] toStringArr(FileInputStream fin) throws Exception {
        byte[] buff = new byte[fin.available()];
        fin.read(buff, 0, fin.available());
        String str = new String(buff);
        return str.trim().split(" |\r\n");
    }
}
