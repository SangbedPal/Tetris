public class GameThread extends Thread
{
    public GameArea ga;

    public GameThread(GameArea ga)
    {
        this.ga = ga;
    }

    @Override
    public void run()
    {
        while(true)
        {
            ga.spawnTetromino();
            ga.setShadow();

            while(ga.moveTetrominoDown() == true)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            if(ga.isTetrominoOutOfGameArea() == true)
            {
                ga.displayGameOver();
                System.out.println("Game Over");  
                break; 
            }
            else
            {
                ga.moveTetrominoToBackground();
                ga.moveTetrominoToConsole();
                ga.clearRowsAndShiftDown();
            }
        }
    }
}
