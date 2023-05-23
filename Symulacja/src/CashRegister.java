class CashRegister
{
    private boolean allPromotionalSold;

    public CashRegister()
    {
        allPromotionalSold = false;
    }

    public boolean arePromotionalSold()
    {
        return allPromotionalSold;
    }

    public void sellProduct(Product product)
    {
        if (product.isPromotional())
        {
            allPromotionalSold = true;
        }
    }

}
