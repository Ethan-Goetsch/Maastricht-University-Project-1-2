package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import Components.PhysicsComponent;

public class UIManager
{
    private static JFrame mainFrame;

    private static JPanel mainPanel;

    public static void staticStart()
    {
        createGUI();
    }

    private static void createGUI()
    {
        mainFrame = new JFrame();

        mainPanel = new JPanel();


        //mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainFrame.setSize(800, 600);

        mainFrame.setResizable(false);

        mainFrame.setLocationRelativeTo(null);
        
        mainFrame.setVisible(true);
    }

    public static void addComponent(PhysicsComponent physicsComponent)
    {
        mainFrame.add(physicsComponent);

        mainFrame.revalidate();
    }
}