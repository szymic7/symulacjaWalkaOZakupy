import java.util.Random;

public class AdultClient extends Client
{
    private static final double ChanceOfGetting = 100;
    private static final int speed = 75;

    public AdultClient(int x, int y)
    {
        super(x, y);
    }

    public boolean TryToGet()
    {
        Random random = new Random();
        return random.nextDouble() <= ChanceOfGetting;
    }
}





