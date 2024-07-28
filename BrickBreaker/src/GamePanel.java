import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements ActionListener {
    private Timer timer;
    private int delay = 8;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<Brick> bricks;

    public GamePanel() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setBackground(Color.BLACK);
        addKeyListener(new TAdapter());
        timer = new Timer(delay, this);
        timer.start();

        paddle = new Paddle(300, 550, 100, 10);
        ball = new Ball(350, 530, 20, -1, -2);
        bricks = new ArrayList<>();
        int brickWidth = 60;
        int brickHeight = 20;
        int rows = 5;
        int cols = 10;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                bricks.add(new Brick(j * brickWidth + 30, i * brickHeight + 50, brickWidth, brickHeight));
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        paddle.draw(g);
        ball.draw(g);

        for (Brick brick : bricks) {
            brick.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.move();
        checkCollision();
        repaint();
    }

    private void checkCollision() {
        if (ball.getY() + ball.getDiameter() >= paddle.getY() &&
                ball.getX() + ball.getDiameter() >= paddle.getX() &&
                ball.getX() <= paddle.getX() + paddle.getWidth()) {
            ball.setYDir(-ball.getYDir());
        }

        if (ball.getX() <= 0 || ball.getX() + ball.getDiameter() >= getWidth()) {
            ball.setXDir(-ball.getXDir());
        }

        if (ball.getY() <= 0) {
            ball.setYDir(-ball.getYDir());
        }

        for (Brick brick : bricks) {
            if (brick.isVisible() &&
                    ball.getX() + ball.getDiameter() >= brick.getX() &&
                    ball.getX() <= brick.getX() + brick.getWidth() &&
                    ball.getY() + ball.getDiameter() >= brick.getY() &&
                    ball.getY() <= brick.getY() + brick.getHeight()) {
                ball.setYDir(-ball.getYDir());
                brick.setVisible(false);
            }
        }
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT) {
                paddle.moveLeft();
            } else if (key == KeyEvent.VK_RIGHT) {
                paddle.moveRight();
            }
        }
    }
}
