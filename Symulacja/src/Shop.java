import java.util.Random;
import java.util.ArrayList;

public class Shop
{
    private static final int MapSize = 20;
    private int numberOfPromotional;
    private ArrayList<Client> clients;
    private ArrayList<Product> products;
    private int numberOfChild;
    private int numberOfAdult;
    private int numberOfElderly;

    public Shop(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.numberOfChild = numberOfChild;
        this.numberOfAdult = numberOfAdult;
        this.numberOfElderly = numberOfElderly;

        int AllClients = this.numberOfAdult + this.numberOfChild + this.numberOfElderly;

        clients = new ArrayList<>();
        Integer[][] respCoordinates = new Integer[AllClients][2];
        ArrayList<Integer> randomPromotional = new ArrayList<>();
        Random random = new Random();

        // create & spawn ChildClient
        int i=0;
        do{
            int x = random.nextInt(MapSize-1);
            int y = random.nextInt(4);

            if(i==0)
            {
                respCoordinates[i][0] = x;
                respCoordinates[i][1] = y;
                clients.add(new ChildClient(x, y));
                i++;
            }
            else
            {
                int check = 0;
                for(int j=0; j<i; j++)
                {
                    if ((x == respCoordinates[j][0] && y == respCoordinates[j][1]))
                    {
                        check++;
                        break;
                    }
                }
                if(check==0)
                {
                    respCoordinates[i][0] = x;
                    respCoordinates[i][1] = y;
                    clients.add(new ChildClient(x, y));
                    i++;
                }
            }
        }while(i<numberOfChild);

        // create & spawn AdultClient
        do{
            int x = random.nextInt(MapSize-1);
            int y = random.nextInt(4);
            int check = 0;

            for(int j=0; j<i; j++)
            {
                if ((x == respCoordinates[j][0] && y == respCoordinates[j][1]))
                {
                    check++;
                    break;
                }
            }
            if(check==0)
            {
                respCoordinates[i][0] = x;
                respCoordinates[i][1] = y;
                clients.add(new AdultClient(x, y));
                i++;
            }

        } while(i<(numberOfAdult+numberOfChild));

        // create & spawn ElderlyClient
        do {
            int x = random.nextInt(MapSize-1);
            int y = random.nextInt(5);
            int check=0;

            for(int j=0; j<i; j++)
            {
                if ((x == respCoordinates[j][0] && y == respCoordinates[j][1]))
                {
                    check++;
                    break;
                }
            }
            if(check==0)
            {
                respCoordinates[i][0] = x;
                respCoordinates[i][1] = y;
                clients.add(new ElderlyClient(x, y));
                i++;
            }
        } while(i<(AllClients));


        this.numberOfPromotional = numberOfPromotional;
        products = new ArrayList<Product>();

        // create nonPromotionalProducts
        for(int y1=5; y1<15; y1++)
        {
            products.add(new Product(2, y1, false));
            products.add(new Product(5, y1, false));
            products.add(new Product(8, y1, false));
            products.add(new Product(11, y1, false));
            products.add(new Product(14, y1, false));
            products.add(new Product(17, y1, false));
        }

        // swap random products for PromotionalProducts
        i=0;
        do{
            random = new Random();
            int a = random.nextInt(60);

            if(i==0)
            {
                int productX = products.get(a).getX();
                int productY = products.get(a).getY();
                products.set(a, new Product(productX, productY, true));
                randomPromotional.add(a);
                i++;
            }
            else
            {
                int check = 0;
                for(int j=0; j<randomPromotional.size(); j++)
                {
                    if(a == randomPromotional.get(j))
                    {
                        check++;
                        break;
                    }
                }
                if(check==0)
                {
                    int productX = products.get(a).getX();
                    int productY = products.get(a).getY();
                    products.set(a, new Product(productX, productY, true));
                    randomPromotional.add(a);
                    i++;
                }
            }

        }while(i<numberOfPromotional);
    }

    public Client getClient(int indexOfClient)
    {
        return clients.get(indexOfClient);
    }

    public ArrayList<Client> getClients()
    {
        return clients;
    }

    public ArrayList<Product> getProducts()
    {
        return products;
    }

    public int getClientXLocation(Client client)
    {
        return client.getXLocation();
    }

    public int getClientYLocation(Client client)
    {
        return client.getYLocation();
    }

    public void clientLeaves(Client client)
    {
        if(client.getXLocation()>=18 && client.getYLocation()>=18)
        {
            clients.remove(client);
            System.out.println("Klient opuscil sklep.");
        }
    }

}



