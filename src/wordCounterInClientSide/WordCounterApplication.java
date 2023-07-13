package wordCounterInClientSide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class WordCounterApplication {

    

    /**
     * 
     * The main method of the WordCounterApplication class.
     * Connects to a server and performs word counting operations.
     * 
     * @author: @Khalid
     * 
     */
    public static void main(String[] args) {
        int serverPort = 8090;

        // Create an instance of the WordCounterFrame class for GUI
        WordCounterFrame clientFrame = new WordCounterFrame();
        clientFrame.setVisible(true);

        try {
            // Connect to the server using a socket
            Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);
            clientFrame.updateConnectionStatus(socket.isConnected());

            // Read data from the input stream of the socket
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read a line of text from the server and update the GUI frame
            String text = bufferedReader.readLine();
            clientFrame.updateServerText(text);

            // Split the received text into words and count the number of words
            String[] words = text.split(" ");
            int wordCount = words.length;
            clientFrame.updateWordNum(wordCount);

            // Close the BufferedReader and the socket
            bufferedReader.close();
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
