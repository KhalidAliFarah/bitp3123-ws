package translatorClientSide;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * 
 * client-side application that connects to a server, 
 * facilitates user interaction for selecting translation options,
 *  and displays the translation result on the console.
 * 
 * @author Khalid
 * 
 */
public class TranslatorClientSide {
    public static void main(String[] args) {
        try {
            int serverSidePort = 2211;
            Socket socket = new Socket(InetAddress.getLocalHost(), serverSidePort);

            // Read user input and initialize variables
            Scanner userInput = new Scanner(System.in);
            int languageOption = 0;
            int languageIndex = 0;
            int textOption = 0;
            int textIndex = 0;

            // Get the desired translation option from the user
            while (languageOption < 1 || languageOption > 3) {
                System.out.println("\n=== Choose a Translation Option ===");
                System.out.print("\n");
                System.out.println("1. Translate from English to Bahasa Malaysia");
                System.out.println("2. Translate from English to Arabic");
                System.out.println("3. Translate from English to Korean");
                System.out.print("\n");
                System.out.print("\n Please enter the number corresponding to your desired translation option: ");

                languageOption = userInput.nextInt();
            }

            languageIndex = languageOption;
            userInput.nextLine();

            // Get the text to be translated from the user
            while (textOption < 1 || textOption > 6) {
                System.out.println("\n=== Translation Options ===");
                System.out.print("\n");
                System.out.println("1. Good morning");
                System.out.println("2. Good night");
                System.out.println("3. How are you?");
                System.out.println("4. Thank you");
                System.out.println("5. Goodbye");
                System.out.println("6. What’s up?");
                System.out.print("\n");
                System.out.print("Please enter the text you want to translate: ");

                textOption = userInput.nextInt();
            }

            textIndex = textOption - 1;

            // Send selected language and text indexes to the server
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeInt(textIndex);
            dos.writeInt(languageIndex);

            // Receive the translation from the server
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String translation = bufferedReader.readLine();
            System.out.println("\nTranslation: " + translation);

            // Close streams and socket
            dos.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
