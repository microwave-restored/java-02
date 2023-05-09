package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import main.components.GraphicsPanel;
import main.shapes.Shape;
import main.shapes.Square;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        int perimeter = 800;
        int squareSize1 = 100;
        int squareSize2 = 50;

        Square square1 = new Square(perimeter / 2 - squareSize1 / 2, perimeter / 2 - squareSize1 / 2, squareSize1, Color.BLUE, Color.BLUE, perimeter);
        Square square2 = new Square(square1.getX() + square1.getSide() / 2 - squareSize2 / 2, square1.getY() + square1.getSide() / 2 - squareSize2 / 2, squareSize2, Color.RED, Color.RED, perimeter);

        shapes.add(square1);
        shapes.add(square2);

        GraphicsPanel canvas = new GraphicsPanel(shapes);

        Runnable squareController = () -> {
            Color color2 = Color.RED;
            Color strokeColor1 = Color.BLACK;
            Color strokeColor2 = Color.BLACK;

            while (true) {
                square1.updatePosition(canvas.getWidth(), canvas.getHeight());
                square2.updatePosition(canvas.getWidth(), canvas.getHeight());

                if (square2.getY() <= 0 || square2.getY() + square2.getSide() >= canvas.getHeight()) {
                    color2 = Color.GREEN;
                    strokeColor2 = Color.YELLOW;
                }

                if (square2.getX() <= 0 || square2.getX() + square2.getSide() >= canvas.getWidth()) {
                    color2 = Color.ORANGE;
                    strokeColor2 = Color.RED;
                }

                square2.setColor(color2);
                square2.setStrokeColor(strokeColor2);

                if (square1.getY() <= 0 || square1.getY() + square1.getSide() >= canvas.getHeight()) {
                    strokeColor1 = Color.GRAY;
                }

                if (square1.getX() <= 0 || square1.getX() + square1.getSide() >= canvas.getWidth()) {
                    strokeColor1 = Color.GREEN;
                }

                square1.setStrokeColor(strokeColor1);

                square1.setX(square2.getX() + square2.getSide() / 2 - square1.getSide() / 2);
                square1.setY(square2.getY() + square2.getSide() / 2 - square1.getSide() / 2);

                canvas.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(perimeter, perimeter);
        frame.add(canvas);
        frame.setVisible(true);

        Thread animationThread = new Thread(squareController);
        animationThread.start();
    }
}