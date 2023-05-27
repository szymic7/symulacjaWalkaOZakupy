import java.util.ArrayList;
import java.util.Random;

class Shelf
{
    private ArrayList<Product> products;
    private int NumberOfPromotional;

    public Shelf(int NumberOfPromotional)
    {
        this.NumberOfPromotional = NumberOfPromotional;
        products = new ArrayList<>();
        Random random = new Random();

        for (int i=0; i<10; i++)
        {
            boolean isPromotional = false;
            products.add(new Product(isPromotional));
        }

        for(int i=0; i<NumberOfPromotional; i++)
        {
            int a = random.nextInt(10);
            boolean isPromotional = true;
            products.set(a, new Product(isPromotional));
        }
    }

    public ArrayList<Product> getProducts()
    {
        return products;
    }

}

