package com.knubisoft.DiffImages;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        File file = new File("D:\\Projects\\java-education\\src\\main\\resources\\1.png");
        File file2 = new File("D:\\Projects\\java-education\\src\\main\\resources\\2.png");

        readColors(file, file2);
    }

    @SneakyThrows
    private static void readColors(File file, File file2) {
        BufferedImage img = ImageIO.read(file);
        BufferedImage img2 = ImageIO.read(file2);

        if (!isFilesMatch(img, img2)) {
            return;
        }

        Group group = new Group();
        BufferedImage result = new BufferedImage(img2.getWidth(), img2.getHeight(), img2.getType());

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                int pixel2 = img2.getRGB(x, y);

                Color color = new Color(pixel, true);
                Color color2 = new Color(pixel2, true);

                //buildImage(color, color2, result, x, y, group);
                addGroup(group, x, y, color, color2);

            }
        }

        for (Point point1 : group.getPoints()) {
            System.out.println(point1.getX() + ";" + point1.getY());
        }

        //writeImage(result);
    }

    //TODO: Если пиксели находятся рядом (в радиусе 50 пикселей) или
    // занимают большую площадь, то прямоугольник должен подсвечивать всю площадь которая отличается.
    private static void buildImage(Color color, Color color2, BufferedImage result, int x, int y) {
        if (color2.equals(color)) {
            result.setRGB(x, y, color2.getRGB());
        } else {

            color2 = new Color(255, 0, 0);
            result.setRGB(x, y, color2.getRGB());
        }

    }

    private static void addGroup(Group group, int x, int y, Color color, Color color2) {
        if (!color2.equals(color)) {
            Point point = new Point(x, y);
            group.addPoint(point);
        }
    }

    private static boolean isFilesMatch(BufferedImage img, BufferedImage img2) {

        return img.getHeight() == img2.getHeight() && img.getWidth() == img2.getWidth();
    }

    @SneakyThrows
    private static void writeImage(BufferedImage result) {
        File output = new File("D:\\Projects\\java-education\\src\\main\\resources\\3.png");
        ImageIO.write(result, "png", output);

        System.out.println("File successfully created. Path " + output.getAbsolutePath());
    }

}