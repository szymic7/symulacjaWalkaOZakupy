/**
 * Klasa reprezentująca klienta seniora, dziedzicząca po klasie <code>Client</code>
 */
public class ElderlyClient extends Client
{
    /**
     * Konstruktor obiektu typu <code>ElderlyClient</code>, wywołujący konstruktor klasy <code>Client</code>
     * @param x współrzędna x klienta
     * @param y współrzędna y klienta
     */
    public ElderlyClient(int x, int y)
    {
        super(x, y, 70, 70);
    }

}
