import java.util.ArrayList;
import java.util.Random;

public class Simulation
{
    private Shop shop;
    private ArrayList<Client> clients;
    public Simulation(int numberOfPromotional){
        this.shop = new Shop(numberOfPromotional);
        this.clients = new ArrayList<Client>();
    }
    public void runSimulation(){
        Random random = new Random();
        while(!clients.isEmpty()){
            // tu bedzie cala iteracja
        }
    }
    public static void main(String[] args)
    {
        // np mozna zrobic tak, ze w argumentach konstruktora obiektu Simulation, beda wymagane argumenty
        // ktore beda argumentami konstruktora Shop (np. liczba klientow, liczba produktow promocyjnych)
        Simulation simulation = new Simulation(10);
        simulation.runSimulation();
    }

}




