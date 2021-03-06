package edu.school21.printer.logic;
import java.io.FileInputStream;
import java.io.IOException;

public class CheckSignature {
    private final  String[] bmpSignature = {"42", "4d"};

    public CheckSignature() {}

    public void checkBmpFile(String fileName) throws IOException, MyException {
        FileInputStream file = new FileInputStream(fileName);
        int readByte;
        for (int i = 0; i < bmpSignature.length; i++) {
            if (!(((readByte = file.read()) != -1) &&
                    bmpSignature[i].equals(String.format("%02x", readByte).toLowerCase()))) {
                throw new MyException("Not BMP file format");
            }
        }
    }
}
