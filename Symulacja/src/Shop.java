import java.util.Random;
import java.util.ArrayList;

public class Shop
{
    private static final int MapSize = 10;
    private static final int NumberOfPromotional = 5;

    private ArrayList<Client> clients;
    private ArrayList<String> products;
    private Shelf shelf;
    private CashRegister cashRegister;

    public Shop()
    {
        clients = new ArrayList<>();
        Random random = new Random();

        for(int i=0; i<2; i++)
        {
            int x = random.nextInt(MapSize+1);
            int y = random.nextInt(MapSize+1);

            clients.add(new ChildClient(x, y));
        }

        for(int i=0; i<2; i++)
        {
            int x = random.nextInt(MapSize+1);
            int y = random.nextInt(MapSize+1);

            clients.add(new AdultClient(x, y));
        }

        for(int i=0; i<2; i++)
        {
            int x = random.nextInt(MapSize+1);
            int y = random.nextInt(MapSize+1);

            clients.add(new ElderlyClient(x, y));
        }

        shelf = new Shelf(NumberOfPromotional);
        cashRegister = new CashRegister();
    }

    public void RunSimulation()
    {
        Random random = new Random();

    }


}


