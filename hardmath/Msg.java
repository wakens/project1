package hardmath;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Msg
{
    /*
     * This method creates an easy way to display a String message
     * and ask for user input in JOptionPane.
     */
    public static String in(final String msg) {
        return JOptionPane.showInputDialog(msg);
    }
 
    /*
     * This method creates an easy way to display a String message in JOptionPane.
     */
    public static void msg(final String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
