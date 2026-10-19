import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.DisplayName;

public class GameStateTest {

    @Test
    @DisplayName("Testing for immediate win detection")
    public void immediateWinDetectionTest() {

        GameState gameState = new GameState();

        // Create an addingDeck and removingDeck and a player with those decks
        Deck addingDeck = new Deck(3, gameState);
        Deck removingDeck = new Deck(4, gameState);
        Player player = new Player(3, addingDeck, removingDeck, gameState);

        // Give the player 3 of the same card
        player.addToHand(new Card(3));
        player.addToHand(new Card(3));
        player.addToHand(new Card(3));
        player.addToHand(new Card(3));

        // Check that they find an immediate winner
        assertTrue(gameState.hasWinner(player));

    }
    
    @Test
    @DisplayName("Testing for no immediate win detection")
    public void notImmediateWinDetectionTest() {

        GameState gameState = new GameState();

        // Create an addingDeck and removingDeck for a player
        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);
        Player player = new Player(1, addingDeck, removingDeck, gameState);

        // Add cards to player hand
        player.addToHand(new Card(1));
        player.addToHand(new Card(1));
        player.addToHand(new Card(1));
        player.addToHand(new Card(3));

        // Check the player is not an immediate winner with 3 of the same card
        assertFalse(gameState.hasWinner(player));
        assertFalse(gameState.isWon());
    }

    @Test
    @DisplayName("Testing that a winning hand needs 4 cards")
    public void fourCardWinTest() {

        GameState gameState = new GameState();

        // Create an addingDeck and removingDeck for a player
        Deck addingDeck = new Deck(1, gameState);
        Deck removingDeck = new Deck(2, gameState);
        Player player = new Player(1, addingDeck, removingDeck, gameState);

        // Add only 3 cards to a players hand
        player.addToHand(new Card(1));
        player.addToHand(new Card(1));
        player.addToHand(new Card(1));

        // Tests that they aren't the winner and that no one has won
        assertFalse(gameState.hasWinner(player));
        assertFalse(gameState.isWon());
    }
}
