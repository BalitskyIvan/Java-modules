package edu.school21.printer.app;
import edu.school21.printer.logic.*;
import com.beust.jcommander.*;

public class Start {

    public static void main(String[] args) {
        try {
            Arguments param = new Arguments();
            JCommander.newBuilder()
                    .addObject(param)
                    .build()
                    .parse(args);
            Print img = new Print(param.getFirstParam(), param.getSecondParam(), "target/resources/image.bmp");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
