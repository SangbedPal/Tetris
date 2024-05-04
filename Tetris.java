import java.awt.EventQueue;

public class Tetris
{
    @SuppressWarnings("Unchecked")
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Frame frame = new Frame();

                ScoreArea sa = new ScoreArea();
                frame.get().add(sa);

                GameArea ga = new GameArea(sa);
                frame.get().add(ga);
        
                GameThread thread = new GameThread(ga);
                thread.start();

                Control control = new Control(frame, ga);
                control.initControls();
            }
        });
    }
}
