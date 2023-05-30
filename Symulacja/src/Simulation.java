import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Simulation
{
    private Shop shop;
    private HashMap<Client, Integer> clientsIndexes;
    public Simulation(int numberOfPromotional){
        this.shop = new Shop(numberOfPromotional);
        this.clientsIndexes = new HashMap<Client, Integer>();
        for(int i=0; i<shop.getClients().size(); i++){
            clientsIndexes.put(shop.getClient(i), i);
        }
    }
    public void runSimulation(){
        Random random = new Random();
        while(!shop.getClients().isEmpty()){
            for(int i = 0; i < shop.getClients().size(); i++) {

                // interakcje miedzy klientami
                for(int j=0; j<shop.getClients().size(); j++) {
                    if(clientsIndexes.get(shop.getClient(j))!=clientsIndexes.get(shop.getClient(i))) {
                        if ((shop.getClient(i).getXLocation() == shop.getClient(j).getXLocation()) &&
                        (shop.getClient(i).getYLocation() == shop.getClient(j).getYLocation())){
                            if(clientsIndexes.get(shop.getClient(i))<clientsIndexes.get(shop.getClient(j))){
                                System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") przepuscil " +
                                        "klienta(" + clientsIndexes.get(shop.getClient(j)) + ").");
                                i++;
                            }
                        }
                    }
                }

                // ruch klienta
                shop.getClient(i).move();
                System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + "): x = " + shop.getClient(i).getXLocation()
                        + ", y = " + shop.getClient(i).getYLocation());

                // podnoszenie produktow
                if ((shop.getClient(i).TryToGet(shop.getProducts())) > -1) {
                    if (shop.getClient(i).TryToBuy(shop.getProducts().get(shop.getClient(i).TryToGet(shop.getProducts())))) {
                        System.out.println("Klient(" + clientsIndexes.get(shop.getClient(i)) + ") zakupil produkt.");
                    } else {
                        System.out.println("Klientowi(" + clientsIndexes.get(shop.getClient(i)) + ") nie udalo sie zakupic produktu.");
                    }
                }

                // sprawdzenie, czy klient trafil na kase
                shop.clientLeaves(shop.getClient(i));
            }
        }
    }
    public static void main(String[] args)
    {
        Simulation simulation = new Simulation(60);
        simulation.runSimulation();
    }

}


