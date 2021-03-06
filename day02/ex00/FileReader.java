import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FileReader {
    private final HashMap<String, String[]> knownSignatures;
    private final String path;

    FileReader(String path) {
        this.path = path;
        this.knownSignatures = new HashMap<>();
    }
    public HashMap<String, String[]> getSignatures() {
        return knownSignatures;
    }
    public void readFile() throws IOException {
        FileInputStream fin = new FileInputStream(this.path);
        byte[] buff = new byte[fin.available()];
        fin.read(buff, 0, fin.available());
        String readToString = new String(buff);
        formingHashMap(readToString);
        fin.close();
    }

    private void formingHashMap(String str)  {
        String[] str1 = str.split("\n");
        for (int i = 0; i < str1.length; ++i) {
            String[] pair = str1[i].split(",");
            knownSignatures.put(pair[0], pair[1].trim().toLowerCase(Locale.ROOT).split(" "));
        }
    }
}
