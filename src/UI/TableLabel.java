package UI;

/**
 * Package of GUI table goodness
 */
 
import java.awt.Component;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
 
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
 
/**
 * A Table with a Label!
 * 
 * @author John McParland
 * 
 */
public class TableLabel extends JFrame {
 
    /**
     * @see java.io.Serializable
     */
    private static final long serialVersionUID = 1l;
    /**
     * Logger for this class
     */
    private static final Logger LOGGER = Logger.getLogger(TableLabel.class
            .getName());
 
    /**
     * Create a Table displaying a Label!
     */
    public TableLabel() {
        setTitle("Table Label");
        setSize(600, 480);
        this.getContentPane().add(createMainPane());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    /**
     * Create the main panel upon which a table will be displayed
     * 
     * @return The main panel
     */
    private JPanel createMainPane() {
        // Some made-up columns and data
        String[] cols = { "First name", "Second name", "Motto" };
        String[][] data = { { "John", "McParland", "John likes Java" },
                { "Bob", "Smith", "Swing is getting old now - such a shame" },
                { "June", "Jones", "Lorem ipsum doler sit amet" } };
 
        // NOTE 1: I have created my own table, and implemented getColumnClass
        // because this is what allows the custom renderer to be used
        JTable table = new JTable(data, cols) {
 
            /**
             * @see java.io.Serializable
             */
            private static final long serialVersionUID = 3l;
 
            /*
             * (non-Javadoc)
             * 
             * @see javax.swing.JTable#getColumnClass(int)
             */
            public Class<?> getColumnClass(int column) {
                if (0 < this.getRowCount()) {
                    return getValueAt(0, column).getClass();
                } else {
                    return null;
                }
            }
        };
 
        // NOTE 2: Using a custom cell renderer
        table.setDefaultRenderer(String.class, new LabelRenderer());
 
        // Add into a scrollpane / regular pane
        JScrollPane scroller = new JScrollPane(table);
        JPanel panel = new JPanel();
        panel.add(scroller);
        return panel;
    }
    
    /**
     * Displays Strings as JLabels
     */
    private class LabelRenderer extends DefaultTableCellRenderer {
 
        /**
         * @see java.io.Serializable
         */
        private static final long serialVersionUID = 2l;
 
        /*
         * (non-Javadoc)
         * 
         * @see
         * javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent
         * (javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
         */
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {
            LOGGER.info("Rendering (" + row + "," + column + ") - "
                    + value.toString() + " as a label");
            JLabel label = new JLabel(value.toString());
            try {
                // BTW: Ensure you are allowed to use the icons -
                // This page seems to allow their use;
                // http://www.iconarchive.com/show/hat-icons-by-rob-sanders/Hat-cowboy-black-icon.html
                label.setIcon(new ImageIcon(new URL(
                        "http://www.iconarchive.com/icons/rob-sanders/hat/32/Hat-cowboy-black-icon.png")));
            } catch (MalformedURLException mfe) {
                LOGGER.severe("Aargh I cannot find the icon");
            }
            return label;
        }
    }
    

    /**
     * Main program
     * 
     * @param args
     *            Program arguments, not required!
     */
    public static void main(String[] args) {
        // Put it in a different thread
        SwingUtilities.invokeLater(
        /**
         * Runs the GUI
         */
        new Runnable() {
            /*
             * (non-Javadoc)
             * 
             * @see java.lang.Runnable#run()
             */
            public void run() {
                // Create the demo!
                new TableLabel();
            }
        });
    }
 
}
    