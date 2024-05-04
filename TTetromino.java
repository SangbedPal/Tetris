import java.awt.Color;

public class TTetromino extends Tetromino
{
    public TTetromino()
    {
        int[][] shape = { {0, 1, 0}, {1, 1, 1} };
        this.shape = shape;

        this.colour = new Color(128, 0, 128);

        this.height = this.shape.length;
        this.width = this.shape[0].length;
        this.x = (10 - this.width)/2;
        this.y = 0-this.height;

        this.name = 'T';
    }
}
