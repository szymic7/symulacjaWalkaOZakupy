import java.util.Random;
import java.util.ArrayList;

public class Shop
{
    private static final int MapSize = 20;
    private int numberOfPromotional;
    private ArrayList<Client> clients;
    private ArrayList<Product> products;
    private Shelf shelf;
    private CashRegister cashRegister;

    public Shop(int numberOfPromotional)
    {
        this.numberOfPromotional = numberOfPromotional;

        clients = new ArrayList<>();
        Random random = new Random();

        Integer[][] respCoordinates = new Integer[3][2];

        // create & spawn ChildClient
        int x = random.nextInt(MapSize);
        int y = random.nextInt(5);
        respCoordinates[0][0] = x;
        respCoordinates[0][1] = y;
        clients.add(new ChildClient(x, y));

        // create & spawn AdultClient
        do {
            x = random.nextInt(MapSize);
            y = random.nextInt(5);
            if (x != respCoordinates[0][0] || y != respCoordinates[0][1]) {
                clients.add(new AdultClient(x, y));
                respCoordinates[1][0] = x;
                respCoordinates[1][1] = y;
            }
        } while(clients.size()<2);

        // create & spawn ElderlyClient
        do {
            x = random.nextInt(MapSize);
            y = random.nextInt(5);
            if ((x != respCoordinates[0][0] || y != respCoordinates[0][1]) && (x != respCoordinates[1][0] || y != respCoordinates[1][1])) {
                clients.add(new ElderlyClient(x, y));
                respCoordinates[2][0] = x;
                respCoordinates[2][1] = y;
            }
        } while(clients.size()<3);

        shelf = new Shelf(numberOfPromotional);
        cashRegister = new CashRegister();

        // create nonPromotionalProducts
        for(int i=0; i<60; i++){
            products.add(new Product(false));
        }

        // swap radnom products for PromotionalProducts
        for(int i=0; i<numberOfPromotional; i++)
        {
            int a = random.nextInt(60);
            products.set(a, new Product(true));
        }
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

// {(0,0), (1,0), (2,0), (3,0), (4,0)}

