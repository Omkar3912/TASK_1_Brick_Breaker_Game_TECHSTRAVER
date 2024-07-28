import java.awt.Graphics;
import java.awt.Color;

public class Ball {
    private int x, y, diameter, xDir, yDir;

    public Ball(int x, int y, int diameter, int xDir, int yDir) {
        this.x = x;
        this.y = y;
        this.diameter = diameter;
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move() {
        x += xDir;
        y += yDir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getXDir() {
        return xDir;
    }

    public int getYDir() {
        return yDir;
    }

    public void setXDir(int xDir) {
        this.xDir = xDir;
    }

    public void setYDir(int yDir) {
        this.yDir = yDir;
    }
}
