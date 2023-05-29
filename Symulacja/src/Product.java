class Product
{
    private int x;
    private int y;
    private boolean isPromotional;

    public Product(int x, int y, boolean isPromotional)
    {
        this.x = x;
        this.y = y;
        this.isPromotional = isPromotional;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean isPromotional()
    {
        return isPromotional;
    }
    public void isSold(){
        this.isPromotional = false;
    }
}
