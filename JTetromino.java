import java.awt.Color;

public class JTetromino extends Tetromino
{
    public JTetromino()
    {
        int[][] shape = { {1, 0, 0}, {1, 1, 1} };
        this.shape = shape;

        this.colour = new Color(0, 0, 255);

        this.height = this.shape.length;
        this.width = this.shape[0].length;
        this.x = (10 - this.width)/2;
        this.y = 0-this.height;

        this.name = 'J';
    }
}
