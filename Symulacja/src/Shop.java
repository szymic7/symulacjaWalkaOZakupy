import java.util.Random;
import java.util.ArrayList;

public class Shop
{
    private static final int MapSize = 20;
    private int numberOfPromotional;
    private ArrayList<Client> clients;
    private ArrayList<Product> products;

    public Shop(int numberOfPromotional)
    {
        clients = new ArrayList<>();
        Integer[][] respCoordinates = new Integer[3][2];

        // create & spawn ChildClient
        Random random = new Random();
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


        this.numberOfPromotional = numberOfPromotional;
        products = new ArrayList<Product>();

        // create nonPromotionalProducts
        for(int y1=5; y1<15; y1++){
            products.add(new Product(2, y1, false));
            products.add(new Product(5, y1, false));
            products.add(new Product(8, y1, false));
            products.add(new Product(11, y1, false));
            products.add(new Product(14, y1, false));
            products.add(new Product(17, y1, false));
        }

        // swap random products for PromotionalProducts
        for(int i=0; i<numberOfPromotional; i++)
        {
            random = new Random();
            int a = random.nextInt(60);
            int productX = products.get(a).getX();
            int productY = products.get(a).getY();
            products.set(a, new Product(productX, productY, true));
        }
    }


    public Client getClient(int indexOfClient){
        return clients.get(indexOfClient);
    }
    public ArrayList<Client> getClients(){
        return clients;
    }
    public ArrayList<Product> getProducts(){
        return products;
    }
    public int getClientXLocation(Client client){
        return client.getXLocation();
    }
    public int getClientYLocation(Client client){
        return client.getYLocation();
    }
    // musimy ustalic czy location podajemy jako jeden int, czy jako x i y i zmienić najwyzej


}







