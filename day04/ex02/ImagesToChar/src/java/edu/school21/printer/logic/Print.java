package edu.school21.printer.logic;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.diogonunes.jcdp.color.api.Ansi;

public class Print {
    private final String white;
    private final String black;
    private final String path;;

    public Print(String strWhite, String strBlack, String path)  {
        this.white = strWhite;
        this.black = strBlack;
        this.path = path;
        try {
            printImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        int height = image.getHeight();
        int width = image.getWidth();
        String str;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int color = image.getRGB(i, j);
                if (color == -1) {
                    str = Ansi.PREFIX +  Ansi.BColor.valueOf(this.white) + Ansi.POSTFIX;
                } else {
                    str = Ansi.PREFIX + Ansi.BColor.valueOf(this.black) + Ansi.POSTFIX;
                }
                System.out.print(str + "  ");
            }
            System.out.println(Ansi.PREFIX + Ansi.BColor.NONE + Ansi.POSTFIX);
        }
    }
}
