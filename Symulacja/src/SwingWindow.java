import javax.swing.*;
import java.awt.*;
import javax.swing.event.*;

public class SwingWindow
{
    private JFrame frame;
    private JSlider childSlider;
    private JSlider adultSlider;
    private JSlider elderlySlider;
    private JLabel childLabel;
    private JLabel adultLabel;
    private JLabel elderlyLabel;
    private JPanel storePanel;

    public SwingWindow()
    {
        initialize();
    }

    private void initialize()
    {
        frame = new JFrame();
        frame.setBounds(200, 200, 850, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        storePanel = new JPanel();
        storePanel.setLayout(new GridLayout(4, 4)); // Zakładamy siatkę 4x4 półek

        /* Tworzenie półek sklepowych
        for (int i = 0; i < 16; i++) {
            JPanel polka = new JPanel();
            polka.setBackground(Color.WHITE);
            polka.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            storePanel.add(polka);
        }
        */
        frame.add(storePanel);

        JLabel titleLabel = new JLabel("Symulacja zakupów");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setBounds(330, 10, 200, 20);
        frame.getContentPane().add(titleLabel);

        //child slider
        JLabel childTitleLabel = new JLabel("ChildClient:");
        childTitleLabel.setBounds(570, 185, 80, 20);
        frame.getContentPane().add(childTitleLabel);

        childLabel = new JLabel("0");
        childLabel.setBounds(650, 185, 20, 20);
        frame.getContentPane().add(childLabel);

        childSlider = new JSlider();
        childSlider.setMinimum(0);
        childSlider.setMaximum(10);
        childSlider.setBounds(670, 185, 150, 20);
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
        adultTitleLabel.setBounds(570, 225, 80, 20);
        frame.getContentPane().add(adultTitleLabel);

        adultLabel = new JLabel("0");
        adultLabel.setBounds(650, 225, 20, 20);
        frame.getContentPane().add(adultLabel);

        adultSlider = new JSlider();
        adultSlider.setMinimum(0);
        adultSlider.setMaximum(10);
        adultSlider.setBounds(670, 225, 150, 20);
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
        elderlyTitleLabel.setBounds(570, 265, 80, 20);
        frame.getContentPane().add(elderlyTitleLabel);

        elderlyLabel = new JLabel("0");
        elderlyLabel.setBounds(650, 265, 20, 20);
        frame.getContentPane().add(elderlyLabel);

        elderlySlider = new JSlider();
        elderlySlider.setMinimum(0);
        elderlySlider.setMaximum(10);
        elderlySlider.setBounds(670, 265, 150, 20);
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

