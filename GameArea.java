import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameArea extends JPanel
{
    public static final int height = Frame.height - 80;
    public static final int width = (GameArea.height)/2;

    public Tetromino tetromino;
    public Tetromino nextTetromino;
    public Color[][] background;
    public char[][] console;
    public int count;
    public int shadow_y;
    public int score = 0;
    public boolean gameOver = false;
    RandomBag bag;
    public ScoreArea sa;

    public GameArea(ScoreArea sa) 
    {
        this.sa = sa;

        this.setBounds(20, 20, GameArea.width, GameArea.height);
        this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        this.setBackground(Color.BLACK);

        this.count = 0;

        this.bag = new RandomBag();
        this.bag.fill();
        
        this.nextTetromino = bag.getTetromino();
        this.count = 1;

        this.background = new Color[20][10];
        this.console = new char[20][10];

        for(int i=0; i<20; i++)
        {
            for(int j=0; j<10; j++)
            {
                this.console[i][j] = '.';
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Gamearea 
        drawGrid(g);
        drawBackground(g);
        drawTetromino(g);
        drawShadow(g);

        // Console
        drawTetromino();
        
        if(gameOver == true)
        {
            g.setColor(new Color(0, 0, 0,200));
            g.fillRect(0,0,width,height);
            g.setColor(Color.MAGENTA);
            Font myFont=new Font("TimesRoman",Font.BOLD,50);
            g.setFont(myFont);
            g.drawString("GAME OVER", 20, 325);
            g.drawString("SCORE : "+score, 37, 395);
        }
    }

    //
    public void displayGameOver()
    {
        gameOver = true;
        repaint();
    }

    public void drawGrid(Graphics g)
    {
        g.setColor(Color.WHITE);
        
        for(int i=1; i<=19; i++)
            g.drawLine(0, i*Cell.size, GameArea.width, i*Cell.size);

        for(int i=1; i<=9; i++)
            g.drawLine(i*Cell.size, 0, i*Cell.size, GameArea.height);
    }

    public void spawnTetromino()
    {
        if(count == 0)
        {
            bag.fill();
        }

        tetromino=nextTetromino;
        nextTetromino = bag.getTetromino();

        count = (count+1) % 7;
    
        sa.setNextTetromin(nextTetromino);
    }

    public void drawTetromino(Graphics g)
    {
        for(int row=0; row < tetromino.height; row++)  
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int x = (tetromino.x + column) * Cell.size;
                    int y = (tetromino.y + row) * Cell.size;

                    g.setColor(tetromino.colour);
                    g.fillRect(x, y, Cell.size, Cell.size);

                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, Cell.size, Cell.size);
                }
            }
        }
    }

    public void drawTetromino()
    {
        RowColumnPair pair;

        ArrayList<RowColumnPair> arr = new ArrayList<>();
        
        for(int row=0; row < tetromino.height; row++)  
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1) 
                {
                    int r = (tetromino.y + row);   
                    int c = (tetromino.x + column);

                    if(r>=0)
                    {
                        console[r][c] = tetromino.name;

                        arr.add( new RowColumnPair(r, c) );
                    }
                }
            }
        }

        drawConsole();

        for(int i=0; i < arr.size(); i++)
        {
            pair = arr.get(i);

            int row = pair.row;
            int column = pair.column;

            console[row][column] = '.';
        }
    }

    public void drawShadow(Graphics g)
    {
        for(int row=0; row < tetromino.height; row++)  
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int x = (tetromino.x + column) * Cell.size;
                    int y = (this.shadow_y + row) * Cell.size;
                    
                    g.setColor(makeTransparent(tetromino.colour, 50));
                    g.fillRect(x, y, Cell.size, Cell.size);

                    g.setColor(Color.MAGENTA);
                    g.drawRect(x, y, Cell.size, Cell.size);
                }
            }
        }
    }

    public boolean moveTetrominoDown()
    {
        if(tetromino.y + tetromino.height != 20) 
        {
            if(checkDown() == true)
            {
                tetromino.y ++;
                repaint(); 
                return true;
            }
            else
            {
                return false; 
            }
        }
        else
        {
            return false; 
        }
    }

    public void moveTetrominoRight()
    {
        if(tetromino.x + tetromino.width != 10)
        {
            if(checkRight() == true)
            {
                tetromino.x ++;
                setShadow();   
                repaint();
            }
            else
            {
                return;
            }
        }
        else
        {
            return;   
        }
    }

    public void moveTetrominoLeft()
    {
        if(tetromino.x != 0)
        {
            if(checkLeft() == true)
            {
                tetromino.x --;
                setShadow();  
                repaint();
            }
            else
            {
                return;
            }
        }
        else
        {
            return;   
        }
    }

    public void dropDownTetrominoInstantly()
    {
        while(tetromino.y + tetromino.height != 20 )
        {
            if(checkDown() == true)
            {
                tetromino.y ++;
                repaint(); 
            }
            else
            {
                break;
            }
        }
    }

    public void setShadow()
    {
        this.shadow_y=tetromino.y;
        
        while(this.shadow_y + tetromino.height != 20)
        {
            if(checkDown2() == true)
            {
                this.shadow_y ++; 
                repaint(); 
            }
            else
            {
                break;
            }
        }
    }

    public void rotateTetromino()
    {
        int[][] newShape = new int[tetromino.width][tetromino.height];
        int[][] oldShape = tetromino.shape;
        int c = 0;

        for(int row = tetromino.height - 1; row>=0; row--)
        {
            for(int column=0; column < tetromino.width; column++)
            {
                newShape[column][c] = tetromino.shape[row][column];
            }

            c++;
        }

        tetromino.shape = newShape;

        tetromino.height = tetromino.shape.length;
        tetromino.width = tetromino.shape[0].length;

        if(tetromino.x + tetromino.width > 10)
        {
            tetromino.x = 10 - tetromino.width;
        }

        if(tetromino.y + tetromino.height > 20)
        {
            tetromino.y = 20 - tetromino.height;
        }

        if(canTetrominoRotate() == true)
        {
            setShadow(); 
            repaint();
        }
        else
        {
            tetromino.shape = oldShape;

            tetromino.height = tetromino.shape.length;
            tetromino.width = tetromino.shape[0].length;
        }
    }

    public boolean checkDown()
    {
        tetromino.y ++;

        for(int row = tetromino.height - 1; row>=0; row--)
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int r = tetromino.y + row;
                    int c = tetromino.x + column;

                    if(r<0)
                    {
                        tetromino.y --;
                        return true;
                    }
                
                    if(background[r][c] != null)
                    {
                        tetromino.y --;
                        return false;
                    }
                }
            }
        }

        tetromino.y --;
        return true;      
    }

    public boolean checkDown2()
    {
        this.shadow_y ++;

        for(int row = tetromino.height - 1; row>=0; row--)
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int r = this.shadow_y + row;
                    int c = tetromino.x + column;

                    if(r<0)
                    {
                        this.shadow_y --;
                        return true;
                    }
            
                    if(background[r][c] != null)
                    {
                        this.shadow_y --;
                        return false;
                    }
                }
            }
        }

        this.shadow_y --;
        return true;      
    }

    public boolean checkLeft()
    {
        tetromino.x --;

        for(int column=0; column < tetromino.width; column++)
        {
            for(int row=0; row < tetromino.height; row++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int r = tetromino.y + row;
                    int c = tetromino.x + column;

                    if(r<0)
                    {
                        tetromino.x ++;
                        return true;
                    }
                
                    if(background[r][c] != null)
                    {
                        tetromino.x ++;
                        return false;
                    }
                }
            }  
        }

        tetromino.x ++;
        return true;
    }

    public boolean checkRight()
    {
        tetromino.x ++;

        for(int column = tetromino.width - 1; column>=0; column--)
        {
            for(int row=0; row < tetromino.height; row++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int r = tetromino.y + row;
                    int c = tetromino.x + column;

                    if(r<0)
                    {
                        tetromino.x --;
                        return true;
                    }
                
                    if(background[r][c] != null)
                    {
                        tetromino.x --;
                        return false;
                    }
                }
            }  
        }

        tetromino.x --;
        return true;
    }

    public void clearRowsAndShiftDown()
    {
        boolean rowFilled;

        for(int row=19; row>=0; row--)
        {
            rowFilled = true;

            for(int column=0; column<10; column++)
            {
                if(background[row][column] == null)
                {
                    rowFilled = false;
                    break;
                }
            }

            if(rowFilled == true)
            {
                clearRow(row);
                shiftDown(row);

                repaint();

                score+=10;
                sa.displayScore(score);
                System.out.println("Score "+score);
                row++;
            }
        }
    }

    public void clearRow(int row)
    {
        // background matrix
        for(int column=0; column<10; column++)
        {
            background[row][column] = null;
        }

        // console matrix
        for(int column=0; column<10; column++)
        {
            console[row][column] = '.';
        }
    }

    public void shiftDown(int r)
    {
        // background matrix
        for(int row=r; row>0; row--)
        {
            for(int column=0; column<10; column++)
            {
                background[row][column] = background[row-1][column];
            }
        }

        // console matrix
        for(int row=r; row>0; row--)
        {
            for(int column=0; column<10; column++)
            {
                console[row][column] = console[row-1][column];
            }
        }

        clearRow(0);
    }

    public boolean isTetrominoOutOfGameArea()
    {
        if(tetromino.y < 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean canTetrominoRotate()
    {
        for(int row=0; row < tetromino.height; row++)
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    int r = tetromino.y + row;
                    int c = tetromino.x + column;
                
                    if(background[r][c] != null)
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    public void drawBackground(Graphics g)
    {
        Color colour;

        for(int row=0; row<20; row++)
        {
            for(int column=0; column<10; column++)  
            {
                colour = background[row][column];

                if(colour != null)
                {
                    int x = column * Cell.size;
                    int y = row * Cell.size;

                    g.setColor(colour);
                    g.fillRect(x, y, Cell.size, Cell.size);

                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, Cell.size, Cell.size);
                }
            } 
        }
    }

    public void drawConsole()
    {
        System.out.print("\033\143");

        for(int i=0; i<20; i++)
        {
            for(int j=0; j<10; j++)
            {
                System.out.print(console[i][j] + "  ");
            }

            System.out.println();
        }
    }

    public void moveTetrominoToBackground()
    {
        for(int row=0; row < tetromino.height; row++)  
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    background[tetromino.y + row][tetromino.x + column] = tetromino.colour;
                }
            }
        }
    }

    public void moveTetrominoToConsole()
    {
        for(int row=0; row < tetromino.height; row++)  
        {
            for(int column=0; column < tetromino.width; column++)
            {
                if(tetromino.shape[row][column] == 1)
                {
                    console[tetromino.y + row][tetromino.x + column] = tetromino.name;
                }
            }
        }
    }

    public Color makeTransparent(Color source, int alpha)
    {
        return new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);
    }
}
