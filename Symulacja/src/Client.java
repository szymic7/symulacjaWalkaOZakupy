import java.util.Random;
import java.util.ArrayList;

public abstract class Client implements IClient
{
    private int x;
    private int y;
    private int chanceOfGetting;
    private int speed;

    public Client(int x, int y, int chanceOfGetting, int speed)
    {
        this.x = x;
        this.y = y;
        this.chanceOfGetting = chanceOfGetting;
        this.speed = speed;
    }

    public void move()
    {
        Random random = new Random();
        int newX;
        int newY;
        if(random.nextInt(100)<this.speed) // zmienna speed warunkuje prawdopodobienstwo wykonania ruchu (wart. zmiennej speed = % szans na ruch)
        {
            do {
                newX = random.nextInt(3)-1;
                newY = random.nextInt(3)-1;
            } while ( (this.x + newX) < 0 || (this.x + newX) > 19 || (this.y + newY) < 0 || (this.y + newY) > 19
                    || ( ( (this.y + newY) >= 5 && (this.y + newY) <= 14 ) && (this.x + newX == 3 || this.x + newX == 4
                    || this.x + newX == 9 || this.x + newX == 10 || this.x + newX == 15 || this.x + newX == 16) ) ||
                    (newX==0 && newY==0) );
            this.x = this.x + newX;
            this.y = this.y + newY;
        }
    }

    public int getXLocation()
    {
        return this.x;
    }
    public int getYLocation(){
        return this.y;
    }

    public int tryToGet(ArrayList<Product> products)
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
        // metoda zwraca indeks produktu, ktory klient moze zakupic, stojac na danym polu
        // jesli nie ma takiego produktu - zwraca -1
    }

    public boolean tryToBuy(Product product)
    {
        Random random = new Random();
        if(random.nextInt(100) < this.chanceOfGetting){ // zmienna chanceOfGetting warunkuje prawdopodobienstwo podniesienia przedmiotu
            product.isSold();
            return true;
        } else {
            return false;
        }
    }

}

interface IClient
{
    void move();
    int tryToGet(ArrayList<Product> products);
    boolean tryToBuy(Product product);
}
