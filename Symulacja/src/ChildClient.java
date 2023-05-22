import java.util.Random;

public class ChildClient extends Client
{
    private static final double ChanceOfGetting = 50;
    private static final int speed = 100;

    public ChildClient(int x, int y)
    {
        super(x, y);
    }

    public boolean TryToGet()
    {
        Random random = new Random();
        return random.nextInt() <= ChanceOfGetting;
    }

}




