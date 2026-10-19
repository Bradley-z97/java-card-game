import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class GameEndTest {

    @Test
    @DisplayName("Testing a game ends cleanly after a player wins")
    public void testGameEndsCleanly() {
        GameState gameState = new GameState();

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
}