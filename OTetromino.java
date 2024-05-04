import java.awt.Color;

public class OTetromino extends Tetromino
{
    public OTetromino()
    {
        int[][] shape = { {1, 1}, {1, 1} };
        this.shape = shape;

        this.colour = new Color(255, 255, 0);

        this.height = this.shape.length;
        this.width = this.shape[0].length;
        this.x = (10 - this.width)/2;
        this.y = 0-this.height;

        this.name = 'O';
    }
}
