package translatorServerSide;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * server-side application that handles translation requests and has a GUI interface 
 * to display incoming requests and their responses.
 * 
 *  @author Khalid
 * 
 */
public class TranslatorServerSideApplicationGUI extends JFrame {
    private JTextArea textAreaRequests;
    private JTextArea textAreaResponses;
    private JLabel labelRequests;
    private JLabel labelResponses;
    private int requestCount;
    private List<String> requestDetails;
    private List<String> responseDetails;

    /**
     * Constructor for the TranslatorServerSideApplicationGUI class.
     * Initializes and organizes the GUI components.
     */
    public TranslatorServerSideApplicationGUI() {
        setTitle("Translator Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new GridLayout(1, 2));

        textAreaRequests = new JTextArea(10, 20);
        textAreaRequests.setEditable(false);

        textAreaResponses = new JTextArea(10, 20);
        textAreaResponses.setEditable(false);

        labelRequests = new JLabel("Requests:");
        labelResponses = new JLabel("Responses:");

        add(labelRequests);
        add(labelResponses);
        add(new JScrollPane(textAreaRequests));
        add(new JScrollPane(textAreaResponses));

        setVisible(true);

        requestCount = 0;
        requestDetails = new ArrayList<>();
        responseDetails = new ArrayList<>();

        startServer();
    }

    /**
     * Main method to start the Translator Server application.
     *
     * @param args Command-line arguments (not used in this case)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TranslatorServerSideApplicationGUI::new);
    }

    /**
     * Starts the server and listens for incoming client requests.
     */
    private void startServer() {
        ServerSocket serverSocket = null;

        try {
            int portNo = 2211;
            serverSocket = new ServerSocket(portNo);
            System.out.println("Server started. Waiting for requests...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Request received from client " + clientSocket.getInetAddress());

                // Read indexes from client
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                int textIndex = dis.readInt();
                int languageIndex = dis.readInt();

                // Perform translation
                GeneratorTranslatorGUI translationGenerator = new GeneratorTranslatorGUI();
                String translation = translationGenerator.translate(textIndex, languageIndex);

                // Send translation to client
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                dos.writeUTF(translation);

                // Close streams and socket
                dis.close();
                dos.close();
                clientSocket.close();

                // Update request and response details
                updateRequestDetails(textIndex, languageIndex);
                updateResponseDetails(translation);
            }
        } catch (IOException e) {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    /**
     * Updates the details of the received translation request.
     *
     * @param textIndex     Index of the selected text
     * @param languageIndex Index of the selected language
     */
    private void updateRequestDetails(int textIndex, int languageIndex) {
        requestCount++;
        String requestDetail = "Request " + requestCount + ": " + LocalDateTime.now() +
                " - Text Index: " + textIndex + ", Language Index: " + languageIndex;
        requestDetails.add(requestDetail);
        updateRequestTextArea();
    }

    /**
     * Updates the details of the generated translation response.
     *
     * @param translation Translated text
     */
    private void updateResponseDetails(String translation) {
        String responseDetail = "Response " + requestCount + ": " + LocalDateTime.now() + " - " + translation;
        responseDetails.add(responseDetail);
        updateResponseTextArea();
    }

    /**
     * Updates the text area displaying the request details.
     */
    private void updateRequestTextArea() {
        StringBuilder sb = new StringBuilder();
        for (String request : requestDetails) {
            sb.append(request).append("\n");
        }
        textAreaRequests.setText(sb.toString());
    }

    /**
     * Updates the text area displaying the response details.
     */
    private void updateResponseTextArea() {
        StringBuilder sb = new StringBuilder();
        for (String response : responseDetails) {
            sb.append(response).append("\n");
        }
        textAreaResponses.setText(sb.toString());
    }
}
