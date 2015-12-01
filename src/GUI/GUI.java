package GUI;

import javax.swing.*;

public class GUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        ImageIcon favicon;
        favicon = new ImageIcon(GUI.class.getResource("/images/plain01.png"));
        JFrame frame = new GUI();
        frame.setSize(1000, 720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Space Impact door Frederik Smolders");
        frame.setIconImage(favicon.getImage());
        frame.setLocationRelativeTo(null);
        JPanel paneel = new Paneel();
        frame.setContentPane(paneel);
        frame.setVisible(true);
    }
}