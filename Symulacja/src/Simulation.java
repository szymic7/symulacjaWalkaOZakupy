import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation
{
    private Shop shop;
    private HashMap<Client, Integer> clientsIndexes;
    private SwingWindow window;

    private static int boughtByChild = 0;
    private static int boughtByAdult = 0;
    private static int boughtByElderly = 0;

    public Simulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.shop = new Shop(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        this.clientsIndexes = new HashMap<>();
        this.window = new SwingWindow(shop, numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);

        for(int i=0; i<shop.getClients().size(); i++)
        {
            clientsIndexes.put(shop.getClient(i), i);
        }
    }

    public void runSimulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        // Alternatywna pętla z delayem, ale wtedy nie dziala while loop
        //ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        //executorService.scheduleAtFixedRate(() -> {

        try {
            while (!shop.getClients().isEmpty() && (numberOfPromotional-boughtByChild-boughtByAdult-boughtByElderly)!=0) {
                Thread.sleep(20);
                window.show();
                for (int i = 0; i < shop.getClients().size(); i++) {
                    // interakcje miedzy klientami
                    int interactions = 0;
                    for (int j = 0; j < shop.getClients().size(); j++) {
                        if ((shop.getClient(i).getXLocation() == shop.getClient(j).getXLocation()) &&
                                (shop.getClient(i).getYLocation() == shop.getClient(j).getYLocation()))
                        // pierwszy warunek - dwoch klientow na tym samym polu
                        {
                            if ((shop.getClient(i) instanceof ChildClient && (shop.getClient(j) instanceof AdultClient || shop.getClient(j) instanceof ElderlyClient))
                                    || (shop.getClient(i) instanceof AdultClient && shop.getClient(j) instanceof ElderlyClient))
                            // drugi warunek - (client i to ChildClient a client j to AdultClient albo ElderlyClient) lub
                            // (klient i to AdultClient a client j to ElderlyClient)
                            {
                                System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") przepuscil " +
                                        "klienta(" + clientsIndexes.get(shop.getClient(j)) + ").");
                                interactions++;
                            }
                        }
                    }

                    // jesli klient nie mial zadnych interakcji (nikogo nie przepuszczal) to przechodzi do ruchu
                    if (interactions == 0) {
                        shop.getClient(i).move(); // ruch klienta
                        System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") x = " + shop.getClient(i).getXLocation() + ", y = " + shop.getClient(i).getYLocation());

                        // sprawdzenie, czy stojac na danym polu klient moze wejsc w interakcje z produktem promocyjnym
                        if ((shop.getClient(i).tryToGet(shop.getProducts())) > -1) {
                            // sprawdzenie, czy klient jest w stanie "dosiegnac"/"dostrzec" produkt i podniesc go
                            if (shop.getClient(i).tryToBuy(shop.getProducts().get(shop.getClient(i).tryToGet(shop.getProducts())))) {
                                System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") zakupil produkt promocyjny.");
                                if (shop.getClient(i) instanceof ChildClient) {
                                    boughtByChild++;
                                } else if (shop.getClient(i) instanceof AdultClient) {
                                    boughtByAdult++;
                                } else if (shop.getClient(i) instanceof ElderlyClient) {
                                    boughtByElderly++;
                                }
                            } else {
                                System.out.println("Klientowi(" + clientsIndexes.get(shop.getClient(i)) + ") nie udalo sie zakupic produktu promocyjnego.");
                            }
                        }
                        // sprawdzenie, czy klient trafil do kasy
                        shop.clientLeaves(shop.getClient(i));
                    }
                }
                // Wyswietlanie mapy
                //window.revalidate();
                window.getStorePanel().repaint();
            }
        } catch (InterruptedException ex) {
            System.out.println("Error.");
        }

        // ALternatywna petla z delayem, ale nie dziala wtedy while
        //}, 0, 500, TimeUnit.MILLISECONDS);

        // zliczanie rezultatow symulacji
        System.out.println("\nWYNIKI SYMULACJI\n");
        System.out.println("Liczba klientow typu Child: " + numberOfChild);
        System.out.println("Liczba klientow typu Adult: " + numberOfAdult);
        System.out.println("Liczba klientow typu Elderly: " + numberOfElderly);
        System.out.println("Liczba produktow promocyjnych na początku symulacji: " + numberOfPromotional);

        // zliczenie, ile zostalo promocyjnych produktow
        /*int promotionalLeft = 0;
        for (Product product : shop.getProducts()) {
            if (product.isPromotional()) {
                promotionalLeft++;
            }
        }*/

        int productsSold = 0;
        for (Product product : shop.getProducts()) {
            if (product.ifSold()) {
                productsSold++;
            }
        }

        System.out.println("Liczba sprzedanych produktow promocyjnych: " + productsSold/*(numberOfPromotional - promotionalLeft)*/);
        System.out.println("Produkty zakupione przez klientow typu Child: " + boughtByChild);
        System.out.println("Produkty zakupione przez klientow typu Adult: " + boughtByAdult);
        System.out.println("Produkty zakupione przez klientow typu Elderly: " + boughtByElderly);

        // zapis wynikow symulacji do pliku WynikiSymulacji.txt
        try {
            PrintWriter zapis = new PrintWriter("WynikiSymulacji.txt");
            zapis.println("----- WYNIKI SYMULACJI -----\n");
            zapis.println("Klienci bioracy udzial w symulacji:");
            zapis.println("Liczba klientow typu Child: " + numberOfChild);
            zapis.println("Liczba klientow typu Adult: " + numberOfAdult);
            zapis.println("Liczba klientow typu Elderly: " + numberOfElderly + "\n");
            zapis.println("Liczba produktow promocyjnych na początku symulacji: " + numberOfPromotional);
            zapis.println("Liczba sprzedanych produktow promocyjnych: " + productsSold /*(numberOfPromotional - promotionalLeft)*/);
            zapis.println("Produkty zakupione przez klientow typu Child: " + boughtByChild);
            zapis.println("Produkty zakupione przez klientow typu Adult: " + boughtByAdult);
            zapis.println("Produkty zakupione przez klientow typu Elderly: " + boughtByElderly);
            zapis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }


    public static void main(String[] args)
    {
        int numberOfPromotional = 40;
        int numberOfChild = 1;
        int numberOfAdult = 1;
        int numberOfElderly = 1;

        Simulation simulation = new Simulation(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        simulation.runSimulation(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
    }

}



