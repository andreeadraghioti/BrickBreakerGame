import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class BricksGenerator {
    public int map[][];
    public int BRICK_WIDTH;
    public int BRICK_HEIGHT;

    public BricksGenerator(int row, int col){
        map = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j]=1;
            }
        }
        BRICK_WIDTH = 400/col;
        BRICK_HEIGHT = 200/row;
    }
    public void setBrick(int value, int r, int c){
        map[r][c] = value;
    }
    public void draw(Graphics2D g){
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if(map[i][j]>0){
                    g.setColor(new Color(177,220,214));
                    g.fillRect(j*BRICK_WIDTH+50, i*BRICK_HEIGHT+25, BRICK_WIDTH, BRICK_HEIGHT);
                    //bricks border:
                    g.setColor(new Color(233,189,230));
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*BRICK_WIDTH+50, i*BRICK_HEIGHT+25, BRICK_WIDTH, BRICK_HEIGHT);
                }
            }
        }
    }
}
