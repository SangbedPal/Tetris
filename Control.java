import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import java.awt.event.ActionEvent;

public class Control 
{
    Frame frame;
    GameArea ga;

    public Control(Frame frame, GameArea ga)
    {
        this.frame = frame;
        this.ga = ga;
    }

    public void initControls()
    {
        InputMap im = frame.getRootPane().getInputMap();
        ActionMap am = frame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"), "move tetromino right");
        im.put(KeyStroke.getKeyStroke("LEFT"), "move tetromino left");
        im.put(KeyStroke.getKeyStroke("DOWN"), "drop down tetromino instantly");
        im.put(KeyStroke.getKeyStroke("UP"), "rotate tetromino");

        am.put("move tetromino right", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ga.moveTetrominoRight();
            }
        });

        am.put("move tetromino left", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ga.moveTetrominoLeft();
            }
        });

        am.put("drop down tetromino instantly", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ga.dropDownTetrominoInstantly();
            }
        });

        am.put("rotate tetromino", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ga.rotateTetromino();
            }
        });
    }
}
