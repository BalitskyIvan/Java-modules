package edu.school21.printer.app;
import edu.school21.printer.logic.*;

public class Start {

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                throw new MyException("incorrect number of arguments");
            }
            CheckSignature checkInput = new CheckSignature();
            checkInput.checkBmpFile(args[2]);
            Print print = new Print(args[0], args[1], args[2]);
            print.printImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
