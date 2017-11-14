package english;

import javax.swing.JFrame;

public class English{
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(350, 290); // set frame size
        window.setResizable(false);
        window.setVisible(true); // display frame
    }
}