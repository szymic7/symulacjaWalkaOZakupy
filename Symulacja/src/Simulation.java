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
          
        for(int i=0; i<100; i++) {
            shop.getClient(0).move();
            System.out.println("x = " + shop.getClient(0).getXLocation() + ", y = " + shop.getClient(0).getYLocation());
            if ((shop.getClient(0).TryToGet(shop.getProducts())) > -1) {
                if (shop.getClient(0).TryToBuy(shop.getProducts().get(shop.getClient(0).TryToGet(shop.getProducts())))) {
                    System.out.println("Zakupiono przedmiot");
                } else {
                    System.out.println("Nie udalo sie zakupic przedmiotu");
                }
            }
        }
            //shop.getClient(1).move();
            //System.out.println(shop.getClient(1).getXLocation());
            //System.out.println(shop.getClient(1).getYLocation());
            //shop.getClient(2).move();
            //System.out.println(shop.getClient(2).getXLocation());
            //System.out.println(shop.getClient(2).getYLocation());
        //}
    }
    public static void main(String[] args)
    {
        // np mozna zrobic tak, ze w argumentach konstruktora obiektu Simulation, beda wymagane argumenty
        // ktore beda argumentami konstruktora Shop (np. liczba klientow, liczba produktow promocyjnych)
        Simulation simulation = new Simulation(60);
        simulation.runSimulation();
    }

}


