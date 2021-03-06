import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class Program {

    public static void main(String[] args) {

        FileReader fileReader = new FileReader("signatures.txt");
        try {
            fileReader.readFile();
            HashMap<String, String[]> signatures = fileReader.getSignatures();
            FileOutputStream fos = new FileOutputStream("result.txt");
            readInputFile(signatures, fos);
            fos.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    private static void readInputFile(HashMap<String, String[]> signatures, FileOutputStream fos) throws IOException {
        String path = "";
        int readSymbol = 0;
        while (true) {
            readSymbol = System.in.read();
            while (System.in.available() > 0) {
                path += (char)readSymbol;
                readSymbol = System.in.read();
            }
            if (path.equals("42")) {
                break;
            }
            System.out.println(findSignature(signatures, path, fos));
            path = "";
        }
    }
    private static String findSignature(HashMap<String, String[]> signatures, String path, FileOutputStream fos) {
        boolean signatureFind = false;
        String keyToWrite = "";
        try {
            FileInputStream fin = new FileInputStream(path);
            for (String key : signatures.keySet()) {
                fin.getChannel().position(0);
                String[] valuesInput = signatures.get(key);
                int readByte = -1;
                for (int i = 0; i < valuesInput.length; i++) {
                    if (((readByte = fin.read()) != -1) &&
                            (valuesInput[i].equals(String.format("%02x", readByte).toLowerCase()))) {
                        signatureFind = true;
                    } else {
                        signatureFind = false;
                        break;
                    }
                }
                if (signatureFind) {
                    keyToWrite = key + "\n";
                    break ;
                }
            }
            fin.close();
        } catch (Exception e) {
            return ("UNDEFINED");
        }
        if (signatureFind) {
            try {
                fos.write(keyToWrite.getBytes(StandardCharsets.UTF_8));
                return ("PROCESSED");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ("UNDEFINED");
            }
        } else {
            return ("UNDEFINED");
        }
    }
}
