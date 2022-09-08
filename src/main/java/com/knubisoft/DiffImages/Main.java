package com.knubisoft.DiffImages;

import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    @SneakyThrows
    public static void main(String[] args) {
        File file = new File("D:\\Projects\\DifferenceImages\\src\\main\\resources\\t2.png");
        File file2 = new File("D:\\Projects\\DifferenceImages\\src\\main\\resources\\t1.png");

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
        List<Point> pointList = new ArrayList<>();

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int pixel = img.getRGB(x, y);
                int pixel2 = img2.getRGB(x, y);

                Color color = new Color(pixel, true);
                Color color2 = new Color(pixel2, true);

                buildImage(color, color2, img2, x, y, group, pointList);

            }
        }

        drawRectangle(pointList, img2);
        writeImage(img2);
    }

    private static void drawRectangle(List<Point> pointList, BufferedImage img) {
        int firstX = pointList.get(0).getX();
        int firstY = pointList.get(0).getY();

        Graphics2D g2d = img.createGraphics();

        g2d.setColor(Color.red);
        g2d.drawRect(firstX - 2, firstY - 5, firstY / 2, 25);
        g2d.dispose();

    }

    private static void buildImage(Color color, Color color2, BufferedImage img, int x, int y, Group group, List<Point> pointList) {
        if (color2.equals(color)) {
            img.setRGB(x, y, color2.getRGB());
        } else {
            pointList.add(new Point(x, y));
            group.addPoint(new Point(x, y));

            img.setRGB(x, y, color2.getRGB());
        }
    }

    private static boolean isFilesMatch(BufferedImage img, BufferedImage img2) {

        return img.getHeight() == img2.getHeight() && img.getWidth() == img2.getWidth();
    }

    @SneakyThrows
    private static void writeImage(BufferedImage img) {
        File output = new File("D:\\Projects\\DifferenceImages\\src\\main\\resources\\res3.png");
        ImageIO.write(img, "png", output);

        System.out.println("File successfully created. Path " + output.getAbsolutePath());
    }

}
