import java.util.Random;
import java.util.ArrayList;

public class Shop
{
    private static final int MapSize = 20;
    private ArrayList<Client> clients;
    private ArrayList<Product> products;

    public Shop(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.clients = new ArrayList<>();
        this.products = new ArrayList<Product>();

        // zmienne potrzebne do inicjalizowania
        int allClients = numberOfChild + numberOfAdult + numberOfElderly;
        Integer[][] respCoordinates = new Integer[allClients][2];
        ArrayList<Integer> randomPromotional = new ArrayList<>();

        Random random = new Random();

        // tworzenie klientów typu ChildClient
        int i=0;
        while(i<numberOfChild)
        {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(5);

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
        }

        // tworzenie klientów typu AdultClient
        while(i<(numberOfAdult+numberOfChild))
        {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(5);
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
        }

        // tworzenie klientów typu ElderlyClient
        while(i<(allClients))
        {
            int x = random.nextInt(MapSize);
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
        }

        // tworzenie pdouktów niepromocyjnych
        for(int y1=5; y1<15; y1++)
        {
            products.add(new Product(2, y1, false));
            products.add(new Product(5, y1, false));
            products.add(new Product(8, y1, false));
            products.add(new Product(11, y1, false));
            products.add(new Product(14, y1, false));
            products.add(new Product(17, y1, false));
        }

        // zamiana lospwych produktów niepromocyjnych na promocyjne
        i=0;
        while(i<numberOfPromotional)
        {
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

        }
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

    public void clientLeaves(Client client)
    {
        if(client.getXLocation()>=18 && client.getYLocation()>=18)
        {
            clients.remove(client);
            System.out.println("Klient opuscil sklep.");
        }
    }

}
