import java.util.Random;

public abstract class Client
{
    private int x;
    private int y;
    private static int ChanceOfGetting;
    private static int speed;

    public Client(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void move(){
        Random random = new Random();
        if(random.nextInt(100)<=speed){
            int newX = random.nextInt(3)-1;
            int newY = random.nextInt(3)-1;
            this.x = this.x + newX;
            this.y = this.y + newY;
        }
    };
    public abstract boolean TryToGet(Product product);
    public int getXLocation(){
        return this.x;
    }
    public int getYLocation(){
        return this.y;
    }

}


