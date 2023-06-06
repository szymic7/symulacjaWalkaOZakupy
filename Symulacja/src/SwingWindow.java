import javax.swing.JFrame;

public class SwingWindow
{
    private JFrame window = new JFrame();

    public SwingWindow()
    {
        window.setTitle("SIEMA");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(500, 500);
        window.setLocationRelativeTo(null);
    }

    public void show()
    {
        window.setVisible(true);
    }

}

