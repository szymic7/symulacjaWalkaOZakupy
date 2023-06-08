import java.util.Random;
import java.util.HashMap;

public class Simulation
{
    private Shop shop;
    private HashMap<Client, Integer> clientsIndexes;
    private static int boughtByChild = 0;
    private static int boughtByAdult = 0;
    private static int boughtByElderly = 0;

    public Simulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.shop = new Shop(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        this.clientsIndexes = new HashMap<>();

        for(int i=0; i<shop.getClients().size(); i++)
        {
            clientsIndexes.put(shop.getClient(i), i);
        }
    }

    public void runSimulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        Random random = new Random();

        while (!shop.getClients().isEmpty())
        {
            for (int i=0; i<shop.getClients().size(); i++)
            {

                // interakcje miedzy klientami
                int interactions=0;
                for(int j=0; j<shop.getClients().size(); j++)
                {
                    if((shop.getClient(i).getXLocation() == shop.getClient(j).getXLocation()) &&
                            (shop.getClient(i).getYLocation() == shop.getClient(j).getYLocation()))
                    // pierwszy warunek - dwoch klientow na tym samym polu
                    {
                        if((clientsIndexes.get(shop.getClient(i))<numberOfChild && clientsIndexes.get(shop.getClient(j))>=numberOfChild) ||
                                (clientsIndexes.get(shop.getClient(i))<(numberOfAdult+numberOfChild) && clientsIndexes.get(shop.getClient(j))>=(numberOfAdult+numberOfChild)))
                        // drugi warunek - (client i to ChildClient a client j to AdultClient albo ElderlyClient) lub
                        // (klient i to ChildClient albo AdultClient a client j to ElderlyClient)
                        {
                            System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") przepuscil " +
                                    "klienta(" + clientsIndexes.get(shop.getClient(j)) + ").");
                            interactions++;
                        }
                    }
                }

                if(interactions==0)
                {
                    shop.getClient(i).move(); // ruch klienta
                    System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") x = " + shop.getClient(i).getXLocation() + ", y = " + shop.getClient(i).getYLocation());

                    // sprawdzenie, czy stojac na danym polu klient moze wejsc w interakcje z produktem promocyjnym
                    if ((shop.getClient(i).tryToGet(shop.getProducts())) > -1)
                    {
                        // sprawdzenie, czy klient jest w stanie "dosiegnac"/"dostrzec" produkt i podniesc go
                        if (shop.getClient(i).tryToBuy(shop.getProducts().get(shop.getClient(i).tryToGet(shop.getProducts()))))
                        {
                            System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") zakupil produkt promocyjny.");
                            if (clientsIndexes.get(shop.getClient(i)) < numberOfChild)
                            {
                                boughtByChild++;
                            }
                            else if (clientsIndexes.get(shop.getClient(i)) >= numberOfChild && clientsIndexes.get(shop.getClient(i)) < (numberOfChild + numberOfAdult))
                            {
                                boughtByAdult++;
                            }
                            else if (clientsIndexes.get(shop.getClient(i)) >= (numberOfChild + numberOfAdult))
                            {
                                boughtByElderly++;
                            }
                        }
                        else
                        {
                            System.out.println("Klientowi(" + clientsIndexes.get(shop.getClient(i)) + ") nie udalo sie zakupic produktu promocyjnego.");
                        }
                    }

                    // sprawdzenie, czy klient trafil do kasy
                    shop.clientLeaves(shop.getClient(i));
                }
            }
        }

        // zliczanie rezultatow symulacji
        System.out.println("\n\nWYNIKI SYMULACJI\n");
        System.out.println("Liczba klientow typu Child: " + numberOfChild);
        System.out.println("Liczba klientow typu Adult: " + numberOfAdult);
        System.out.println("Liczba klientow typu Elderly: " + numberOfElderly);
        System.out.println("Liczba produktow promocyjnych na początku symulacji: " + numberOfPromotional);

        // zliczenie, ile zostalo promocyjnych produktow
        int promotionalLeft = 0;
        for(Product product : shop.getProducts())
        {
            if(product.isPromotional())
            {
                promotionalLeft++;
            }
        }

        System.out.println("Liczba sprzedanych produktow promocyjnych: " + (numberOfPromotional-promotionalLeft));
        System.out.println("Produkty zakupione przez klientow typu Child: " + boughtByChild);
        System.out.println("Produkty zakupione przez klientow typu Adult: " + boughtByAdult);
        System.out.println("Produkty zakupione przez klientow typu Elderly: " + boughtByElderly);

    }

    public static void main(String[] args)
    {
        int numberOfPromotional = 45;
        int numberOfChild = 3;
        int numberOfAdult = 3;
        int numberOfElderly = 3;

        Simulation simulation = new Simulation(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        simulation.runSimulation(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);

        SwingWindow window = new SwingWindow(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        window.show();
    }

}




