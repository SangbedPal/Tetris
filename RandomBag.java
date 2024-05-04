import java.util.LinkedList;
import java.util.Collections;
import java.util.Random;

public class RandomBag
{
    LinkedList<Tetromino> bag;

    public void fill()
    {
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i<7; i++)
        {
            list.add(i);
        }
    
        Collections.shuffle(list);

        bag = new LinkedList<>(); 
        
        for(int i=0; i<7; i++)
        {
            int index = list.get(i);

            switch(index)
            {
                case 0: bag.add( new ITetromino() );
                    break;
                case 1: bag.add( new JTetromino() );
                    break;
                case 2: bag.add( new LTetromino() );
                    break;
                case 3: bag.add( new OTetromino() );
                    break;
                case 4: bag.add( new STetromino() );
                    break;
                case 5: bag.add( new TTetromino() );
                    break;
                case 6: bag.add( new ZTetromino() );
                    break;
            }
        }
    }

    public Tetromino getTetromino()
    {
        Random random = new Random();

        int index = random.nextInt(bag.size());

        Tetromino tetromino = bag.get(index);
        bag.remove(index);

        return tetromino;
    }
}
    
