import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CardGame {
    private int numPlayers;
    private String filepath;
    
    public static void main(String[] args) {
        // TODO: main method implementation
        String packLocation;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of players: ");

        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        while (true) {
            System.out.println("Please enter location of pack to load: ");
            packLocation = scanner.nextLine();
            if (isValidPack(packLocation, numPlayers)) {
                break;
            } else {
                System.out.println("Invalid pack. Please try again");
            }
            }
        
        CardPack pack = new CardPack(numPlayers, packLocation);
        pack.initialisePack(packLocation);

        GameState gameState = new GameState();
        Deck[] decks = new Deck[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            decks[i] = new Deck(i + 1, gameState);
        }

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            if (i == numPlayers - 1) {
                players[i] = new Player(i + 1, decks[i], decks[0], gameState);
            } else {
                players[i] = new Player(i + 1, decks[i], decks[i+1], gameState);
            }
        }

        for (int round = 0; round < 4; round++) {
            for(int i = 0; i < numPlayers; i++) {
                Card card = pack.takeCard();
                players[i].addToHand(card);
            }
        }

        for (int round = 0; round < 4; round++) {
            for (int i = 0; i < numPlayers; i++) {
                Card card = pack.takeCard();
                decks[i].insertCard(card);
            }
        }

        Thread[] threads = new Thread[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            threads[i] = new Thread(players[i]);
        }
        for (int i = 0; i < numPlayers; i++) {
            threads[i].start();
        }
        for (int i = 0; i < numPlayers; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        for (int i = 0; i < numPlayers; i++) {
            decks[i].writeToTextFile();
        }
        

}


    /**
     * helper function that checks the validity of a txt file by making
     * sure the file exists, there are no negative values, and that
     * there are enough lines to satisfy the 8n pack size rule.
     */
    public static boolean isValidPack(String filepath, int numPlayers) {
        int lineCount = 0;
        try {
            File file = new File(filepath);
            Scanner fileScanner = new Scanner(file);
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                lineCount++;
                
                try {
                    int value = Integer.parseInt(line);
                    if (value < 0) {
                        fileScanner.close();
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            
        } catch (FileNotFoundException e) {
                return false;
            }
        return lineCount == 8 * numPlayers;
    }
}