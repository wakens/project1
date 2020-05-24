

package hardmath;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Msg
{
    public static String in(final String msg) {
        return JOptionPane.showInputDialog(msg);
    }
    
    public static void msg(final String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
}
