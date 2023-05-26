public abstract class Client
{
    private int x;
    private int y;

    public Client(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public abstract void move();
    public abstract boolean TryToGet(Product product);
    public int getXLocation(){
        return this.x;
    }
    public int getYLocation(){
        return this.y;
    }

}





