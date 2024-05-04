import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.Color;
import java.awt.Toolkit;

public class Frame extends JFrame  
{
    public static final int height = Toolkit.getDefaultToolkit().getScreenSize().height - 200;
    public static final int width = height/2 + 20 - 40/2 + (height/2 - 20);

    Container c;
    
    public Frame() 
    {
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Tetris Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(Frame.height, 0, Frame.width, Frame.height);

        ImageIcon icon = new ImageIcon("tetris.png");
        this.setIconImage(icon.getImage());

        this.c = this.getContentPane();
        this.c.setBackground(Color.GRAY);
        this.c.setLayout(null);
    }

    public Container get()
    {
        return this.c;
    }
}

