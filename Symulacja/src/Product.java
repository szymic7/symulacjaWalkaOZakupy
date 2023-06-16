class Product
{
    private int x;
    private int y;
    private boolean promotional;
    private boolean sold;

    public Product(int x, int y, boolean promotional)
    {
        this.x = x;
        this.y = y;
        this.promotional = promotional;
        this.sold = false;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean getPromotional()
    {
        return this.promotional;
    }
    public boolean getSold(){
        return this.sold;
    }
    public void isSold(){
        this.sold = true;
    }

}
