import java.util.Random;

public class ChildClient extends Client
{
    private static final double ChanceOfGetting = 50;
    private static final int speed = 100;

    public ChildClient(int x, int y)
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
