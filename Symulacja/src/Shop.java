import java.util.Random;
import java.util.ArrayList;
import java.util.Map;

public class Shop
{
    private static final int MapSize = 10;
    private static final int NumberOfPromotional = 5;
    // zrobilbym w sumie te 2 rzeczy jako mozliwe do wpisywania, jako argumenty konstruktora

    private ArrayList<Client> clients;
    private Map<Client, Integer> clientsPositions; // opcjonalnie
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

    public Client getClient(int indexOfClient){
        return clients.get(indexOfClient);
    }
    public int getClientXLocation(Client client){
        return client.getXLocation();
    }
    public int getClientYLocation(Client client){
        return client.getYLocation();
    }
    // musimy ustalic czy location podajemy jako jeden int, czy jako x i y i zmienić najwyzej


}
