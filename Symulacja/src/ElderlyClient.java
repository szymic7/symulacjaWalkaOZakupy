import java.util.Random;

public class ElderlyClient extends Client
{
    private static final double ChanceOfGetting = 70;
    private static final int speed = 50;

    public ElderlyClient(int x, int y)
    {
        super(x, y);
    }

    public boolean TryToGet(Product product)
    {
        if(product.isPromotional()){
            Random random = new Random();
            return random.nextInt(100) <= ChanceOfGetting;
        } else {
            return false;
            // zmien kierunek ruchu, chyba ze to zrobimy w klasie Shop, a ta metoda bedzie tylko zwracac boola
        }
    }

    public void move()
    {

    }

}

