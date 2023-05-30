import java.util.ArrayList;
import java.util.Random;

public class Simulation
{
    private Shop shop;
    public Simulation(int numberOfPromotional){
        this.shop = new Shop(numberOfPromotional);
    }
    public void runSimulation(){
        Random random = new Random();
        //while(!shop.getClients().isEmpty()){
        for(int i=0; i<50; i++) {
            for(int j = 0; j < shop.getClients().size(); j++) {
                shop.getClient(j).move();
                System.out.println("Klient(" + j + "): x = " + shop.getClient(j).getXLocation() + ", y = " + shop.getClient(j).getYLocation());
                if ((shop.getClient(j).TryToGet(shop.getProducts())) > -1) {
                    if (shop.getClient(j).TryToBuy(shop.getProducts().get(shop.getClient(j).TryToGet(shop.getProducts())))) {
                        System.out.println("Klient(" + j + ") zakupil produkt.");
                    } else {
                        System.out.println("Klientowi(" + j + ") nie udalo sie zakupic produktu.");
                    }
                }
                shop.clientLeaves(shop.getClient(j));
            }
        }
    }
    public static void main(String[] args)
    {
        Simulation simulation = new Simulation(60);
        simulation.runSimulation();
    }

}


