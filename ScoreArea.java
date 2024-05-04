import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class ScoreArea extends JPanel
{
    public static final int height = Frame.height - 80;
    public static final int width = (ScoreArea.height)/2;
    public Tetromino nextTetromino;
    public Color[][] background;
    public int count;
    public int sh_y;
    public int score=0;
    public boolean gameOver=false;
    RandomBag bag;

    public ScoreArea() 
    {
        this.setBounds(ScoreArea.width + 40, 20, ScoreArea.width, ScoreArea.height);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setBackground(Color.BLACK);
        this.background = new Color[20][10];
        
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        displayNextTetromino(g);

        g.setColor(Color.MAGENTA);
        Font myFont=new Font("TimesRoman",Font.BOLD,40);
        g.setFont(myFont);
        g.drawString("SCORE : "+score, 90, 195);
    }

    public void displayScore(int score)
    {
        this.score=score;
        repaint();
    }

    public void setNextTetromin(Tetromino nextTetromino)
    {
        this.nextTetromino=nextTetromino;
        repaint();
    }
    
    public void displayNextTetromino(Graphics g)
    {
        for(int row=0; row < nextTetromino.height; row++)  
        {
            for(int column=0; column < nextTetromino.width; column++)
            {
                if(nextTetromino.shape[row][column] == 1)
                {
                    int x = (nextTetromino.x +column) * Cell.size;
                    if (nextTetromino.width%2==1)
                        x+=20;
                    int y = (nextTetromino.y+row) * Cell.size+400;

                    g.setColor(nextTetromino.colour);
                    g.fillRect(x, y, Cell.size, Cell.size);

                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, Cell.size, Cell.size);
                }
            }
        }
    }
}    