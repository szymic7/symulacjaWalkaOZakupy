import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SwingWindow
{
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

    public SwingWindow(int numberOfPromotional, int numberOfChild, int numberOfAdult, int numberOfElderly)
    {
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

        //ustawienia mapy
        storePanel = new JPanel();
        storePanel.setBounds(60,70,400,400);
        storePanel.setBackground(Color.black);

        frame.add(storePanel);

        //nagłówek
        JLabel titleLabel = new JLabel("Symulacja zakupów");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setBounds(330, 10, 200, 20);
        frame.getContentPane().add(titleLabel);

        //promotional slider
        JLabel promotionalTitleLabel = new JLabel("Promotional:");
        promotionalTitleLabel.setBounds(500, 100, 80, 20);
        frame.getContentPane().add(promotionalTitleLabel);

        promotionalLabel = new JLabel(String.valueOf(numberOfPromotional));
        promotionalLabel.setBounds(590, 100, 20, 20);
        frame.getContentPane().add(promotionalLabel);

        promotionalSlider = new JSlider(JSlider.HORIZONTAL, 0,60, numberOfPromotional);
        promotionalSlider.setMinorTickSpacing(2);
        promotionalSlider.setMajorTickSpacing(20);
        promotionalSlider.setPaintTicks(true);
        promotionalSlider.setPaintLabels(true);
        promotionalSlider.setBounds(610, 100, 190, 50);
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
        childTitleLabel.setBounds(510, 160, 80, 20);
        frame.getContentPane().add(childTitleLabel);

        childLabel = new JLabel(String.valueOf(numberOfChild));
        childLabel.setBounds(590, 160, 20, 20);
        frame.getContentPane().add(childLabel);

        childSlider = new JSlider(JSlider.HORIZONTAL, 0,6, numberOfChild);
        childSlider.setMinorTickSpacing(1);
        childSlider.setMajorTickSpacing(2);
        childSlider.setPaintTicks(true);
        childSlider.setPaintLabels(true);
        childSlider.setBounds(610, 160, 180, 50);
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
        adultTitleLabel.setBounds(510, 215, 80, 20);
        frame.getContentPane().add(adultTitleLabel);

        adultLabel = new JLabel(String.valueOf(numberOfAdult));
        adultLabel.setBounds(590, 215, 20, 20);
        frame.getContentPane().add(adultLabel);

        adultSlider = new JSlider(JSlider.HORIZONTAL, 0,6, numberOfAdult);
        adultSlider.setMinorTickSpacing(1);
        adultSlider.setMajorTickSpacing(2);
        adultSlider.setPaintTicks(true);
        adultSlider.setPaintLabels(true);
        adultSlider.setBounds(610, 215, 180, 50);
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
        elderlyTitleLabel.setBounds(510, 265, 80, 20);
        frame.getContentPane().add(elderlyTitleLabel);

        elderlyLabel = new JLabel(String.valueOf(numberOfElderly));
        elderlyLabel.setBounds(590, 265, 20, 20);
        frame.getContentPane().add(elderlyLabel);

        elderlySlider = new JSlider(JSlider.HORIZONTAL, 0,6, numberOfElderly);
        elderlySlider.setMinorTickSpacing(1);
        elderlySlider.setMajorTickSpacing(2);
        elderlySlider.setPaintTicks(true);
        elderlySlider.setPaintLabels(true);
        elderlySlider.setBounds(610, 265, 180, 50);
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

