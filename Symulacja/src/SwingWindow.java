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

        JLabel titleLabel = new JLabel("Symulacja zakupów");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        titleLabel.setBounds(210, 10, 200, 20);
        frame.getContentPane().add(titleLabel);

        //child slider
        JLabel childTitleLabel = new JLabel("ChildClient:");
        childTitleLabel.setBounds(20, 50, 80, 20);
        frame.getContentPane().add(childTitleLabel);

        childLabel = new JLabel("0");
        childLabel.setBounds(100, 50, 20, 20);
        frame.getContentPane().add(childLabel);

        childSlider = new JSlider();
        childSlider.setMinimum(0);
        childSlider.setMaximum(10);
        childSlider.setBounds(120, 50, 150, 20);
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
        adultTitleLabel.setBounds(20, 90, 80, 20);
        frame.getContentPane().add(adultTitleLabel);

        adultLabel = new JLabel("0");
        adultLabel.setBounds(100, 90, 20, 20);
        frame.getContentPane().add(adultLabel);

        adultSlider = new JSlider();
        adultSlider.setMinimum(0);
        adultSlider.setMaximum(10);
        adultSlider.setBounds(120, 90, 150, 20);
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
        elderlyTitleLabel.setBounds(20, 130, 80, 20);
        frame.getContentPane().add(elderlyTitleLabel);

        elderlyLabel = new JLabel("0");
        elderlyLabel.setBounds(100, 130, 20, 20);
        frame.getContentPane().add(elderlyLabel);

        elderlySlider = new JSlider();
        elderlySlider.setMinimum(0);
        elderlySlider.setMaximum(10);
        elderlySlider.setBounds(120, 130, 150, 20);
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

