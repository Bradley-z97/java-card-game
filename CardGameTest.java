import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CardGameTest {

    @Test
    @DisplayName("Testing a game ends cleanly after a player wins")
    public void testGameEndsCleanly() throws InterruptedException {
        GameState gameState = new GameState();

        // Create two decks for test
        Deck deck1 = new Deck(1, gameState);
        Deck deck2 = new Deck(2, gameState);

        // deck1 is left of player 1, and deck 2 is on the right
        // opposite for player 2
        Player player1 = new Player(1, deck1, deck2, gameState);
        Player player2 = new Player(2, deck2, deck1, gameState);

        // player 1 starts with a winning hand
        for (int i = 0; i < 4; i++) {
            player1.addToHand(new Card(1));
            player2.addToHand(new Card(3));
        }
        // add cards so player 2 never draws from an empty deck
        for (int i = 0; i < 10; i++) {
            deck1.insertCard(new Card(5));
            deck2.insertCard(new Card(6));
        }

        Thread t1 = new Thread(player1);
        Thread t2 = new Thread(player2);
        
        t1.start();
        t2.start();
        // waits 2 seconds for threads to finish 
        t1.join(2000);
        t2.join(2000);

        assertFalse(t1.isAlive());
        assertFalse(t2.isAlive());
        assertEquals(1, gameState.getWinnerId());
    }


    /**
     * Checks deck output text file matches the final deck contents
     * upon game end.
     */
    @Test
    @DisplayName("Testing the deckX_output.txt at game end is matches the deck contents")
    public void testOutputTxtIsCorrect() throws IOException {
        GameState gameState = new GameState();
        Deck deck1 = new Deck(1, gameState);
        Deck deck2 = new Deck(2, gameState);

        // trigger a win so deck contents text file is created
        Player player1 = new Player(1, deck1, deck2, gameState);
        for (int i = 0; i < 4; i++) {
            player1.addToHand(new Card(1));
        }
        gameState.hasWinner(player1);

        for (int i = 1; i <= 4; i++) {
            deck1.insertCard(new Card(i));
        }

        File output = new File("deck1_output.txt");
        try {
            deck1.writeToTextFile(); // deck catches its own exception

            // throws FileNotFoundException which extends IOException
            try (Scanner scanner = new Scanner(output)) {
                // if deck failed to write, .nextLine() will error (empty file) 
                assertEquals("deck1 contents: 1 2 3 4 ", scanner.nextLine());
            }
        } finally {
            // delete all text files
            output.delete();
            new File("deck2_output.txt").delete();
            new File("Player1.txt").delete();
        }
    }
}