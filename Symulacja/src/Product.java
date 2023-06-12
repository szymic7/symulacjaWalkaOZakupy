class Product
{
    private int x;
    private int y;
    private boolean isPromotional;
    private boolean isSold;

    public Product(int x, int y, boolean isPromotional)
    {
        this.x = x;
        this.y = y;
        this.isPromotional = isPromotional;
        this.isSold = false;
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
    /*public void isSold(){
        this.isPromotional = false;
    }*/
    public void isSold(){
        this.isSold = true;
    }
    public boolean ifSold(){
        return this.isSold;
    }
}
