import java.awt.Color;

public class ZTetromino extends Tetromino
{
    public ZTetromino()
    {
        int[][] shape = { {1, 1, 0}, {0, 1, 1} };
        this.shape = shape;

        this.colour = new Color(255, 0, 0);

        this.height = this.shape.length;
        this.width = this.shape[0].length;
        this.x = (10 - this.width)/2;
        this.y = 0-this.height;

        this.name = 'Z';
    }
}
