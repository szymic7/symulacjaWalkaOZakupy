import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Simulation
{
    private Shop shop;
    private HashMap<Client, Integer> clientsIndexes;

    public Simulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.shop = new Shop(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        this.clientsIndexes = new HashMap<>();

        for(int i=0; i<shop.getClients().size(); i++)
        {
            clientsIndexes.put(shop.getClient(i), i);
        }
    }

    public void runSimulation(int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        Random random = new Random();

        /* test listy produktów promocyjnych
        for(Product product : shop.getProducts())
        {
            System.out.println(product.isPromotional());
        }
        */

        while (!shop.getClients().isEmpty())
        {
            for (int i=0; i<shop.getClients().size(); i++)
            {
                // interakcje miedzy klientami
                for(int j=0; j<shop.getClients().size(); j++)
                {
                    if((shop.getClient(i).getXLocation() == shop.getClient(j).getXLocation()) &&
                            (shop.getClient(i).getYLocation() == shop.getClient(j).getYLocation()))
                    {
                        if((clientsIndexes.get(shop.getClient(i))<numberOfChild && clientsIndexes.get(shop.getClient(j))>=numberOfChild) ||
                                (clientsIndexes.get(shop.getClient(i))<(numberOfAdult+numberOfChild) && clientsIndexes.get(shop.getClient(j))>=(numberOfAdult+numberOfChild)))
                        {
                            System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") przepuscil " +
                                    "klienta(" + clientsIndexes.get(shop.getClient(j)) + ").");
                            i++;
                        }
                    }
                }

                // ruch klientów
                shop.getClient(i).move();
                System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") x = " + shop.getClient(i).getXLocation() + ", y = " + shop.getClient(i).getYLocation());

                if((shop.getClient(i).TryToGet(shop.getProducts())) > -1)
                {
                    if(shop.getClient(i).TryToBuy(shop.getProducts().get(shop.getClient(i).TryToGet(shop.getProducts()))))
                    {
                        System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") zakupil produkt promocyjny.");
                    }
                    else
                    {
                        System.out.println("Klientowi(" + clientsIndexes.get(shop.getClient(i)) + ")nie udalo sie zakupic produktu promocyjnego.");
                    }
                }
                shop.clientLeaves(shop.getClient(i));
            }
        }

    }

    public static void main(String[] args)
    {
        int numberOfPromotional = 60;
        int numberOfChild = 3;
        int numberOfAdult = 3;
        int numberOfElderly = 3;

        Simulation simulation = new Simulation(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        simulation.runSimulation(numberOfChild, numberOfAdult, numberOfElderly);
    }

}

