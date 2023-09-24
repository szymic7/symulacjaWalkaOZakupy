/**
 * Klasa reprezentująca dorosłego klienta, dziedzicząca po klasie <code>Client</code>
 */
public class AdultClient extends Client
{
    /**
     * Konstruktor obiektu typu <code>AdultClient</code>, wywołujący konstruktor klasy <code>Client</code>
     * @param x współrzędna x klienta
     * @param y współrzędna y klienta
     */
    public AdultClient(int x, int y)
    {
        super(x, y, 100, 85);
    }
}
