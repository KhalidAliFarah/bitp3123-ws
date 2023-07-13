package translatorClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 
 *client-side application with a GUI interface for selecting a language and text to be translated. 
 *It communicates with the server to obtain the translation and displays the result on the GUI.
 *
 * @author Khalid
 */
public class TranslatorClientSideGUI extends JFrame implements ActionListener {

    private JLabel labelLanguage;
    private JComboBox<String> comboBoxLanguage;
    private JLabel labelText;
    private JComboBox<String> comboBoxText;
    private JButton buttonTranslate;
    private JTextArea textAreaTranslation;

    /**
     * Constructor for the TranslatorClientSideGUI class.
     * Initializes and organizes the GUI components.
     */
    public TranslatorClientSideGUI() {
        setTitle("Translator Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        // Create and initialize GUI components
        labelLanguage = new JLabel("Select Language:");
        comboBoxLanguage = new JComboBox<>(new String[]{"Korean", "Bahasa Malaysia", "Arabic"});

        labelText = new JLabel("Select Text:");
        comboBoxText = new JComboBox<>(new String[]{"Good morning", "Good night", "How are you?", "Thank you", "Goodbye", "What’s up?"});

        buttonTranslate = new JButton("Translate");
        buttonTranslate.addActionListener(this);

        textAreaTranslation = new JTextArea(10, 30);
        textAreaTranslation.setEditable(false);

        // Add GUI components to the frame
        add(labelLanguage);
        add(comboBoxLanguage);
        add(labelText);
        add(comboBoxText);
        add(buttonTranslate);
        add(textAreaTranslation);

        setVisible(true);
    }

    /**
     * Main method to start the Translator Client application.
     *
     * @param args Command-line arguments (not used in this case)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TranslatorClientSideGUI::new);
    }

    /**
     * ActionListener implementation for handling button click events.
     *
     * @param e ActionEvent object representing the event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonTranslate) {
            String selectedLanguage = (String) comboBoxLanguage.getSelectedItem();
            String selectedText = (String) comboBoxText.getSelectedItem();
            int languageIndex = comboBoxLanguage.getSelectedIndex();
            int textIndex = comboBoxText.getSelectedIndex();

            try {
                int serverSidePort = 2211;
                Socket socket = new Socket(InetAddress.getLocalHost(), serverSidePort);

                // Send selected language and text indexes to the server
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeInt(textIndex);
                dos.writeInt(languageIndex);

                // Receive the translation from the server
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String translation = bufferedReader.readLine();

                // Update the GUI with the translation result
                textAreaTranslation.setText(selectedText + " (" + selectedLanguage + "): " + translation);

                // Close streams and socket
                dos.close();
                bufferedReader.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
