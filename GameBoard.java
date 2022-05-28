import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameBoard extends JPanel implements Runnable{
    static final int GAME_WIDTH = 500;
    static final int GAME_HEIGHT = 500;
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 100;
    static final int PADDLE_HEIGHT = 10;
    static int no_of_bricks = 20;
    Thread gameThread;
    Image image;
    Graphics graphics;
    //Random random;
    Paddle paddle;
    Ball ball;
    BricksGenerator map;

    public GameBoard() {
        newPaddle();
        newBall();
        newBricks();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBricks(){
        map = new BricksGenerator(4,6);
    }
    public void newBall(){
        ball= new Ball((GAME_WIDTH-BALL_DIAMETER)/2,GAME_HEIGHT-PADDLE_HEIGHT-BALL_DIAMETER,BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddle(){
        //in the beginning of the game, the paddle must appear in the middle of the bottom of the window
        paddle = new Paddle((GAME_WIDTH-PADDLE_WIDTH)/2,GAME_HEIGHT-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        paddle.draw(g);
        ball.draw(g);
        map.draw((Graphics2D)g);
    }
    public void move(){
        //makes the ball and paddle move smoother
        //we call each of this method after each iteration of our game loop
        paddle.move();
        ball.move();
    }
    public void checkCollision(){
        //bounce ball off top window edge
        if(ball.y<=0){  //top
            ball.setyDirection(-ball.yVelocity);
        }
        //bounce ball off left and right window edges
        if(ball.x<=0){ //left
            ball.setxDirection(-ball.xVelocity);
        }
        if(ball.x>=GAME_WIDTH-BALL_DIAMETER){  //right
            ball.setxDirection(-ball.xVelocity);
        }
        //bounce ball off paddle
        if(ball.intersects(paddle)){
            ball.yVelocity = (-ball.yVelocity);
        }
        //makes brick disappear when ball hits
        BB: for(int i=0; i<map.map.length; i++){
            for(int j=0; j<map.map[0].length; j++){
                if(map.map[i][j]>0){
                    int width = map.BRICK_WIDTH;
                    int height = map.BRICK_HEIGHT;
                    int x = 50+j*width;
                    int y = 25+i*height;
                    Rectangle brickRectangle = new Rectangle(x,y,width,height);
                    if(ball.intersects(brickRectangle)){
                        map.setBrick(0,i,j);
                        no_of_bricks--;
                        //make ball bounce off the destroyed brick
                        if(ball.x+20<=x || ball.x+1>=x+width){
                            ball.xVelocity = -ball.xVelocity;
                        }
                        else{
                            ball.yVelocity = -ball.yVelocity;
                        }
                        break BB;
                    }
                }
            }
        }
        //stops paddle at window edge
        if(paddle.x<=0){
            paddle.x = 0;
        }
        if(paddle.x >= (GAME_WIDTH-PADDLE_WIDTH)){
            paddle.x = GAME_WIDTH-PADDLE_WIDTH;
        }
        //creates new paddle and ball
        if(ball.y>=GAME_HEIGHT-BALL_DIAMETER){
            newPaddle();
            newBall();
            newBricks();
        }
    }
    public void run(){
        //game loop
        long lastTime = System.nanoTime();//very long value
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        while(true){
            long now = System.nanoTime();//returns the current value of the most precise available system timer, in nanoseconds
            delta += (now-lastTime)/ns;
            lastTime = now;
            if(delta>=1){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }
    }
}
