// Package containing the server-side implementation of a translator application
package translatorServerSide;

// Import necessary Java IO and networking libraries
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 the main class of the server-side application for a translator system. 
 *It receives client requests, performs translation, and sends back the translated text.
 * 
 * @author Khalid
 */
public class TranslatorServerSideApplication {

    /**
     * The main method, where the server-side application begins execution.
     * 
     * @param args Command-line arguments (not used in this case)
     * @throws IOException if an I/O error occurs when accepting a client request
     */
    public static void main(String[] args) throws IOException {

        // Initialize a ServerSocket object to null
        ServerSocket serverSocket = null;

        try {
            // Define the port number on which the server will listen for client requests
            int portNo = 2211;
            
            // Create a new ServerSocket that listens on the defined port number
            serverSocket = new ServerSocket(portNo);

            // Notify server operator that the server is waiting for a client request
            System.out.println("Waiting for request...");

            // Server enters an infinite loop, waiting for and processing client requests
            while (true) {
            	
                // Accept a connection from a client
                Socket clientSocket = serverSocket.accept();

                // Create a DataInputStream that allows reading data sent from the client
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());

                // Read integers representing the text and language index from the client
                int textIndex = dis.readInt();
                int languageIndex = dis.readInt();

                // Create a new translation generator and generate a translation based on the received text and language index
                GeneratorTranslator translationGenerator = new GeneratorTranslator();
                String translation = translationGenerator.translate(textIndex, languageIndex);

                // Create a DataOutputStream that allows sending data back to the client
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

                // Write the translation back to the client
                dos.writeUTF(translation);

                // Close the input and output streams and the client socket after sending the response
                dis.close();
                dos.close();
                clientSocket.close();
            }

        } catch (IOException e) {
            // In case of an exception, close the server socket if it is not already closed
            if (serverSocket != null)
                serverSocket.close();
            // Print the stack trace for debugging purposes
            e.printStackTrace();
        }

    }

}
