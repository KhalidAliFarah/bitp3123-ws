package wordCounterInServerSide;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * WordCounterServerApplication is the main class representing the server-side application for counting words.
 * It listens for client connections, generates text, and sends the text to clients.
 * 
 * @author: Khalid
 * 
 */
public class WordCounterServerApplication {

    public static void main(String[] args) {
        // Define the port number on which the server will listen for client connections
        int portNo = 8090;
        
        // Create an instance of WordCounterServerFrame for displaying server information
        WordCounterServerFrame serverFrame = new WordCounterServerFrame();
        serverFrame.setVisible(true);

        try {
            // Create a ServerSocket that listens on the specified port number
            ServerSocket serverSocket = new ServerSocket(portNo);
            
            // Create an instance of GenerateText for generating random text
            GenerateText textGenerator = new GenerateText();
            
            // Initialize a counter for total requests received
            int totalRequest = 0;

            while (true) {
                // Update the server status to indicate it is not processing a request
                serverFrame.updateServerStatus(false);
                
                // Accept a connection from a client
                Socket socket = serverSocket.accept();
                
                // Generate random text
                String text = textGenerator.getText();
                
                // Get the output stream from the socket
                OutputStream outputStream = socket.getOutputStream();
                
                // Create a DataOutputStream for writing data to the output stream
                DataOutputStream dos = new DataOutputStream(outputStream);
                
                // Write the generated text to the client
                dos.writeUTF(text);
                
                // Close the DataOutputStream and the output stream
                dos.close();
                outputStream.close();

                // Increment the total number of requests received
                totalRequest++;
                
                // Update the request status on the server frame
                serverFrame.updateRequestStatus("Data sent to the client: " + text);
                serverFrame.updateRequestStatus("Accepted connection from the client. Total requests = " + totalRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
