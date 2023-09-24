import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Klasa odpowiedzialna za przeprowadzanie symulacji
 */
public class Simulation
{
    private Shop shop;
    private HashMap<Client, Integer> clientsIndexes; // aby klienci mieli stale indeksy

    private static int boughtByChild = 0;
    private static int boughtByAdult = 0;
    private static int boughtByElderly = 0;

    private JFrame frame;
    private JSlider promotionalSlider;
    private JSlider childSlider;
    private JSlider adultSlider;
    private JSlider elderlySlider;
    private JLabel promotionalLabel;
    private JLabel childLabel;
    private JLabel adultLabel;
    private JLabel elderlyLabel;
    private JPanel storePanel;
    private JButton start;
    private static final Color dark_green = new Color(0,215,0);
    private static final Color dark_red = new Color(215,0,0);
    private static final Color brown = new Color(150,75,0);

    /**
     * Konstruktor obiektów typu Simulation
     * @param numberOfPromotional liczba produktów promocyjnych występujących w symulacji
     * @param numberOfChild liczba klientów typu <code>ChildClient</code> biorących udział w symulacji
     * @param numberOfAdult liczba klientów typu <code>AdultClient</code> biorących udział w symulacji
     * @param numberOfElderly liczba klientów typu <code>EldelryClient</code> biorących udział w symulacji
     */

    public Simulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.shop = new Shop(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
        this.clientsIndexes = new HashMap<>();
        for(int i=0; i<shop.getClients().size(); i++)
        {
            clientsIndexes.put(shop.getClient(i), i);
        }
        initialize(numberOfPromotional, numberOfChild, numberOfAdult, numberOfElderly);
    }

    /**
     * Metoda zwraca storePanel, czyli mapę, wyświetlaną na GUI
     * @return storePanel - mapa, wyświetlana na GUI
     */
    public JPanel getStorePanel(){
        return storePanel;
    }

    /**
     * Ustawia widoczność GUI na <code>true</code>
     */
    public void show()
    {
        frame.setVisible(true);
    }

