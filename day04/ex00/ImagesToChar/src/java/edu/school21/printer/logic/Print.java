package edu.school21.printer.logic;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Print {
    private final char white;
    private final char black;
    private char[][] imageToPrint;
    private int height;
    private int width;

    public Print(String strWhite, String strBlack, String path)  {
        this.white = strWhite.charAt(0);
        this.black = strBlack.charAt(0);
        try {
            convertImage(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertImage(String path) throws IOException {
        BufferedImage image = ImageIO.read(new File(path));
        height = image.getHeight();
        width = image.getWidth();
        imageToPrint = new char[height][width];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int color = image.getRGB(i, j);
                if (color == -1) {
                    imageToPrint[i][j] = white;
                } else {
                    imageToPrint[i][j] = black;
                }
            }
        }
    }

    public void printImage() {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                System.out.print(imageToPrint[i][j]);
            }
            System.out.println();
        }
    }

}
