import java.awt.Color;

public class STetromino extends Tetromino
{
    public STetromino()
    {
        int[][] shape = { {0, 1, 1}, {1, 1, 0} };
        this.shape = shape;

        this.colour = new Color(0, 255, 0);

        this.height = this.shape.length;
        this.width = this.shape[0].length;
        this.x = (10 - this.width)/2;
        this.y = 0-this.height;

        this.name = 'S';
    }
}
