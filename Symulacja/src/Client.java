public abstract class Client
{
    private int x;
    private int y;
    private int speed;
    private int ChanceOfGetting;

    public Client(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract void move();
    public abstract void TryToGet();


}