    /**
     * Metoda odpowiedzialna za urochomienie symulacji
     * @param numberOfPromotional liczba produktów promocyjnych występujących w symulacji
     * @param numberOfChild liczba klientów typu <code>ChildClient</code> biorących udział w symulacji
     * @param numberOfAdult liczba klientów typu <code>AdultClient</code> biorących udział w symulacji
     * @param numberOfElderly liczba klientów typu <code>EldelryClient</code> biorących udział w symulacji
     */
    public void runSimulation(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        getStorePanel().repaint();
        try {
            while (!shop.getClients().isEmpty() && (numberOfPromotional-boughtByChild-boughtByAdult-boughtByElderly)!=0) {

                // delay, aby dostosowywac predkosc symulacji do potrzeb
                Thread.sleep(20);

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
                            // Jesli moze to nastepuje sprawdzenie, czy jest w stanie "dosiegnac"/"dostrzec" produkt i podniesc go
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
                // Zaktualizowanie mapy (GUI)
                getStorePanel().repaint();
            }
        } catch (InterruptedException ex) {
            System.out.println("Error.");
        }

        // zliczanie rezultatow symulacji
        System.out.println("\nWYNIKI SYMULACJI\n");
        System.out.println("Liczba klientow typu Child: " + numberOfChild);
        System.out.println("Liczba klientow typu Adult: " + numberOfAdult);
        System.out.println("Liczba klientow typu Elderly: " + numberOfElderly);
        System.out.println("Liczba produktow promocyjnych na początku symulacji: " + numberOfPromotional);

        int productsSold = 0;
        for (Product product : shop.getProducts())
        {
            if (product.getSold())
            {
                productsSold++;
            }
        }

        System.out.println("Liczba sprzedanych produktow promocyjnych: " + productsSold);
        System.out.println("Produkty zakupione przez klientow typu Child: " + boughtByChild);
        System.out.println("Produkty zakupione przez klientow typu Adult: " + boughtByAdult);
        System.out.println("Produkty zakupione przez klientow typu Elderly: " + boughtByElderly + "\n");


        // zapis wynikow symulacji do pliku WynikiSymulacji.txt
        try {
            PrintWriter zapis = new PrintWriter("WynikiSymulacji.txt");
            zapis.println("----- WYNIKI SYMULACJI -----\n");
            zapis.println("Klienci bioracy udzial w symulacji:");
            zapis.println("Liczba klientow typu Child: " + numberOfChild);
            zapis.println("Liczba klientow typu Adult: " + numberOfAdult);
            zapis.println("Liczba klientow typu Elderly: " + numberOfElderly + "\n");
            zapis.println("Liczba produktow promocyjnych na początku symulacji: " + numberOfPromotional);
            zapis.println("Liczba sprzedanych produktow promocyjnych: " + productsSold);
            zapis.println("Produkty zakupione przez klientow typu Child: " + boughtByChild);
            zapis.println("Produkty zakupione przez klientow typu Adult: " + boughtByAdult);
            zapis.println("Produkty zakupione przez klientow typu Elderly: " + boughtByElderly);
            zapis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /**
     * Metoda odpowiedzialna za resetowanie symulacji
     */
    public void resetSimulation()
    {

        shop.getClients().clear();
        boughtByChild = 0;
        boughtByAdult = 0;
        boughtByElderly = 0;

        int newNumberOfPromotional = promotionalSlider.getValue();
        int newNumberOfChild = childSlider.getValue();
        int newNumberOfAdult = adultSlider.getValue();
        int newNumberOfElderly = elderlySlider.getValue();
        shop = new Shop(newNumberOfPromotional, newNumberOfChild, newNumberOfAdult, newNumberOfElderly);
        clientsIndexes.clear();

        for(int i = 0; i < shop.getClients().size(); i++)
        {
            clientsIndexes.put(shop.getClient(i), i);
        }
    }

    /**
     * Metoda odpowiedzialna za wygenerowanie GUI
     * @param numberOfPromotional liczba produktów promocyjnych występujących w symulacji
     * @param numberOfChild liczba klientów typu <code>ChildClient</code> biorących udział w symulacji
     * @param numberOfAdult liczba klientów typu <code>AdultClient</code> biorących udział w symulacji
     * @param numberOfElderly liczba klientów typu <code>EldelryClient</code> biorących udział w symulacji
     */
    public void initialize(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        //ustawienia okna
        frame = new JFrame();
        frame.setBounds(200, 100, 850, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        //rysowanie mapy
        storePanel = new JPanel() {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);

                g.setColor(Color.black);
                g.drawRect(0,0,getWidth()-1,getHeight()-1);

                //półki w sklepie
                g.setColor(Color.gray);
                g.fillRect(60, 100, 40, 200);
                g.fillRect(180, 100, 40, 200);
                g.fillRect(300, 100, 40, 200);

                //ramki wokół półek
                g.setColor(Color.BLACK);
                g.drawRect(60, 100, 40, 200);
                g.drawRect(180, 100, 40, 200);
                g.drawRect(300, 100, 40, 200);

                //rysowanie kasy
                g.setColor(Color.BLACK);
                g.fillRect(360, 360, 40, 40);

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 11));
                g.drawString("KASA", 366, 381);

                //rysowanie klientów
                for (Client client : shop.getClients())
                {
                    int x = client.getXLocation();
                    int y = client.getYLocation();
                    int clientX = x*20;
                    int clientY = y*20;

                    if (client instanceof ChildClient)
                    {
                        g.setColor(dark_red);
                    }
                    else if (client instanceof AdultClient)
                    {
                        g.setColor(Color.BLUE);
                    }
                    else if (client instanceof ElderlyClient)
                    {
                        g.setColor(dark_green);
                    }

                    g.fillOval(clientX, clientY, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawOval(clientX, clientY, 20, 20);
                }

                //rysowanie produktów
                int i=1;
                for (Product product : shop.getProducts())
                {
                    int x = product.getX();
                    int y = product.getY();
                    int productX;
                    int productY = y*20;

                    // wartości współrzędnych x produktów nie odpowiadają miejscu "na półce", a miejscu, z którego dany produkt można podnieść
                    // (aby łatwo porownywac wspolrzedne klienta i produktu, sprawdzając, czy może wystąpić interakcja)
                    // dlatego w zaleznosci od regału, do x dodajemy 1 lub odejmujemy 1 (aby na mapie byl we wlasciwym miejscu)

                    if(i%2==1)
                    {
                        productX = (x+1) * 20;
                    }
                    else
                    {
                        productX = (x-1) * 20;
                    }

                    if (product.getPromotional() && !product.getSold())
                    {
                        g.setColor(Color.YELLOW);
                    }
                    else if (!product.getPromotional())
                    {
                        g.setColor(brown);
                    }
                    else if (product.getSold())
                    {
                        g.setColor(Color.GRAY);
                    }

                    g.fillRect(productX, productY, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(productX, productY, 20, 20);

                    // rysowanie znakow % na produktach promocyjnych
                    if (product.getPromotional() && !product.getSold()){
                        g.setColor(Color.BLACK);
                        g.setFont(new Font("Calibri", Font.BOLD, 17));
                        g.drawString("%", productX+4, productY+16);
                    }

                    i++;
                }
            }
        };
        storePanel.setBackground(Color.WHITE);
        storePanel.setBounds(60,65,400,400);
        frame.getContentPane().add(storePanel);

        //nagłówek
        JLabel titleLabel = new JLabel("Symulacja zakupów");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleLabel.setBounds(320, 10, 250, 25);
        frame.getContentPane().add(titleLabel);

        //przycisk start
        start = new JButton("START");
        start.setBackground(dark_green);
        start.setBounds(580,90,130,40);
        start.addActionListener(e -> {

            resetSimulation();
            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                protected Void doInBackground() {
                    runSimulation(promotionalSlider.getValue(), childSlider.getValue(), adultSlider.getValue(), elderlySlider.getValue());
                    return null;
                }

                protected void done() {
                    storePanel.repaint();
                }
            };
            worker.execute();
        });

        frame.add(start);

        //promotional slider
        JLabel promotionalTitleLabel = new JLabel("Promotional:");
        promotionalTitleLabel.setBounds(500, 155, 80, 25);
        frame.getContentPane().add(promotionalTitleLabel);

        promotionalLabel = new JLabel(String.valueOf(numberOfPromotional));
        promotionalLabel.setBounds(590, 155, 20, 25);
        frame.getContentPane().add(promotionalLabel);

        promotionalSlider = new JSlider(JSlider.HORIZONTAL, 0,60, numberOfPromotional);
        promotionalSlider.setMinorTickSpacing(5);
        promotionalSlider.setMajorTickSpacing(20);
        promotionalSlider.setPaintTicks(true);
        promotionalSlider.setPaintLabels(true);
        promotionalSlider.setBounds(610, 155, 190, 50);
        promotionalSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = promotionalSlider.getValue();
                promotionalLabel.setText(String.valueOf(value));
            }
        });

        frame.getContentPane().add(promotionalSlider);

        //child slider
        JLabel childTitleLabel = new JLabel("ChildClient:");
        childTitleLabel.setForeground(dark_red);
        childTitleLabel.setBounds(510, 210, 80, 25);
        frame.getContentPane().add(childTitleLabel);

        childLabel = new JLabel(String.valueOf(numberOfChild));
        childLabel.setBounds(590, 210, 20, 25);
        frame.getContentPane().add(childLabel);

        childSlider = new JSlider(JSlider.HORIZONTAL, 0,6, numberOfChild);
        childSlider.setMinorTickSpacing(1);
        childSlider.setMajorTickSpacing(2);
        childSlider.setPaintTicks(true);
        childSlider.setPaintLabels(true);
        childSlider.setBounds(610, 210, 180, 50);
        childSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e)
            {
                int value = childSlider.getValue();
                childLabel.setText(String.valueOf(value));
            }
        });

        frame.getContentPane().add(childSlider);

        //adult slider
        JLabel adultTitleLabel = new JLabel("AdultClient:");
        adultTitleLabel.setForeground(Color.BLUE);
        adultTitleLabel.setBounds(510, 265, 80, 25);
        frame.getContentPane().add(adultTitleLabel);

        adultLabel = new JLabel(String.valueOf(numberOfAdult));
        adultLabel.setBounds(590, 265, 20, 25);
        frame.getContentPane().add(adultLabel);

        adultSlider = new JSlider(JSlider.HORIZONTAL, 0,6, numberOfAdult);
        adultSlider.setMinorTickSpacing(1);
        adultSlider.setMajorTickSpacing(2);
        adultSlider.setPaintTicks(true);
        adultSlider.setPaintLabels(true);
        adultSlider.setBounds(610, 265, 180, 50);
        adultSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e)
            {
                int value = adultSlider.getValue();
                adultLabel.setText(String.valueOf(value));
            }
        });

        frame.getContentPane().add(adultSlider);

        //elderly slider
        JLabel elderlyTitleLabel = new JLabel("ElderlyClient:");
        elderlyTitleLabel.setForeground(dark_green);
        elderlyTitleLabel.setBounds(510, 320, 80, 25);
        frame.getContentPane().add(elderlyTitleLabel);

        elderlyLabel = new JLabel(String.valueOf(numberOfElderly));
        elderlyLabel.setBounds(590, 320, 20, 25);
        frame.getContentPane().add(elderlyLabel);

        elderlySlider = new JSlider(JSlider.HORIZONTAL, 0,6, numberOfElderly);
        elderlySlider.setMinorTickSpacing(1);
        elderlySlider.setMajorTickSpacing(2);
        elderlySlider.setPaintTicks(true);
        elderlySlider.setPaintLabels(true);
        elderlySlider.setBounds(610, 320, 180, 50);
        elderlySlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e)
            {
                int value = elderlySlider.getValue();
                elderlyLabel.setText(String.valueOf(value));
            }
        });

        frame.getContentPane().add(elderlySlider);

        // wyswietlenie GUI
        show();

    }

    /**
     * Statyczna metoda <code>main</code>, w której tworzona jest symulacja
     * @param args
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() -> {
            Simulation simulation = new Simulation(0,0,0,0);
        });
    }

}

