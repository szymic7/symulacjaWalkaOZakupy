import java.util.Random;
import java.util.ArrayList;

public abstract class Client implements IClient
{
    private int x;
    private int y;
    private int ChanceOfGetting;
    private int speed;

    public Client(int x, int y, int ChanceOfGetting, int speed)
    {
        this.x = x;
        this.y = y;
        this.ChanceOfGetting = ChanceOfGetting;
        this.speed = speed;
    }

    public void move()
    {
        Random random = new Random();
        int newX;
        int newY;
        if(random.nextInt(100)<this.speed)
        {   // zmienna speed warunkuje prawdopodobienstwo wykonania ruchu
            do {
                newX = random.nextInt(3)-1;
                newY = random.nextInt(3)-1;
            } while ( (this.x + newX) < 0 || (this.x + newX) > 19 || (this.y + newY) < 0 || (this.y + newY) > 19
                    || ( ( (this.y + newY) >= 5 && (this.y + newY) <= 14 ) && (this.x + newX == 3 || this.x + newX == 4
                    || this.x + newX == 9 || this.x + newX == 10 || this.x + newX == 15 || this.x + newX == 16) ) );
            this.x = this.x + newX;
            this.y = this.y + newY;
            int directionX = newX;
            int directionY = newY;
        }
    }

    public int getChanceOfGetting()
    {
        return this.ChanceOfGetting;
    }

    public int TryToGet(ArrayList<Product> products)
    {
        int index = -1;
        for(int i=0; i<60; i++)
        {
            if(this.x == products.get(i).getX() && this.y == products.get(i).getY())
            {
                if(products.get(i).isPromotional()==true)
                    index = i;
            }
        }
        return index;
    }

    public boolean TryToBuy(Product product)
    {
        Random random = new Random();
        return random.nextInt(100) < this.getChanceOfGetting();
    }

    public int getXLocation()
    {
        return this.x;
    }

    public int getYLocation(){
        return this.y;
    }

}

interface IClient
{
    void move();
    int TryToGet(ArrayList<Product> products);
}




