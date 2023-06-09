import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SwingWindow
{
    private Shop shop;
    private int numberOfPromotional;
    private int numberOfChild;
    private int numberOfAdult;
    private int numberOfElderly;

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
    private JButton stop;

    public SwingWindow(Shop shop, int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
        this.shop = shop;
        this.numberOfPromotional = numberOfPromotional;
        this.numberOfChild = numberOfChild;
        this.numberOfAdult = numberOfAdult;
        this.numberOfElderly = numberOfElderly;

        initialize();
    }

    private void initialize()
    {
        //ustawienia okna
        frame = new JFrame();
        frame.setBounds(200, 200, 850, 550);
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
                //ci klienci źle się rysują
                //w sensie w złych miejscach
                //x i y gdzieś jest pojeabny ale nie wiem gdzie i jak
                //bo jestem kurwa zmęczony
                //może to jest problem  z umijescowieniem window.show() w klasie Simulation
                for (Client client : shop.getClients())
                {
                    int x = client.getXLocation();
                    int y = client.getYLocation();
                    int clientX = x*20;
                    int clientY = y*20;
                    
                    if (client instanceof ChildClient) 
                    {
                        g.setColor(Color.RED);
                    } 
                    else if (client instanceof AdultClient) 
                    {
                        g.setColor(Color.BLUE);
                    } 
                    else if (client instanceof ElderlyClient) 
                    {
                        g.setColor(Color.GREEN);
                    }
                    
                    g.fillOval(clientX, clientY, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawOval(clientX, clientY, 20, 20);
                }

                //rysowanie produktów
                //to nie działa tak jak chcemy jeszcze bo są pokazane są te pola promocyjne
                //z których klienci mogą kupić produkt a powinny się pojawiać te produkty na tych półkach szarych
                for (Product product : shop.getProducts()) {
                    int x = product.getX();
                    int y = product.getY();
                    int productX = x*20;
                    int productY = y*20;

                    if (product.isPromotional())
                    {
                        g.setColor(Color.YELLOW);
                    }
                    else
                    {
                        g.setColor(Color.GRAY);
                    }

                    g.fillRect(productX, productY, 20, 20);
                    g.setColor(Color.BLACK);
                    g.drawRect(productX, productY, 20, 20);
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

        //przyciski start/stop
        //ogólnie ci powiem że to pojebane bo te przyciski powinny startować i zatrzymywać symulację
        //poza tym za każdym razem jak symulacja się skończy to musi być
        //możliwość ponownego jej odplaenia z innymi parametrami wybranymi na suwakach
        //podsumowując nie wiem jak my to zrobimy
        start = new JButton("START");
        start.setBackground(Color.GREEN);
        start.setBounds(520,90,120,40);

        frame.add(start);

        stop = new JButton("STOP");
        stop.setBackground(Color.red);
        stop.setBounds(660,90,120,40);

        frame.add(stop);

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
            public void stateChanged(ChangeEvent e)
            {
                int value = promotionalSlider.getValue();
                promotionalLabel.setText(String.valueOf(value));
            }
        });

        frame.getContentPane().add(promotionalSlider);

        //child slider
        JLabel childTitleLabel = new JLabel("ChildClient:");
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

    }

    public void show()
    {
        frame.setVisible(true);
    }

}

