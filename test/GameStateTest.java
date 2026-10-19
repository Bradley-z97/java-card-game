import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.DisplayName;

public class GameStateTest {

    @Test
    @DisplayName("Testing for immediate win detection")
    public void immediateWinDetectionTest() {
        GameState gameState = new GameState();
        Deck addingDeck = new Deck(3, gameState);
        Deck removingDeck = new Deck(4, gameState);
        Player player = new Player(3, addingDeck, removingDeck, gameState);

        player.addToHand(new Card(3));
        player.addToHand(new Card(3));
        player.addToHand(new Card(3));
        player.addToHand(new Card(3));

        assertTrue(gameState.hasWinner(player));

    }
    
    @Test
    @DisplayName("Testing for no immediate win detection")
    public void notImmediateWinDetectionTest() {
        GameState gameState = new GameState();
        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);
        Player player = new Player(1, addingDeck, removingDeck, gameState);

        player.addToHand(new Card(1));
        player.addToHand(new Card(1));
        player.addToHand(new Card(1));
        player.addToHand(new Card(3));

        assertFalse(gameState.hasWinner(player));
        assertFalse(gameState.isWon());
    }

    @Test
    @DisplayName("Testing that a winning hand needs 4 cards")
    public void fourCardWinTest() {
        GameState gameState = new GameState();
        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);
        Player player = new Player(1, addingDeck, removingDeck, gameState);

        player.addToHand(new Card(1));
        player.addToHand(new Card(1));
        player.addToHand(new Card(1));

        assertFalse(gameState.hasWinner(player));
        assertFalse(gameState.isWon());
    }
}
