import java.util.ArrayList;

public class Simulation
{
    private ArrayList<Client> clients;

    public Simulation()
    {
        ArrayList clients = new ArrayList<>();
        clients.add(new ChildClient());
        clients.add(new AdultClient());
        clients.add(new ElderlyClient());
    }

    
    public static void main(String[] args)
    {
        Simulation simulation = new Simulation();
    }

}




