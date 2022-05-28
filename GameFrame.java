import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {
    GameBoard board;
    public GameFrame(){
        board = new GameBoard();
        this.add(board);
        this.setTitle("Brick Breaker");
        this.setResizable(false);
        this.setBackground(new Color(233,189,230));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
