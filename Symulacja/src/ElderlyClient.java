import java.util.Random;

public class ElderlyClient extends Client
{
    private static final double ChanceOfGetting = 75;
    private static final int speed = 50;

    public ElderlyClient(int x, int y)
    {
        super(x, y);
    }

    public boolean TryToGet()
    {
        Random random = new Random();
        return random.nextDouble() <= ChanceOfGetting;
    }
}




