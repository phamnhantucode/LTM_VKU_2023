package zSERVER;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class zProgram {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }
        new Thread( new FrmServerGUI()).start();
    }
}
