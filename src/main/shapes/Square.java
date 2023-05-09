package main.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.Random;

public class Square extends Shape {
    protected int side;
    protected int perimeter;
    protected int direction;
    protected Color strokeColor;

    public Square() {
    }

    public Square(int x, int y, int side, Color color, Color strokeColor, int perimeter) {
        super(x, y, color);
        this.side = side;
        this.perimeter = perimeter;
        this.direction = 0;
        this.strokeColor = strokeColor;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public void updatePosition(int width, int height) {
        switch (direction) {
            case 0: // move right
                x += 10;
                if (x + side >= perimeter) {
                    x = perimeter - side;
                    y += 10;
                    direction = 1;
                }
                break;
            case 1: // move up
                y -= 10;
                if (y <= 0) {
                    y = 0;
                    x -= 10;
                    direction = 2;
                }
                break;
            case 2: // move left
                x -= 10;
                if (x <= 0) {
                    x = 0;
                    y -= 10;
                    direction = 3;
                }
                break;
            case 3: // move down
                y += 10;
                if (y + side >= perimeter) {
                    y = perimeter - side;
                    x += 10;
                    direction = 0;
                }
                break;
        }
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(color);
        g2d.fillRect(x, y, side, side);
        Stroke stroke = new BasicStroke(5);
        g2d.setStroke(stroke);
        g2d.setColor(strokeColor);
        g2d.drawRect(x, y, side, side);
    }
}
