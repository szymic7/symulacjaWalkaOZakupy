import java.util.Random;

public class AdultClient extends Client
{
    private static final double ChanceOfGetting = 100;
    private static final int speed = 75;

    public AdultClient(int x, int y)
    {
        super(x, y);
    }

    public boolean TryToGet(Product product)
    {
        if(product.isPromotional()) {
            Random random = new Random();
            // return random.nextDouble(100) <= ChanceOfGetting;
            return true;
            // zawsze bedzie to prawda dla AdultClient, wiec mozemy po prostu zwrocic true
        } else {
            return false;
        }
    }

    public void move()
    {

    }

}


