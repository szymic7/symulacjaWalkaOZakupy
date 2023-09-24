/**
 * Klasa reprezentująca produkt
 */
public class Product
{
    private int x;
    private int y;
    private boolean promotional;
    private boolean sold;

    /**
     * Konstruktor obiektu <code>Product</code>
     * @param x wspolrzedna x produktu (a dokładniej współrzędna x pola, z którego można wejść w interakcję z porduktem)
     * @param y wspolrzedna y produktu
     * @param promotional czy dany produkt jest produktem promocyjnym?
     */
    public Product(int x, int y, boolean promotional)
    {
        this.x = x;
        this.y = y;
        this.promotional = promotional;
        this.sold = false;
    }

    /**
     * Metoda zwracająca współrzędną x produktu
     * @return Współrzędna x produktu
     */
    public int getX(){
        return this.x;
    }

    /**
     * Metoda zwracająca współrzędną y produktu
     * @return Współrzędna y produktu
     */
    public int getY(){
        return this.y;
    }

    /**
     * Metoda zwraca zmienną typu <code>boolean</code> informującą, czy dany produkt jest produktem promocyjnym
     * @return Zmienna <code>promotional</code> mówiąca, czy dany produkt jest produktem promocyjnym
     */
    public boolean getPromotional()
    {
        return this.promotional;
    }

    /**
     * Metoda zwraca zmienną typu <code>boolean</code> informującą, czy dany produkt został sprzedany
     * @return Zmienna <code>sold</code> mówiąca, czy dany produkt jest sprzedany
     */
    public boolean getSold(){
        return this.sold;
    }

    /**
     * Metoda mienia wartość zmiennej <code>sold</code> produktu na <code>true</code> - oznacza produkt jako sprzedany
     */
    public void isSold(){
        this.sold = true;
    }

}
