import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle {
    int xVelocity;
    int speed = 10;//the paddle moves at the speed of 10 pixels with one key press

    public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            setxDirection(speed);
            move();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            setxDirection(-speed);
            move();
        }
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            setxDirection(0);
            move();
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            setxDirection(0);
            move();
        }
    }
    public void setxDirection(int xDirection){
        xVelocity = xDirection;
    }
    public void move(){
        x = x+xVelocity;
    }
    public void draw(Graphics g){
        g.setColor(new Color(105,52,101));
        g.fillRect(x,y,width,height);
    }
}
