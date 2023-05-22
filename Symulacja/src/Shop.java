public class Shop
{
    private static final int MapSize = 10;
    private static final int NumberOfProducts = 5;

    private ArrayList<Client> clients;
    private ArrayList<String> products;

    public Shop()
    {
        clients = new ArrayList<>();
        products = new ArrayList<>();

        for (int i=1; i<=NumberOfProducts; i++)
        {
            products.add("Product " + i);
        }

        Random random = new Random();

        for(int i=0; i<2; i++)
        {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(MapSize);

            clients.add(new ChildClient(x, y));
        }

        for(int i=0; i<2; i++)
        {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(MapSize);

            clients.add(new AdultClient(x, y));
        }

        for(int i=0; i<2; i++)
        {
            int x = random.nextInt(MapSize);
            int y = random.nextInt(MapSize);

            clients.add(new ElderlyClient(x, y));
        }

    }

}




