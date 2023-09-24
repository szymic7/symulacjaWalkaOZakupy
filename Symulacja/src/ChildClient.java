/**
 * Klasa reprezentująca klienta dziecko, dziedzicząca po klasie <code>Client</code>
 */
public class ChildClient extends Client
{
    /**
     * Konstruktor obiektu typu <code>ChildClient</code>, wywołujący konstruktor klasy <code>Client</code>
     * @param x współrzędna x klienta
     * @param y współrzędna y klienta
     */
    public ChildClient(int x, int y)
    {
        super(x, y, 50, 100);
    }

}
