import java.awt.*;
import java.util.*;

public class Ball extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int ballSpeed = 3;

    public Ball(int x, int y, int width, int height) {
        super(x,y,width,height);
        random = new Random();
        int randomxDirection = random.nextInt(2);
        if(randomxDirection == 0)
            randomxDirection--;
        setxDirection(randomxDirection*ballSpeed);
        int randomyDirection = random.nextInt(2);
        if(randomyDirection == 0)
            randomyDirection--;
        setyDirection(randomyDirection*ballSpeed);
    }
    public void setxDirection(int randomxDirection){
        xVelocity = randomxDirection;
    }
    public void setyDirection(int randomyDirection){
        yVelocity = randomyDirection;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.blue);
        g.fillOval(x,y,width,height);
    }
}
