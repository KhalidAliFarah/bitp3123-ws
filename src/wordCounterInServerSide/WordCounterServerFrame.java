package wordCounterInServerSide;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * WordCounterServerFrame represents the front-end of the server-side application for counting words.
 * It provides a GUI interface for displaying the server status and the status of each request made to the server.
 * 
 * @author: Khalid
 */
public class WordCounterServerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // Private components
    private JLabel lblServerStatus;
    private JTextArea txtRequestStatus;
    
    // Private dimension
    private int width = 700;
    private int height = 500;
    
    /**
     * Constructor for the WordCounterServerFrame class.
     * Initializes the frame and its components.
     */
    public WordCounterServerFrame() {
        this.setLayout(new BorderLayout());
        this.setTitle("TCP Application: Server Side");
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        // Initialize server status label
        this.lblServerStatus = new JLabel("-");
        
        // Initialize request status text area
        this.txtRequestStatus = new JTextArea(20, 70);
        
        // Load the components onto the frame
        loadComponent();
    }
    
    /**
     * Creates and arranges Swing components to display the server status.
     * 
     * @param font The default font for the application.
     * @return Swing components organized in a panel.
     */
    private JPanel getServerStatusPanel(Font font) {
        // Components to display server's status
        JPanel panel = new JPanel();
        JLabel lblServer = new JLabel("Server status: ");
        
        // Style the components
        lblServer.setFont(font);
        lblServerStatus.setFont(font);
        lblServer.setBackground(Color.WHITE);
        lblServer.setOpaque(true);
        lblServerStatus.setBackground(Color.WHITE);
        lblServerStatus.setOpaque(true);
        
        // Organize components into the panel
        panel.add(lblServer);
        panel.add(lblServerStatus);
        
        return panel;
    }
    
    /**
     * Creates and arranges Swing components to display the status of requests made to the server.
     * 
     * @return Swing components organized in a panel.
     */
    private JPanel getRequestStatusPanel() {
        // Component to display request's status
        JPanel panel = new JPanel();
        
        // Set default message when the frame is launched for the first time
        txtRequestStatus.setText("\n > Server is running");
        txtRequestStatus.setEditable(false);
        
        // Styling the request text
        txtRequestStatus.setFont(new Font("Courier", Font.PLAIN, 15));
        
        // Add component to panel
        panel.add(txtRequestStatus);
        
        return panel;
    }
    
    /**
     * Defines the default font style for the application.
     * 
     * @return Font object representing the default font style.
     */
    private Font getFontStyle() {
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
        return font;
    }
    
    /**
     * Loads and arranges the GUI components onto the frame.
     */
    public void loadComponent() {
        // Get the server status panel and add it to the frame
        Font font = this.getFontStyle();
        JPanel topPanel = this.getServerStatusPanel(font);
        this.add(topPanel, BorderLayout.NORTH);
        
        // Component to display request's status
        JPanel centrePanel = this.getRequestStatusPanel();
        this.add(centrePanel, BorderLayout.CENTER);
    }
    
    /**
     * Updates the server status displayed on the frame.
     * 
     * @param result The status of the server.
     */
    public void updateServerStatus(boolean result) {
        String status = "Waiting for connection.";
        if (result)
            status = "Received connection.";
        this.lblServerStatus.setText(status);
    }
    
    /**
     * Updates the status of the requests made to the server.
     * 
     * @param status The request status.
     */
    public void updateRequestStatus(String status) {
        // Get the current status displayed on the window
        String currentText = this.txtRequestStatus.getText();
        txtRequestStatus.setEditable(true);
        
        // Display the latest status on top
        status += "\n > " + currentText;
        this.txtRequestStatus.setText(status);
        txtRequestStatus.setEditable(false);
    }
}
