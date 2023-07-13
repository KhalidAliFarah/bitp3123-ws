package wordCounterInClientSide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 *
 * 
 * a JFrame that displays server text, word count, and connection status with specific labels. 
 * It has a fixed width and height.
 * 
 *  @author: Khalid
 * 
 */
public class WordCounterFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JLabel lblServerText; // Label to display server text
    
    private JLabel lblWordNum; // Label to display word count
    
    private JLabel lblStatusValue; // Label to display connection status

    private int width = 700;
    private int height = 200;

    public WordCounterFrame() {
        // Set layout manager to BorderLayout
        this.setLayout(new BorderLayout());
        
        // Set the title of the frame
        this.setTitle("TCP Application: Client Side");
        
        // Set the size of the frame
        this.setSize(width, height);
        
        // Center the frame on the screen
        this.setLocationRelativeTo(null);

        // Initialize server text label with placeholder value
        lblServerText = new JLabel("-");
        
        // Initialize connection status label with placeholder value
        lblStatusValue = new JLabel("-");
        
        // Initialize word count label with placeholder value
        lblWordNum = new JLabel("-");

        // Set default close operation to exit the application
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load and arrange the GUI components
        loadComponent();
    }

    // Returns a JPanel for displaying the connection status
    private JPanel getConnectionStatusPanel(Font font) {
        // Create a new JPanel
        JPanel panel = new JPanel();
        
        // Create a label for connection status
        JLabel lblConnStatus = new JLabel("Connection status: ");

        // Set the font for connection status label
        lblConnStatus.setFont(font);
        
        // Set the font for connection status value label
        lblStatusValue.setFont(font);
        
        // Set the background color of connection status label to black
        lblConnStatus.setBackground(Color.BLACK);
        
        // Set the text color of connection status label to white
        lblConnStatus.setForeground(Color.WHITE);
        
        // Set the background color of connection status value label to black
        lblStatusValue.setBackground(Color.BLACK);
        
        // Set the text color of connection status value label to green
        lblStatusValue.setForeground(Color.GREEN);

        // Set the background color of the panel to black
        panel.setBackground(Color.BLACK);
        
        // Add connection status label to the panel
        panel.add(lblConnStatus);
        
        // Add connection status value label to the panel
        panel.add(lblStatusValue);

        return panel; // Return the panel
    }

    // Returns a JPanel for displaying the server text and word count
    private JPanel getServerTextPanel(Font font) {
        // Create a new JPanel
        JPanel panel = new JPanel();
        
        // Create a label for server text
        JLabel lblText = new JLabel("This Text Is From The Server: ");
        
        // Create a label for word count
        JLabel lblNum = new JLabel("This is The Number of words Coming from The Server: ");

        // Set the font for server text label
        lblText.setFont(font);
        
        // Set the font for server text value label
        lblServerText.setFont(font);
        
        // Set the background color of server text label to black
        lblText.setBackground(Color.BLACK);
        
        // Set the text color of server text label to white
        lblText.setForeground(Color.WHITE);
        
        // Set the background color of server text value label to black
        lblServerText.setBackground(Color.BLACK);
        
        // Set the text color of server text value label to green
        lblServerText.setForeground(Color.GREEN);

        // Set the font for word count label
        lblNum.setFont(font);
        
        // Setthe font for word count value label
        lblWordNum.setFont(font);
        
        // Set the background color of word count label to black
        lblNum.setBackground(Color.BLACK);
        
        // Set the text color of word count label to white
        lblNum.setForeground(Color.WHITE);
        
        // Set the background color of word count value label to black
        lblWordNum.setBackground(Color.BLACK);
        
        // Set the text color of word count value label to green
        lblWordNum.setForeground(Color.GREEN);

        // Set the background color of the panel to black
        panel.setBackground(Color.BLACK);
        
        // Add server text label to the panel
        panel.add(lblText);
        
        // Add server text value label to the panel
        panel.add(lblServerText);
        
        // Add word count label to the panel
        panel.add(lblNum);
        
        // Add word count value label to the panel
        panel.add(lblWordNum);

        return panel; // Return the panel
    }

    // Load and arrange the GUI components
    private void loadComponent() {
    	
        // Get the font style for labels
        Font font = getFontStyle();

        // Create the connection status panel
        JPanel northPanel = getConnectionStatusPanel(font);
        
        // Add the panel to the north (top) of the frame
        this.add(northPanel, BorderLayout.NORTH);

        // Create the server text panel
        JPanel center = getServerTextPanel(font);
        
        // Add the panel to the center of the frame
        this.add(center, BorderLayout.CENTER);
    }

    // Returns the desired font style for the labels
    private Font getFontStyle() {
    	
        // Create a new font object with Arial, bold style, and size 24
        Font font = new Font("Arial", Font.BOLD, 24);
        
        return font; // Return the font
    }

    // Update the server text label with the provided text
    public void updateServerText(String serverText) {
        this.lblServerText.setText(serverText);
    }

    // Update the connection status label based on the connection status
    public void updateConnectionStatus(boolean connStatus) {
        String status = "No connection to server.";
        if (connStatus)
            status = "Connection has been established.";
        this.lblStatusValue.setText(status);
    }

    // Update the word count label with the provided number
    public void updateWordNum(int wordNum) {
        this.lblWordNum.setText(Integer.toString(wordNum));
    }
}
